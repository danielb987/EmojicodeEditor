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
package emojicode.compiler;

import emojicode.SourcePosition;
import emojicode.TokenType;
import emojicode.Token;
import java.util.List;

/**
 * A stream of tokens.
 */
public final class TokenStream {
    
    public static final String UNEXPECTED_END_OF_PROGRAM_STRING = "Unexpected end of program";
    
    private final List<Token> tokens;
    private int index = 0;
    private SourcePosition lastPosition;
    
    
    public TokenStream(final List<Token> tokenList) {
        this.tokens = tokenList;
    }
    
    
    public Token consumeToken() throws CompilerError {
        
        assumeHasMoreTokens();
        return tokens.get(index++);
    }
    
/*
    public Token consumeToken() throws CompilerError {
        if (! hasMoreTokens()) {
            throw new CompilerError(nextToken().position, "Unexpected end of program");
        }
        return tokens.get(index++);
    }
*/
    
    public Token consumeToken(final TokenType tokenType) throws CompilerError {
        
        assumeHasMoreTokens();
        
        Token token = nextToken();
        
        if (token.type != tokenType) {
            throw new CompilerError(
                    token.startPosition,
                    token.endPosition,
                    String.format("Expected %s but instead found %s (%s).",
                            tokenType.name(), nextToken().type.name(), nextToken().toString()));
        }
        
        index++;
        
        return token;
    }
    
/*
    public Token consumeToken(TokenType tokenType) throws CompilerError {
        if (! hasMoreTokens()) {
            throw new CompilerError(nextToken().position, "Unexpected end of program");
        }
        
        if (nextToken().type != tokenType)
            throw new CompilerError(
                    nextToken().position,
                    String.format("Expected %s but instead found %s (%s).",
                            tokenType.name(), nextToken().type.name(), nextToken().toString()));
        
        return tokens.get(index++);
    }
*/
    
    public boolean hasMoreTokens() {
        return (index < tokens.size());
    }
    
    
    public boolean nextTokenIs(final TokenType tokenType) {
        return (hasMoreTokens() && nextToken().type == tokenType);
    }
    
    
    public boolean nextTokenIs(final int ch) {
        return nextTokenIs(ch, TokenType.Identifier);
    }
    
    
    public boolean nextTokenIs(final String identifier) {
        return (identifier.length() == 1)
                && nextTokenIs(identifier.codePointAt(0), TokenType.Identifier);
    }
    
    
    public boolean nextTokenIs(final int ch, final TokenType tokenType) {
        return hasMoreTokens()
                && (nextToken().type == tokenType)
                && (nextToken().getFirstChar() == ch);
    }
    
    
    public boolean nextTokenOperatorIs(final String operator) {
        return hasMoreTokens()
                && (nextToken().type == TokenType.Operator)
                && (nextToken().toString().equals(operator));
    }
    
    
    public boolean nextTokenIsEverythingBut(final TokenType tokenType) {
        return hasMoreTokens() && (nextToken().type != tokenType);
    }
    
    
    public boolean nextTokenIsEverythingBut(final int ch, final TokenType tokenType) {
        return hasMoreTokens()
                && (!(nextToken().type == tokenType)
                && (nextToken().getFirstChar() == ch));
    }
    
    
    public boolean consumeTokenIf(final TokenType tokenType) {
        if (nextTokenIs(tokenType)) {
            index++;
            return true;
        }
        return false;
    }
    
    
    public boolean consumeTokenIdentifierIf(final String identifier) {
        if (nextTokenIs(TokenType.Identifier) && nextToken().toString().equals(identifier)) {
            index++;
            return true;
        }
        return false;
    }
    
    
    public boolean consumeTokenIf(final int ch) {
        return consumeTokenIf(ch, TokenType.Identifier);
    }
    
    
    public boolean consumeTokenIf(final int ch, final TokenType tokenType) {
        if (nextTokenIs(ch, tokenType)) {
            index++;
            return true;
        }
        return false;
    }
    
    
    public Token nextToken() {
        Token token = tokens.get(index);
        lastPosition = token.startPosition;
        return token;
    }
    
    
    public void assumeHasMoreTokens() throws CompilerError {
        if (!hasMoreTokens()) {
            throw new CompilerError(lastPosition, lastPosition, UNEXPECTED_END_OF_PROGRAM_STRING);
        }
    }
    
    
    public String getIdentifierToken() throws CompilerError {
        Token token = consumeToken();
        if (token.type != TokenType.Identifier) {
            throw new CompilerError(token.startPosition,
                                    token.endPosition,
                                    "Unexpected token. Expected token "
                                            + TokenType.Identifier.name()
                                            + " but found token "
                                            + token.type.name());
        }
        
        return token.toString();
    }
    
    
    public int getIntegerToken() throws CompilerError {
        Token token = consumeToken();
        if (token.type != TokenType.Integer) {
            throw new CompilerError(token.startPosition,
                                    token.endPosition,
                                    "Unexpected token. Expected token "
                                            + TokenType.Integer.name()
                                            + " but found token "
                                            + token.type.name());
        }
        
        try {
            return Integer.parseInt(token.toString());
        } catch (NumberFormatException ex) {
            throw new CompilerError(token.startPosition,
                                    token.endPosition,
                                    String.format("Invalid number %s", token.toString()));
        }
    }
    
    
    public Token consumeIdentifierToken(final String identifier) throws CompilerError {
        Token token = consumeToken();
        if (token.type != TokenType.Identifier) {
            throw new CompilerError(token.startPosition,
                                    token.endPosition,
                                    "Unexpected token. Expected token "
                                            + TokenType.Identifier.name()
                                            + " but found token "
                                            + token.type.name());
        }
        
        if (!token.toString().equals(identifier)) {
            throw new CompilerError(token.startPosition,
                                    token.endPosition,
                                    "Unexpected identifier. Expected identifier "
                                            + identifier
                                            + " but found token "
                                            + token.toString());
        }
        
        return token;
    }
    
}
