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

import java.util.HashMap;
import java.util.Map;

/**
 * The types of tokens that the compiler can handle.
 *
 * @author Daniel Bergqvist
 */
public enum TokenType {
    
    //CHECKSTYLE.OFF: JavadocVariableCheck - No need for individual Javadoc
    // comments for all these enums
    
    NoType,
    String,
    MultilineComment,
    SinglelineComment,
    DocumentationComment,
    Integer,
    Double,
    BooleanTrue(Emojicode.E_THUMBS_UP_SIGN),
    BooleanFalse(Emojicode.E_THUMBS_DOWN_SIGN),
    Identifier,
    Variable,
    Symbol,
    Operator(TokenType.getOperatorEmojis()),
    BeginArgumentList(Emojicode.E_WHITE_EXCLAMATION_MARK),
    EndArgumentList(Emojicode.E_RED_EXCLAMATION_MARK),
    GroupBegin(Emojicode.E_RIGHT_FACING_FIST),
    GroupEnd(Emojicode.E_LEFT_FACING_FIST),

    BlockBegin(Emojicode.E_GRAPES),
    BlockEnd(Emojicode.E_WATERMELON),
    Return(Emojicode.E_RIGHT_ARROW_CURVING_LEFT),
    RepeatWhile(Emojicode.E_CLOCKWISE_RIGHT_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS),
    ForIn(Emojicode.E_CLOCKWISE_RIGHT_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY),
    Error(Emojicode.E_POLICE_CARS_LIGHT),
    If(Emojicode.E_TANGERINE),
    ElseIf(Emojicode.E_LEMON),
    Else(Emojicode.E_STRAWBERRY),
    SuperInit(Emojicode.E_GOAT),
    FrozenDeclaration(Emojicode.E_SOFT_ICE_CREAM),
    Declaration(Emojicode.E_SHORTCAKE),
    Assignment(Emojicode.E_CUSTARD),
    ErrorHandler(Emojicode.E_AVOCADO),
    New(Emojicode.E_NEW_SIGN),
    This(Emojicode.E_DOG);
    
    //CHECKSTYLE.ON: JavadocVariableCheck
    
    
    /**
     * A map between unicode codepoint and token type.
     */
    private static Map<Integer, TokenType> map;
    
    
    private static void put(final int codePoint, final TokenType type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument type must not be null");
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(codePoint, type);
    }
    
    
    private TokenType() {
    }
    
    
    private TokenType(final String emojicode) {
        put(emojicode.codePointAt(0), this);
    }
    
    
    private TokenType(final String[] emojicodeList) {
        for (String emojicode : emojicodeList) {
            put(emojicode.codePointAt(0), this);
        }
    }
    
    
    /**
     * Get all emojis that are operators.
     * 
     * @return a list of all emojis that are operators
     */
    private static String[] getOperatorEmojis() {
        String[] operatorEmojis = {
                                        Emojicode.E_HEAVY_PLUS_SIGN,
                                        Emojicode.E_HEAVY_MINUS_SIGN,
                                        Emojicode.E_HEAVY_DIVISION_SIGN,
                                        Emojicode.E_HEAVY_MULTIPLICATION_SIGN,
                                        Emojicode.E_OPEN_HANDS,
                                        Emojicode.E_HANDSHAKE,
                                        Emojicode.E_LEFT_POINTING_TRIANGLE,
                                        Emojicode.E_RIGHT_POINTING_TRIANGLE,
                                        Emojicode.E_LEFTWARDS_ARROW,
                                        Emojicode.E_RIGHTWARDS_ARROW,
                                        Emojicode.E_HEAVY_LARGE_CIRCLE,
                                        Emojicode.E_ANGER_SYMBOL,
                                        Emojicode.E_CROSS_MARK,
                                        Emojicode.E_LEFT_POINTING_BACKHAND_INDEX,
                                        Emojicode.E_RIGHT_POINTING_BACKHAND_INDEX,
                                        Emojicode.E_PUT_LITTER_IN_ITS_SPACE,
                                        Emojicode.E_HANDS_RAISED_IN_CELEBRATION,
                                        Emojicode.E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE,
                                        Emojicode.E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK,
        };
        return operatorEmojis;
    }
    
    
    /**
     * Get the token type of the unicode codepoint.
     * 
     * @param codePoint the codepoint for which to get the token type
     * @return the token type
     */
    public static final TokenType getTokenType(int codePoint) {
        return map.get(codePoint);
    }
}
