/*
 * Copyright 2017 Theo Weidmann. All rights reserved.
 *
 * Converted to Java by Daniel Bergqvist.
 *
 * See the file LICENSE for The Artistic License 2.0
 */
package emojicode;

import emojicode.compiler.CompilerError;
import emojicode.compiler.EmojiTokenization;
import emojicode.compiler.StringHelper;

/**
 * An emojicode token
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
