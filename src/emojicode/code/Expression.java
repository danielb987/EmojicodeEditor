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
 * Expression
 * http://www.emojicode.org/docs/reference/syntax.html#srule-expression
 * 
 * @author Daniel Bergqvist
 */
public class Expression extends Parent {
    
    private Token createInstanceOfClass;
    private Token executeConstructor;
    
    private Expression returnExpression;
    private MethodCall methodCall;
    
    
    public Expression(Parent parent) {
        super(parent, Parent.HasVariables.NO);
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        Token token = tokenStream.nextToken();
        
//        System.out.format("TokenType: %s, token: %s\n", token.type.name(), token.toString());
        
        this.startPosition = token.startPosition;
        
        switch (token.type) {
            
            case Identifier:
                if (token.toString().equals("🍎")) {     // 🍎 = return the value of the following expression
                    tokenStream.consumeToken();
                    returnExpression = new Expression(this);
                    returnExpression.parse(tokenStream);
                } else if (token.toString().equals(Emojicode.E_LARGE_BLUE_DIAMOND)) {     // 🔷 = create new instance of a class
                    tokenStream.consumeToken();
                    if (tokenStream.nextTokenIs(TokenType.This))
                        createInstanceOfClass = tokenStream.consumeToken(TokenType.This);
                    else
                        createInstanceOfClass = tokenStream.consumeToken(TokenType.Identifier);
                    
                    if (tokenStream.nextTokenIs(TokenType.New))
                        executeConstructor = tokenStream.consumeToken(TokenType.New);
                    else
                        executeConstructor = tokenStream.consumeToken(TokenType.Identifier);
                } else if (token.toString().equals(Emojicode.E_DOUGHNUT)) {     // 🍩 = call type method
                    tokenStream.consumeToken();
                    methodCall = new MethodCall(this, EmojiMethod.MethodType.TYPE_METHOD);
                    methodCall.parse(tokenStream);
                    this.endPosition = methodCall.endPosition;
                } else {                // call method
                    methodCall = new MethodCall(this, EmojiMethod.MethodType.METHOD);
                    methodCall.parse(tokenStream);
                    this.endPosition = methodCall.endPosition;
                }
                break;
                
            default:
                throw new RuntimeException("Why did we get here??? TokenType: "+token.type.name());
        }
    }
    
    
    public void run(List<List<Variable>> variableStack) {
        if (methodCall != null)
            methodCall.run(variableStack);
    }
    
}
