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
import emojicode.compiler.Lexer;
import emojicode.compiler.LogicError;
import emojicode.compiler.TokenStream;

/**
 *
 * @author Daniel Bergqvist
 */
public class EmojiPackageUserDefinied extends EmojiPackage {
    
    final String filename;
    final String source;
    
    MainBlock mainBlock;
    
    DocumentationComment lastDocumentationComment = null;
    
    
    public EmojiPackageUserDefinied(String name, String filename, String source) {
        super(name);
        this.filename = filename;
        this.source = source;
    }
    
    
    public void parse() throws CompilerError, LogicError {
        
        Lexer lexer = new Lexer(source, filename);

        TokenStream tokenStream = lexer.lex();
        
        parse(tokenStream);
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError, LogicError {
        
        while (tokenStream.hasMoreTokens()) {
            Token token = tokenStream.nextToken();
//            System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
            
            if (token.type == TokenType.Identifier) {
                switch (token.toString()) {
                    case Emojicode.E_TURKEY:      // 🦃 - enumeration
                        Enumeration enumeration = new Enumeration(lastDocumentationComment, this);
                        enumeration.parse(tokenStream);
                        lastDocumentationComment = null;
                        break;
                    case Emojicode.E_CHEQUERED_FLAG:    // 🏁 - start flag = start of program
                        mainBlock = new MainBlock(lastDocumentationComment, this);
                        mainBlock.parse(tokenStream);
                        lastDocumentationComment = null;
                        break;
                    case Emojicode.E_RABBIT:    // 🐇 - class = define a class
                        tokenStream.consumeToken();
                        Token classIdentifierToken = tokenStream.consumeToken(TokenType.Identifier);
                        EmojiClassUserDefined emojiClass = new EmojiClassUserDefined(classIdentifierToken.toString(), lastDocumentationComment, this);
                        emojiClass.parse(tokenStream);
                        classes.put(emojiClass.name, emojiClass);
//                        mainBlock = new MainBlock(lastDocumentationComment, this);
//                        mainBlock.parse(tokenStream);
                        lastDocumentationComment = null;
                        break;
                    default:
                        throw new CompilerError(token.startPosition, token.endPosition, "Unknown identifier found: "+token.toString());
                }
            }
            else {
                System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
                throw new CompilerError(token.startPosition, token.endPosition, "Token Identifier expected, found token "+token.type.name());
            }
//            tokenStream.consumeToken();
            
//            throw new RuntimeException("Hej");
        }
        
        if (1==1)
            return;
        
        // We shouldn't be here.....
        
        
        
        
        
        Token token = tokenStream.nextToken();
        
        if (token.type == TokenType.Identifier) {
            
            if (token.toString().equals(Emojicode.E_CHEQUERED_FLAG))    // 🏁 - start flag = start of program
                ;
            else if (token.toString().equals(Emojicode.E_TURKEY))   // 🦃 - enumeration
                ;
            else if (token.toString().equals(Emojicode.E_RABBIT))   // 🐇 - class
                ;
            else if (token.toString().equals(Emojicode.E_DOVE_OF_PEACE))   // 🕊 - value type
                ;
            else if (token.toString().equals(Emojicode.E_CROCODILE))   // 🐊 - protocol
                ;
            else if (token.toString().equals(Emojicode.E_PACKAGE))   // 📦 - import package
                ;
            else if (token.toString().equals(Emojicode.E_WALE))   // 🐋 - extension. http://www.emojicode.org/docs/reference/extensions.html
                ;
            else if (token.toString().equals(Emojicode.E_EARTH_GLOBE_EUROPE_AFRICA))   // 🌍 - export package, make a package public. http://www.emojicode.org/docs/reference/packages.html
                ;
            else if (token.toString().equals(Emojicode.E_CRYSTAL_BALL))   // 🔮 - package version. http://www.emojicode.org/docs/reference/packages.html
                ;
            else if (token.toString().equals(Emojicode.E_SCROLL))   // 📜 - include source file
                ;
            else
                System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
        }
        else
            System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
        
        
        tokenStream.consumeToken();
        
    }
    
    
    public void run() {
        mainBlock.run();
    }
    
}
