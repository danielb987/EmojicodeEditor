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
import emojicode.compiler.LogicError;
import emojicode.compiler.TokenStream;

/**
 * Statement
 * http://www.emojicode.org/docs/reference/syntax.html#srule-statement
 * 
 * @author Daniel Bergqvist
 */
public class Statement extends Parent {
    
    private Expression expression;
    
    
    public Statement(final Parent parent) {
        super(parent);
    }
    
    
    public void parse(final TokenStream tokenStream) throws CompilerError, LogicError {
        
        Token token = tokenStream.nextToken();
        
        this.startPosition = token.startPosition;
        
//        System.out.format("Token %s: %s\n", token.type.name(), token.toString());
        
        switch (token.type) {
            case If:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case ForIn:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case RepeatWhile:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case Return:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case Declaration:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case Assignment:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case SuperInit:
//                System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
                tokenStream.consumeToken(TokenType.SuperInit);
                Token superInitToken;
                if (tokenStream.nextTokenIs(TokenType.New)) {
                    superInitToken = tokenStream.consumeToken(TokenType.New);
                } else {
                    superInitToken = tokenStream.consumeToken(TokenType.SuperInit);
                }
//                if (1==1) throw new CompilerError(token.startPosition, token.endPosition, "Code not implemented yet");
                break;
            case ErrorHandler:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case Error:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case FrozenDeclaration:
                if (1 == 1) { throw new RuntimeException("Code not implemented yet"); }
                break;
            case Identifier:
                expression = new Expression(this);
                expression.parse(tokenStream);
                this.middlePosition = expression.endPosition;
                this.endPosition = expression.endPosition;
                break;
            default:
                throw new RuntimeException("Why did we get here??? TokenType: "+token.type.name());
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
    
    
    public void run() {
//        Debugger.getInstance().step(startPosition, middlePosition, endPosition);
        if (expression != null) {
            expression.run();
        }
    }
    
}
