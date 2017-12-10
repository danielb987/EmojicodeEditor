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

import emojicode.Token;
import emojicode.TokenType;
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import java.util.List;

/**
 * Emojicode Statement.
 * http://www.emojicode.org/docs/reference/syntax.html#srule-statement
 */
public final class Statement extends Parent {
    
    /**
     * An emojicode statement.
     */
    private SubStatement subStatement;
    
    /**
     * An emojicode expression.
     */
    private Expression expression;
    
    
    /**
     * Create an instance.
     * @param parent The parent
     */
    public Statement(final Parent parent) {
        super(parent, Parent.HasVariables.NO);
    }
    
    
    /**
     * Parse this statement.
     * @param tokenStream the stream of tokens.
     * @throws CompilerError thrown on compilation error
     */
    public void parse(final TokenStream tokenStream) throws CompilerError {
        
        Token token = tokenStream.nextToken();
        
        this.startPosition = token.startPosition;
        
//        System.out.format("Token %s: %s\n", token.type.name(), token.toString());
        
        switch (token.type) {
            case If:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case ForIn:
                subStatement = new StatementForIn(this);
                subStatement.parse(tokenStream);
                break;
            case RepeatWhile:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case Return:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case Declaration:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case Assignment:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case SuperInit:
//                System.out.format("Token: %s, value: %s\n", token.type.toString(),
//                                  token.toString());
                tokenStream.consumeToken(TokenType.SuperInit);
                Token superInitToken;
                if (tokenStream.nextTokenIs(TokenType.New)) {
                    superInitToken = tokenStream.consumeToken(TokenType.New);
                } else {
                    superInitToken = tokenStream.consumeToken(TokenType.SuperInit);
                }
//                if (1==1) throw new CompilerError(token.startPosition, token.endPosition,
//                                                  "Code not implemented yet");
                break;
            case ErrorHandler:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case Error:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case FrozenDeclaration:
                throw new RuntimeException("Code not implemented yet");
//                break;
            case Identifier:
                expression = new Expression(this);
                expression.parse(tokenStream);
                this.middlePosition = expression.endPosition;
                this.endPosition = expression.endPosition;
                break;
            default:
                throw new CompilerError(token.startPosition,
                                        token.endPosition,
                                        "Why did we get here??? TokenType: "
                                            + token.type.name());
//                throw new RuntimeException("Why did we get here??? TokenType: "
//                                            + token.type.name());
//                break;
        }
        
//        expression


//        if
//        for-in
//        repeat-while
//        return
//        declaration
//        assignment
//        superinitializer
//        error-check-control
//        error
//        frozen-declaration
        
    }
    
    
    /**
     * Run the emojicode statement.
     * @param variableStack 
     */
    public void run(List<List<Variable>> variableStack) {
//        Debugger.getInstance().step(startPosition, middlePosition, endPosition);
        if (subStatement != null) {
            subStatement.run(variableStack);
        }
        
        if (expression != null) {
            expression.run(variableStack);
        }
    }
    
}
