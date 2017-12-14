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
import emojicode.compiler.LogicError;
import emojicode.compiler.TokenStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration
 * http://www.emojicode.org/docs/reference/enums.html
 * 
 * @author Daniel Bergqvist
 */
public class Enumeration extends Parent {
    
    
    private String typeIdentifier;
    private final DocumentationComment documentationComment;
    
    private final List<String> enumerationValues = new ArrayList<>();
    
    DocumentationComment lastDocumentationComment = null;
    
    
    public Enumeration(DocumentationComment documentationComment, Parent parent) {
        super(parent, Parent.HasVariables.NO);
        this.documentationComment = documentationComment;
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        tokenStream.consumeIdentifierToken(Emojicode.E_TURKEY);     // 🦃 - enumeration
        
        typeIdentifier = tokenStream.getIdentifierToken();          // Type identifier
        
        tokenStream.consumeToken(TokenType.BlockBegin);
        
        tokenStream.assumeHasMoreTokens();
        
//        System.out.println("Enumeration");
        while (tokenStream.consumeTokenIdentifierIf(Emojicode.E_RADIO_BUTTON)) {
//            System.out.format("Next token: %s, %s\n", tokenStream.nextToken().type.name(), tokenStream.nextToken().toString());
            enumerationValues.add(tokenStream.getIdentifierToken());
        }
        
        
        
        while (tokenStream.hasMoreTokens()) {
            Token token = tokenStream.nextToken();
//            System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
            
            if (token.fType == TokenType.Identifier) {
                switch (token.toString()) {
                    case Emojicode.E_RABBIT:      // 🐇 - type method
                        throw new RuntimeException("Not implemented yet. See EmojiClass for how to do this");
//                        TypeMethod typeMethod = new TypeMethod(lastDocumentationComment, this);
//                        typeMethod.parse(tokenStream);
//                        break;
                    default:
                        throw new CompilerError(token.fStartPosition, token.fEndPosition, "Unknown identifier found: "+token.toString());
                }
            }
            else {
//                System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
                throw new CompilerError(token.fStartPosition, token.fEndPosition, "Token Identifier expected, found token "+token.fType.name());
            }
//            tokenStream.consumeToken();
        }
        
        
//        System.out.format("\n\nNext token: %s, %s\n", tokenStream.nextToken().type.name(), tokenStream.nextToken().toString());
//        while (tokenStream.hasMoreTokens()) {
//            Token token = tokenStream.nextToken();
//        }
        
    }
    
    
}
