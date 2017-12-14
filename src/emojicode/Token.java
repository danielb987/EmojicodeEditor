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
    
    /**
     * The type of this token.
     */
    public TokenType fType;
    
    /**
     * The start position of this token.
     */
    public final SourcePosition fStartPosition;
    
    /**
     * The end position of this token.
     */
    public SourcePosition fEndPosition;
    
    /**
     * The value of this token.
     */
    private final StringBuilder fValue = new StringBuilder(50);
    
    /**
     * The value of this token.
     */
    private String fValueString;
    
    
    /**
     * Create a new token.
     * @param aStartPosition the start position of this token.
     */
    public Token(final SourcePosition aStartPosition) {
        this.fStartPosition = aStartPosition;
    }
    
    
    /**
     * Validate this token.
     * @throws CompilerError if the token is not valid
     */
    public void validate() throws CompilerError {
        if (fType == TokenType.Integer) {
            if (StringHelper.getLastUnicodeChar(toString()) == 'x') {
                throw new CompilerError(fStartPosition,
                                        fEndPosition,
                                        "Expected a digit after integer literal prefix.");
            }
        }
        if (fType == TokenType.Double) {
            if (StringHelper.getLastUnicodeChar(toString()) == '.') {
                throw new CompilerError(fStartPosition,
                                        fEndPosition,
                                        "Expected a digit after decimal seperator.");
            }
        }
        if (fType == TokenType.Identifier) {
            if (!EmojiTokenization.isValidEmoji(toString())) {
                throw new CompilerError(fStartPosition, fEndPosition, "Invalid emoji.");
            }
        }
    }
    
    
    /**
     * Is this token an identifier which starts with the character ch?
     * @param ch the character the identifier must start with
     * @return true if the token is an identifier that starts with the character ch
     */
    public boolean isIdentifier(final int ch) {
        return (fType == TokenType.Identifier)
                && (fValue.length() == 1)
                && (fValue.codePointAt(0) == ch);
    }
    
    
    /**
     * Return the first unicode codepoint in the token.
     * @return the first unicode codepoint in the token
     */
    public int getFirstCodepoint() {
        return fValue.codePointAt(0);
    }
    
    
    /**
     * Append a string to the token.
     * @param string the string to be appended to the token
     */
    public void append(final String string) {
//        System.out.format("Token.append string: %s\n", string);
        fValue.append(string);
        fValueString = null;
    }
    
    
    /**
     * Append an array of characters to the token.
     * @param chars the characters to be appended to the token
     */
    public void append(final char[] chars) {
        fValue.append(chars);
        fValueString = null;
//        System.out.format("Token.append chars: %s. After: %s\n", new String(chars),
//                value.toString());
    }
    
    
    /**
     * Append an unicode codepoint to the token.
     * @param codePoint the characters to be appended to the token
     */
    public void append(final int codePoint) {
        fValue.append(Character.toChars(codePoint));
        fValueString = null;
//        System.out.format("Token.append codePoint: %s. After: %s\n",
//                new String(Character.toChars(codePoint)), value.toString());
    }
    
    
    /**
     * Return the value of the token.
     * @return the value of the token
     */
    @Override
    public String toString() {
        if (fValueString == null) {
            fValueString = fValue.toString();
        }
        return fValueString;
    }
    
    
}
