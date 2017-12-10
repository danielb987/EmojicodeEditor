/*
 * The MIT License
 *
 * Copyright 2017 Daniel Bergqvist.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package emojicode.code;

import emojicode.Emojicode;
import emojicode.Token;
import emojicode.TokenType;
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import java.util.List;

/**
 * ForIn statement.
 * Loops thru a list of items.
 */
public class StatementForIn extends SubStatement {
    
    private String forInVariableName;
    private LocalVariableDefinition forInVariable;
    
    private Expression expression;
    
    private boolean hasRangeExpression;
    private int rangeStart;
    private int rangeStep;
    private int rangeStop;
    
    private CodeBlock codeBlock;
    
    
    public StatementForIn(final Parent parent) {
        
        super(parent, Parent.HasVariables.YES);
    }
    
    /**
     * Parse this statement.
     * @param tokenStream the stream of tokens.
     * @throws CompilerError thrown on compilation error
     */
    @Override
    public void parse(final TokenStream tokenStream) throws CompilerError {
        
        System.out.println("StatementForIn");
        
        Token token = tokenStream.consumeToken(TokenType.ForIn);
        
        this.startPosition = token.startPosition;
        
        token = tokenStream.consumeToken(TokenType.Variable);
        forInVariableName = token.toString();
        
        forInVariable = new LocalVariableDefinition(forInVariableName,
                                                                       EmojicodeVariableType.INTEGER,
                                                                       IsFrozen.NO,
                                                                       currentStackBlockIndex,
                                                                       currentStackVariableIndex);
        
        addVariable(startPosition, endPosition, forInVariable);
        
        token = tokenStream.nextToken();
        
        if ((token.type == TokenType.Identifier)
                && (Emojicode.E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE.equals(token.toString()))) {
            
            tokenStream.consumeToken();
            
            hasRangeExpression = true;
            rangeStart = tokenStream.getIntegerToken();
            rangeStep = 1;
            rangeStop = tokenStream.getIntegerToken();
        } else if ((token.type == TokenType.Identifier)
                && (Emojicode.E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR.equals(token.toString()))) {
            
            tokenStream.consumeToken();
            
            hasRangeExpression = true;
            rangeStart = tokenStream.getIntegerToken();
            rangeStep = tokenStream.getIntegerToken();
            rangeStop = tokenStream.getIntegerToken();
        } else {
            expression = new Expression(this);
            expression.parse(tokenStream);
        }
        
        codeBlock = new CodeBlock(this);
        codeBlock.parse(tokenStream);
    }
    
    
    /**
     * Run the statement.
     * @param variableStack 
     */
    @Override
    public void run(List<List<Variable>> variableStack) {
//        Debugger.getInstance().step(startPosition, middlePosition, endPosition);
        
        if (hasRangeExpression) {
            Variable variable = new Variable(forInVariable.type, null);
            variableStack.get(forInVariable.stackBlockIndex).add(variable);
            
            if (rangeStep >= 0) {
                for (int i = rangeStart; i < rangeStop; i += rangeStep) {
                    variable.instance = i;
                    codeBlock.run(variableStack);
                }
            } else {
                for (int i = rangeStart; i > rangeStop; i += rangeStep) {
                    variable.instance = i;
                    codeBlock.run(variableStack);
                }
            }
        } else if (expression != null) {
            expression.run(variableStack);
        }
    }
    
}
