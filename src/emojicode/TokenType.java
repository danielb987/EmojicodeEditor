/*
 * Copyright 2017 Theo Weidmann. All rights reserved.
 *
 * Converted to Java by Daniel Bergqvist.
 *
 * See the file LICENSE for The Artistic License 2.0
 */
package emojicode;

import java.util.HashMap;
import java.util.Map;

/**
 * The types of tokens that the compiler can handle.
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
    
    
    TokenType() {
    }
    
    
    TokenType(final String emojicode) {
        put(emojicode.codePointAt(0), this);
    }
    
    
    TokenType(final String[] emojicodeList) {
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
    public static final TokenType getTokenType(final int codePoint) {
        return map.get(codePoint);
    }
}
