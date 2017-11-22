/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author daniel
 */
public class Lexer {
    
    private final String string;
    
    int codePoint = 0;
    private int index;        // Index in string
    private int line;         // Line number in string
    private int column;       // Column in string
    private String filename;  // Filename
    private SourcePosition lastSourcePosition;
    
    private final List<Token> tokens = new ArrayList<>();
    
    
    public Lexer(String string, String filename) {
        this.string = string;
        index = 0;
        line = 1;
        column = 0;
        this.filename = filename;
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

    boolean ends_with(String value, String ending) {
        return value.endsWith(ending);
    }

    enum TokenState {
        Continues, Ended, NextBegun, Discard
    }

    /// Called if a new code point is available.
    /// @returns True if the token continues (e.g. a string) or false if the token ended (i.e. it only consits of this
    /// single code point).
    boolean beginToken(Token token) {
        TokenType it = TokenType.getToken(codePoint());
        if (it != null) {
//            System.out.format("Begin token. Token: %s\n", it.toString());
            token.type = it;
            token.append(Character.toChars(codePoint()));
            return false;
        }

        switch (codePoint()) {
            case Emojicode.E_INPUT_SYMBOL_LATIN_LETTERS__CODEPOINT:
                token.type = TokenType.String;
                return true;
            case Emojicode.E_OLDER_WOMAN__CODEPOINT:
                token.type = TokenType.MultilineComment;
                return true;
            case Emojicode.E_OLDER_MAN__CODEPOINT:
                token.type = TokenType.SinglelineComment;
                return true;
            case Emojicode.E_TACO__CODEPOINT:
                token.type = TokenType.DocumentationComment;
                return true;
            case Emojicode.E_KEYCAP_10__CODEPOINT:
                token.type = TokenType.Symbol;
                return true;
        }

        if (('0' <= codePoint() && codePoint() <= '9') || codePoint() == '-' || codePoint() == '+') {
            token.type = TokenType.Integer;
            isHex_ = false;
        }
        else if (EmojiTokenization.isEmoji(codePoint())) {
            token.type = TokenType.Identifier;
        }
        else {
            token.type = TokenType.Variable;
        }
//        System.out.format("Begin token. Append chars: %s. Codepoint: %d\n", new String(Character.toChars(codePoint())), codePoint());
        token.append(Character.toChars(codePoint()));
        return true;
    }
    
    /// Called if a new code point is available and beginToken returned true and all previous calls for this token
    /// returned TokenState.Continues.
    TokenState continueToken(Token token) throws LogicError, CompilerError {
        switch (token.type) {
            case Identifier:
                if (foundZWJ_ && EmojiTokenization.isEmoji(codePoint())) {
                    token.append(codePoint());
                    foundZWJ_ = false;
                    return TokenState.Continues;
                }
                if ((EmojiTokenization.isEmojiModifier(codePoint()) && EmojiTokenization.isEmojiModifierBase(StringHelper.getLastUnicodeChar(token.toString()))) ||
                    (EmojiTokenization.isRegionalIndicator(codePoint()) && token.toString().length() == 1 && EmojiTokenization.isRegionalIndicator(token.toString().codePointAt(0)))) {
                     token.append(codePoint());
                     return TokenState.Continues;
                 }
                if (codePoint() == 0x200D) {
                    token.append(codePoint());
                    foundZWJ_ = true;
                    return TokenState.Continues;
                }
                if (codePoint() == 0xFE0F) {  // Emojicode ignores the Emoji modifier behind an emoji character
                    return TokenState.Continues;
                }
                return TokenState.NextBegun;
            case SinglelineComment:
                if (isNewline()) {
                    return TokenState.Discard;
                }
                return TokenState.Continues;
            case MultilineComment:
                if (codePoint() == Emojicode .E_OLDER_WOMAN__CODEPOINT) {
                    return TokenState.Discard;
                }
                return TokenState.Continues;
            case DocumentationComment:
                if (codePoint() == Emojicode .E_TACO__CODEPOINT) {
                    return TokenState.Ended;
                }
                token.append(codePoint());
                return TokenState.Continues;
            case String:
                if (escapeSequence_) {
                    switch (codePoint()) {
                        case Emojicode .E_INPUT_SYMBOL_LATIN_LETTERS__CODEPOINT:
                        case Emojicode .E_CROSS_MARK__CODEPOINT:
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
                        default: {
                            throw new CompilerError(token.startPosition, token.endPosition, "Unrecognized escape sequence ���" +
                                                new String(Character.toChars(codePoint())) + ".");
//                            throw new CompilerError(new SourcePosition(index,line,character,filename), "Unrecognized escape sequence ���" +
//                                                new String(Character.toChars(codePoint())) + ".");
                        }
                    }

                    escapeSequence_ = false;
                }
                else if (codePoint() == Emojicode .E_CROSS_MARK__CODEPOINT) {
                    escapeSequence_ = true;
                    return TokenState.Continues;
                }
                else if (codePoint() == Emojicode .E_INPUT_SYMBOL_LATIN_LETTERS__CODEPOINT) {
                    return TokenState.Ended;
                }

                token.append(codePoint());
//                System.out.println("Token string: "+token.toString());
//                token.endPosition = lastSourcePosition;
                return TokenState.Continues;
            case Variable:
                // A variable can consist of everything except for whitespaces and identifiers (that is emojis)
                // isWhitespace used here because if it is whitespace, the detection will take place below
                if (Emojicode.isWhitespace(codePoint()) || EmojiTokenization.isEmoji(codePoint())) {
                    return TokenState.NextBegun;
                }

                token.append(codePoint());
                return TokenState.Continues;
            case Integer:
                if (('0' <= codePoint() && codePoint() <= '9') || (((64 < codePoint() && codePoint() < 71) || (96 < codePoint() && codePoint() < 103)) && isHex_)) {
                    token.append(codePoint());
                    return TokenState.Continues;
                }
                else if (codePoint() == '.') {
                    token.type = TokenType.Double;
                    token.append(codePoint());
                    return TokenState.Continues;
                }
                else if ((codePoint() == 'x' || codePoint() == 'X') && token.toString().length() == 1 && token.toString().charAt(0) == '0') {
                    isHex_ = true;
                    token.append(codePoint());
                    return TokenState.Continues;
                }
                else if (codePoint() == '_') {
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
                throw new LogicError("Lexer: Token continued but not handled.");
        }
    }
    
    
    /// Lexes the string and returns a TokenStream
    public TokenStream lex() throws CompilerError, LogicError {
        nextCharOrEnd();
        while (continue_) {
            if (detectWhitespace()) {
                nextCharOrEnd();
                continue;
            }
            
//            Token token = new Token(new SourcePosition(index,line,column,filename));
            Token token = new Token(lastSourcePosition);
            token.endPosition = new SourcePosition(index,line,column,filename);
            tokens.add(token);
            readToken(token);
            
//            tokens.add(new Token(new SourcePosition(index,line,character,filename)));
////            tokens.emplace_back(sourcePosition_);
//            readToken(tokens.get(tokens.size()-1));
        }

        return new TokenStream(tokens);
    }
    
    
    /// Reads exactly one token.
    /// This method calls nextChar() as necessary. On return, .codePoint() already returns the next code point for
    /// another call to beginToken(), if .continue_ is true.
    void readToken(Token token) throws CompilerError, LogicError {
        TokenState state = beginToken(token) ? TokenState.Continues : TokenState.Ended;
        while (true) {
            if (state == TokenState.Ended) {
                token.validate();
                nextCharOrEnd();
                return;
            }
            if (state == TokenState.Discard) {
                tokens.remove(tokens.size()-1);
//                tokens_.pop_back();
                nextCharOrEnd();
                return;
            }
            nextChar();
            if (Emojicode.isWhitespace(codePoint))
                token.endPosition = lastSourcePosition;
            else
                token.endPosition = new SourcePosition(index,line,column,filename);
            state = continueToken(token);
            if (state == TokenState.NextBegun) {
                token.validate();
                return;
            }
            // Whitespace must be detected here so that this method returns on NextBegun without calling detectWhitespace()
            // as the detectWhitespace() would otherwise be called twice for the same character. (Here and in lex())
            detectWhitespace();
        }
    }

    boolean isNewline() { return codePoint() == 0x0A || codePoint() == 0x2028 || codePoint() == 0x2029; }

    void nextChar() throws CompilerError {
        if (!hasMoreChars()) {
            SourcePosition position = new SourcePosition(index,line,column,filename);
            throw new CompilerError(position, position, "Unexpected end of file.");
//            throw new CompilerError(new SourcePosition(index,line,character,filename), "Unexpected end of file.");
//            throw CompilerError(tokens_.back().position(), "Unexpected end of file.");
        }
        lastSourcePosition = new SourcePosition(index,line,column,filename);
        codePoint = string.codePointAt(index);
        column++;
        index += Character.charCount(codePoint);
    }
    
    boolean hasMoreChars() { return index < string.length(); }
    
    void nextCharOrEnd() throws CompilerError {
        if (hasMoreChars()) {
            nextChar();
        }
        else {
            continue_ = false;
        }
    }
    
    int codePoint() { return codePoint; }

    boolean isHex_ = false;
    boolean escapeSequence_ = false;
    boolean foundZWJ_ = false;

    boolean continue_ = true;

//    Map<int, TokenType> singleTokens_;    // This is TokenType.map

}
