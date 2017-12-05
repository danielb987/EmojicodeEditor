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

import emojicode.Emojicode;
import emojicode.SourcePosition;
import emojicode.Token;
import emojicode.TokenType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public final class Lexer {
    
    private final String string;
    
    private int codePoint = 0;
    private int index;        // Index in string
    private int line;         // Line number in string
    private int column;       // Column in string
    private final String filename;  // Filename
    private SourcePosition lastSourcePosition;
    
    private final List<Token> tokens = new ArrayList<>();
    
    private boolean isHex = false;
    private boolean escapeSequence = false;
    private boolean foundZWJ = false;

    private boolean continueToken = true;
    
    
    //CHECKSTYLE.OFF: MagicNumberCheck - This class has several magic numbers which is only
    // used in this class.
    
    
    public Lexer(final String aString, final String aFilename) {
        this.string = aString;
        index = 0;
        line = 1;
        column = 0;
        this.filename = aFilename;
    }
    
    
//    boolean isNewline() {
//        return (c == 0x0A || c == 0x2028 || c == 0x2029);
//    }

    boolean detectWhitespace() {
        if (isNewline()) {
            column = 0;
            line++;
            return true;
        }
        return Emojicode.isWhitespace(codePoint());
    }

//    static boolean ends_with(final String value, final String ending) {
//        return value.endsWith(ending);
//    }

    enum TokenState {
        Continues, Ended, NextBegun, Discard
    }
    
    
    /**
     * Called if a new code point is available.
     *
     * @param token Used to return the token to the caller.
     * @return true if the token continues (e.g. a string) or false if the
     * token ended (i.e. it only consists of this single code point).
     */
    boolean beginToken(final Token token) {
        TokenType it = TokenType.getTokenType(codePoint());
        if (it != null) {
//            System.out.format("Begin token. Token: %s\n", it.toString());
            token.type = it;
            token.append(Character.toChars(codePoint()));
            return false;
        }

        switch (codePoint()) {
            case Emojicode.E_INPUT_SYMBOL_LATIN_LETTERS_CODEPOINT:
                token.type = TokenType.String;
                return true;
            case Emojicode.E_OLDER_WOMAN_CODEPOINT:
                token.type = TokenType.MultilineComment;
                return true;
            case Emojicode.E_OLDER_MAN_CODEPOINT:
                token.type = TokenType.SinglelineComment;
                return true;
            case Emojicode.E_TACO_CODEPOINT:
                token.type = TokenType.DocumentationComment;
                return true;
            case Emojicode.E_KEYCAP_10_CODEPOINT:
                token.type = TokenType.Symbol;
                return true;
            default:
                // Do nothing
        }

        if (('0' <= codePoint() && codePoint() <= '9')
                || codePoint() == '-'
                || codePoint() == '+') {
            token.type = TokenType.Integer;
            isHex = false;
        } else if (EmojiTokenization.isEmoji(codePoint())) {
            token.type = TokenType.Identifier;
        } else {
            token.type = TokenType.Variable;
        }
//        System.out.format("Begin token. Append chars: %s. Codepoint: %d\n",
//                new String(Character.toChars(codePoint())), codePoint());
        token.append(Character.toChars(codePoint()));
        return true;
    }
    
    
    /**
     * Called if a new code point is available and beginToken returned true and all previous
     * calls for this token returned TokenState.Continues.
     *
     * @param token the current token.
     * @return the state of the current token.
     * @throws CompilerError compiler error
     */
    TokenState continueToken(final Token token) throws CompilerError {
        switch (token.type) {
            case Identifier:
                if (foundZWJ && EmojiTokenization.isEmoji(codePoint())) {
                    token.append(codePoint());
                    foundZWJ = false;
                    return TokenState.Continues;
                }
                
                if ((EmojiTokenization.isEmojiModifier(codePoint())
                        && EmojiTokenization.isEmojiModifierBase(
                                StringHelper.getLastUnicodeChar(token.toString())))
                        || (EmojiTokenization.isRegionalIndicator(codePoint())
                                && token.toString().length() == 1
                                && EmojiTokenization.isRegionalIndicator(
                                        token.toString().codePointAt(0)))) {
                     token.append(codePoint());
                     return TokenState.Continues;
                }
                
                if (codePoint() == 0x200D) {
                    token.append(codePoint());
                    foundZWJ = true;
                    return TokenState.Continues;
                }
                
                // Emojicode ignores the Emoji modifier behind an emoji character
                if (codePoint() == 0xFE0F) {
                    return TokenState.Continues;
                }
                return TokenState.NextBegun;
                
            case SinglelineComment:
                if (isNewline()) {
                    return TokenState.Discard;
                }
                return TokenState.Continues;
                
            case MultilineComment:
                if (codePoint() == Emojicode.E_OLDER_WOMAN_CODEPOINT) {
                    return TokenState.Discard;
                }
                return TokenState.Continues;
                
            case DocumentationComment:
                if (codePoint() == Emojicode.E_TACO_CODEPOINT) {
                    return TokenState.Ended;
                }
                token.append(codePoint());
                return TokenState.Continues;
                
            case String:
                if (escapeSequence) {
                    switch (codePoint()) {
                        case Emojicode.E_INPUT_SYMBOL_LATIN_LETTERS_CODEPOINT:
                        case Emojicode.E_CROSS_MARK_CODEPOINT:
                            token.append(codePoint());
                            break;
                        case 'n':
                            token.append('\n');
                            break;
                        case 't':
                            token.append('\t');
                            break;
                        case 'r':
                            token.append('\r');
                            break;
                        default:
                            throw new CompilerError(token.startPosition,
                                                    token.endPosition,
                                                    "Unrecognized escape sequence ���"
                                                        + new String(
                                                            Character.toChars(codePoint())) + ".");
                    }

                    escapeSequence = false;
                } else if (codePoint() == Emojicode.E_CROSS_MARK_CODEPOINT) {
                    escapeSequence = true;
                    return TokenState.Continues;
                } else if (codePoint() == Emojicode.E_INPUT_SYMBOL_LATIN_LETTERS_CODEPOINT) {
                    return TokenState.Ended;
                }

                token.append(codePoint());
//                System.out.println("Token string: "+token.toString());
//                token.endPosition = lastSourcePosition;
                return TokenState.Continues;
                
            case Variable:
                // A variable can consist of everything except for whitespaces and identifiers
                // (that is emojis).
                // isWhitespace used here because if it is whitespace, the detection will take
                // place below.
                if (Emojicode.isWhitespace(codePoint()) || EmojiTokenization.isEmoji(codePoint())) {
                    return TokenState.NextBegun;
                }

                token.append(codePoint());
                return TokenState.Continues;
                
            case Integer:
                if (('0' <= codePoint() && codePoint() <= '9')
                        || (((64 < codePoint() && codePoint() < 71)
                        || (96 < codePoint() && codePoint() < 103))
                        && isHex)) {
                    token.append(codePoint());
                    return TokenState.Continues;
                } else if (codePoint() == '.') {
                    token.type = TokenType.Double;
                    token.append(codePoint());
                    return TokenState.Continues;
                } else if ((codePoint() == 'x' || codePoint() == 'X')
                        && token.toString().length() == 1
                        && token.toString().charAt(0) == '0') {
                    isHex = true;
                    token.append(codePoint());
                    return TokenState.Continues;
                } else if (codePoint() == '_') {
                    return TokenState.Continues;
                }
                return TokenState.NextBegun;
                
            case Double:
                if ('0' <= codePoint() && codePoint() <= '9') {
                    token.append(codePoint());
                    return TokenState.Continues;
                }
                return TokenState.NextBegun;
                
            case Symbol:
                token.append(codePoint());
                return TokenState.Ended;
                
            default:
                throw new RuntimeException("Lexer: Token continued but not handled.");
        }
    }
    
    
    /// Lexes the string and returns a TokenStream
    public TokenStream lex() throws CompilerError {
        nextCharOrEnd();
        while (continueToken) {
            if (detectWhitespace()) {
                nextCharOrEnd();
                continue;
            }
            
            Token token = new Token(lastSourcePosition);
            token.endPosition = new SourcePosition(index, line, column, filename);
            tokens.add(token);
            readToken(token);
        }
        
        return new TokenStream(tokens);
    }
    
    
    /**
     * Read exactly one token.
     * This method calls nextChar() as necessary. On return, .codePoint() already returns the
     * next code point for another call to beginToken(), if .continue_ is true.
     * @param token used to return the token to the caller
     * @throws CompilerError a compiler error exception
     */
    void readToken(final Token token) throws CompilerError {
        TokenState state;
        if (beginToken(token)) {
            state = TokenState.Continues;
        } else {
            state = TokenState.Ended;
        }
        
        while (true) {
            if (state == TokenState.Ended) {
                token.validate();
                nextCharOrEnd();
                return;
            }
            if (state == TokenState.Discard) {
                tokens.remove(tokens.size() - 1);
//                tokens_.pop_back();
                nextCharOrEnd();
                return;
            }
            nextChar();
            if (Emojicode.isWhitespace(codePoint)) {
                token.endPosition = lastSourcePosition;
            } else {
                token.endPosition = new SourcePosition(index, line, column, filename);
            }
            state = continueToken(token);
            if (state == TokenState.NextBegun) {
                token.validate();
                return;
            }
            // Whitespace must be detected here so that this method returns on NextBegun
            // without calling detectWhitespace() as the detectWhitespace() would otherwise
            // be called twice for the same character. (Here and in lex())
            detectWhitespace();
        }
    }
    
    
    boolean isNewline() {
        return codePoint() == 0x0A || codePoint() == 0x2028 || codePoint() == 0x2029;
    }
    
    
    void nextChar() throws CompilerError {
        
        if (!hasMoreChars()) {
            SourcePosition position = new SourcePosition(index, line, column, filename);
            throw new CompilerError(position, position, "Unexpected end of file.");
//            throw new CompilerError(new SourcePosition(index,line,character,filename),
//                    "Unexpected end of file.");
//            throw CompilerError(tokens_.back().position(), "Unexpected end of file.");
        }
        lastSourcePosition = new SourcePosition(index, line, column, filename);
        codePoint = string.codePointAt(index);
        column++;
        index += Character.charCount(codePoint);
    }
    
    
    boolean hasMoreChars() {
        return index < string.length();
    }
    
    
    void nextCharOrEnd() throws CompilerError {
        if (hasMoreChars()) {
            nextChar();
        } else {
            continueToken = false;
        }
    }
    
    
    int codePoint() {
        return codePoint;
    }
    
    
    //CHECKSTYLE.ON: MagicNumberCheck - This class has several magic numbers which is only
    // used in this class.
    
}
