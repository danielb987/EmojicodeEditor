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
import emojicode.SourcePosition;
import emojicode.Token;
import emojicode.TokenType;
import emojicode.compiler.CompilerError;
import emojicode.compiler.EmojiTokenization;
import emojicode.compiler.LogicError;
import emojicode.compiler.TokenStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public class EmojiClassUserDefined extends EmojiClass {
    
    
    private Token tokenSuperClass;
    
    
    public EmojiClassUserDefined(String name, Parent parent) {
        super(name, parent);
    }
    
    
    public EmojiClassUserDefined(String name, DocumentationComment documentationComment, Parent parent) {
        super(name, parent);
    }
    
    
    private void parseMethod(   TokenStream tokenStream,
                                DocumentationComment documentationComment,
                                EmojiMethod.MethodType methodType,
                                SourcePosition startPosition) throws CompilerError {
        
        switch (methodType) {
            case REQUIRED_INITIALIZER:
            case INITIALIZER:
                tokenStream.consumeIdentifierToken("🐈");    // 🐈 = initializer (required initializer if preceded by 🔑
                break;
            case TYPE_METHOD:
            case METHOD:
                tokenStream.consumeIdentifierToken(Emojicode.E_PIG);    // 🐖 = method (type method if preceded by 🐇
                break;
            default:
                throw new RuntimeException("methodType has unknown value: " + methodType.name());
        }
        
        Token identifierToken;
        if (tokenStream.nextTokenIs(TokenType.New))
            identifierToken = tokenStream.consumeToken(TokenType.New);      // The name of the method, 🆕
        else
            identifierToken = tokenStream.consumeToken(TokenType.Identifier);     // The name of the method
        
        List<EmojiMethodArgumentType> genericArgumentTypes = new ArrayList<>();
        while (tokenStream.nextTokenIs(Emojicode.E_SPIRAL_SHELL)) {     // 🐚 = generic parameter
            throw new RuntimeException("Not implemented yet");
        }
        
        List<EmojiMethodArgumentType> argumentTypes = new ArrayList<>();
        while (tokenStream.nextTokenIs(TokenType.Variable)) {
            Token parameterToken = tokenStream.consumeToken(TokenType.Variable);     // The name of the parameter method
            Token parameterTypeToken = tokenStream.consumeToken(TokenType.Identifier);     // The type of the parameter method
        }
        
        EmojiArgument returnType = null;
        if (tokenStream.nextTokenOperatorIs(Emojicode.E_RIGHTWARDS_ARROW)) {    // Return type
            tokenStream.consumeToken();
            Token returnTypeToken = tokenStream.consumeToken(TokenType.Identifier);     // The return type of the method
            returnType = new EmojiArgument();
        }
        
        CodeBlock codeBlock = new CodeBlock(this);
        codeBlock.parse(tokenStream);
        
        addInitializerOrMethod(
                new EmojiMethodUserDefined(
                        documentationComment,
                        this,
                        methodType,
                        identifierToken.toString(),
                        genericArgumentTypes,
                        argumentTypes,
                        returnType,
                        codeBlock
                ),
                startPosition,
                codeBlock.endPosition);
        
//        Token t = tokenStream.nextToken();
//        if (1==1) throw new CompilerError(t.startPosition, t.endPosition, String.format("Unknown token found: %s, token: %s, emoji: %b\n", t.toString(), t.type.name(), EmojiTokenization.isEmoji(t.toString().codePointAt(0))));
//        if (1==1) throw new CompilerError(t.startPosition, t.endPosition, String.format("Unknown identifier found: %s, emoji: %b\n", t.toString(), EmojiTokenization.isEmoji(t.toString().codePointAt(0))));
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        DocumentationComment documentationComment = null;
        
        Token token = tokenStream.nextToken();
        
        while ((token.fType == TokenType.Identifier) && (token.toString().equals(Emojicode.E_SPIRAL_SHELL))) {   // 🐚 = Generic type
            tokenStream.consumeToken();
            Token genericVariableToken = tokenStream.consumeToken(TokenType.Identifier);
            Token genericTypeToken = tokenStream.consumeToken(TokenType.Identifier);
            token = tokenStream.nextToken();
        }
        
//        CodeBlock codeBlock = new CodeBlock(this);
//        codeBlock.parse(tokenStream);
        
        if (tokenStream.nextTokenIs(TokenType.Identifier))
            tokenSuperClass = tokenStream.consumeToken(TokenType.Identifier);
        
        tokenStream.consumeToken(TokenType.BlockBegin);
        
        while (! tokenStream.nextTokenIs(TokenType.BlockEnd)) {
            
            token = tokenStream.nextToken();
//            System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
            
            if (token.fType == TokenType.Identifier) {
                switch (token.toString()) {
                    case Emojicode.E_KEY:      // 🔑 = required initializer
                        tokenStream.consumeToken();
                        parseMethod(tokenStream, documentationComment, EmojiMethod.MethodType.REQUIRED_INITIALIZER, token.fStartPosition);
                        break;
                    case "🐈":       // 🐈 = initializer
                        parseMethod(tokenStream, documentationComment, EmojiMethod.MethodType.INITIALIZER, token.fStartPosition);
                        break;
                    case Emojicode.E_RABBIT:      // 🐇 = type method
                        tokenStream.consumeToken();
                        parseMethod(tokenStream, documentationComment, EmojiMethod.MethodType.TYPE_METHOD, token.fStartPosition);
                        break;
                    default:
                        throw new CompilerError(token.fStartPosition, token.fEndPosition, "Unknown identifier found: "+token.toString());
                }
            }
            else {
//                System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
                throw new CompilerError(token.fStartPosition, token.fEndPosition, "Token Identifier expected, found token "+token.fType.name());
            }
        }
        
        tokenStream.consumeToken(TokenType.BlockEnd);
    }

/*
    @Override
    public EmojiClassInstance createInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/    
}
