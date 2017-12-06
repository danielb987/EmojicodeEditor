/*
 * The MIT License
 *
 * Copyright 2017 Theo Weidmann. All rights reserved.
 *
 * Converted to Java by Daniel Bergqvist.
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
package emojicode;

import emojicode.compiler.CompilerError;
import emojicode.compiler.EmojiTokenization;
import emojicode.compiler.StringHelper;

/**
 *
 * @author Daniel Bergqvist
 */
public final class Token {
    
    public TokenType type;
    public final SourcePosition startPosition;
    public SourcePosition endPosition;
    private final StringBuilder value = new StringBuilder();   // The value of this token
    private String valueString;    // The value of this token
    
    public Token(final SourcePosition aStartPosition) {
        this.startPosition = aStartPosition;
    }
    
    
    public void validate() throws CompilerError {
        if (type == TokenType.Integer) {
            if (StringHelper.getLastUnicodeChar(toString()) == 'x') {
                throw new CompilerError(startPosition,
                                        endPosition,
                                        "Expected a digit after integer literal prefix.");
            }
        }
        if (type == TokenType.Double) {
            if (StringHelper.getLastUnicodeChar(toString()) == '.') {
                throw new CompilerError(startPosition,
                                        endPosition,
                                        "Expected a digit after decimal seperator.");
            }
        }
        if (type == TokenType.Identifier) {
            if (!EmojiTokenization.isValidEmoji(toString())) {
                throw new CompilerError(startPosition, endPosition, "Invalid emoji.");
            }
        }
    }
    
    
    public boolean isIdentifier(final int ch) {
        return (type == TokenType.Identifier)
                && (value.length() == 1)
                && (value.codePointAt(0) == ch);
    }
    
    public int getFirstChar() {
        return value.codePointAt(0);
    }
    
    
    public void append(final String string) {
//        System.out.format("Token.append string: %s\n", string);
        value.append(string);
        valueString = null;
    }
    
    
    public void append(final char[] chars) {
        value.append(chars);
        valueString = null;
//        System.out.format("Token.append chars: %s. After: %s\n", new String(chars),
//                value.toString());
    }
    
    
    public void append(final int codePoint) {
        value.append(Character.toChars(codePoint));
        valueString = null;
//        System.out.format("Token.append codePoint: %s. After: %s\n",
//                new String(Character.toChars(codePoint)), value.toString());
    }
    
    
    @Override
    public String toString() {
        if (valueString == null) {
            valueString = value.toString();
        }
        return valueString;
    }
    
    
}
