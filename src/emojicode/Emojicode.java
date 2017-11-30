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

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public interface Emojicode {
    
    // These are fetched from the source code of the Emoji compiler
    // https://github.com/emojicode/emojicode/blob/master/Compiler/Emojis.h
    String E_HOUSE_BUILDING = "🏠";    // 0x1F3E0
    String E_GRAPES = "🍇";    // 0x1F347
    String E_CANDY = "🍬";    // 0x1F36C
    String E_ORANGE_TRIANGLE = "🔶";    // 0x1F536
    String E_RIGHT_ARROW_CURVING_LEFT = "↩";    // 0x21A9
    String E_SHORTCAKE = "🍰";    // 0x1F370
    String E_CUSTARD = "🍮";    // 0x1F36E
    String E_BANANA = "🍌";    // 0x1F34C
    String E_COOKIE = "🍪";    // 0x1F36A
    String E_ICE_CREAM = "🍨";    // 0x1F368
    String E_HONEY_POT = "🍯";    // 0x1F36F
    String E_AUBERGINE = "🍆";    // 0x1F346
    String E_TANGERINE = "🍊";    // 0x1F34A
    String E_LEMON = "🍋";    // 0x1F34B
    String E_STRAWBERRY = "🍓";    // 0x1F353
    String E_WATERMELON = "🍉";    // 0x1F349
    String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS = "🔁";    // 0x1F501
    String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY = "🔂";    // 0x1F502
    String E_DOG = "🐕";    // 0x1F415
    String E_LARGE_BLUE_DIAMOND = "🔷";    // 0x1F537
    String E_HIGH_VOLTAGE_SIGN = "⚡";    // 0x26A1
    String E_CLOUD = "☁";    // 0x2601
    String E_SPIRAL_SHELL = "🐚";    // 0x1F41A
    String E_RADIO = "📻";    // 0x1F4FB
    String E_RIGHTWARDS_ARROW = "➡";    // 0x27A1
    String E_CROCODILE = "🐊";    // 0x1F40A
    String E_PIG = "🐖";    // 0x1F416
    String E_CHEQUERED_FLAG = "🏁";    // 0x1F3C1
    String E_TURKEY = "🦃";    // 0x1F983
    String E_WALE = "🐋";    // 0x1F40B
    String E_CRYSTAL_BALL = "🔮";    // 0x1F52E
    String E_RABBIT = "🐇";    // 0x1F407
    String E_LOCK_WITH_INK_PEN = "🔏";    // 0x1F50F
    String E_OPEN_LOCK = "🔓";    // 0x1F513
    String E_LOCK = "🔒";    // 0x1F512
    String E_CLOSED_LOCK_WITH_KEY = "🔐";    // 0x1F510
    String E_BLACK_NIB = "✒";    // 0x2712
    String E_KEY = "🔑";    // 0x1F511
    String E_PACKAGE = "📦";    // 0x1F4E6
    String E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE = "😜";    // 0x1F61C
    String E_GOAT = "🐐";    // 0x1F410
    String E_BLACK_SQUARE_BUTTON = "🔲";    // 0x1F532
    String E_BEER_MUG = "🍺";    // 0x1F37A
    String E_DOUGHNUT = "🍩";    // 0x1F369
    String E_NEGATIVE_SQUARED_CROSS_MARK = "❎";    // 0x274E
    String E_OPEN_HANDS = "👐";    // 0x1F450
    String E_HANDSHAKE = "🤝";    // 0x1F91D
    String E_HANDS_RAISED_IN_CELEBRATION = "🙌";    // 0x1F64C
    String E_PUT_LITTER_IN_ITS_SPACE = "🚮";    // 0x1F6AE
    String E_STEAM_LOCOMOTIVE = "🚂";    // 0x1F682
    String E_SPARKLES = "✨";    // 0x2728
    String E_OK_HAND_SIGN = "👌";    // 0x1F44C
    String E_INPUT_SYMBOL_FOR_SYMBOLS = "🔣";    // 0x1F523
    String E_ROCKET = "🚀";    // 0x1F680
    String E_MEDIUM_WHITE_CIRCLE = "⚪";    // 0x26AA
    String E_MEDIUM_BLACK_CIRCLE = "⚫";    // 0x26AB
    String E_LARGE_BLUE_CIRCLE = "🔵";    // 0x1F535
    String E_HEAVY_MINUS_SIGN = "➖";    // 0x2796
    String E_HEAVY_PLUS_SIGN = "➕";    // 0x2795
    String E_HEAVY_DIVISION_SIGN = "➗";    // 0x2797
    String E_HEAVY_MULTIPLICATION_SIGN = "✖";    // 0x2716
    String E_LEFT_POINTING_TRIANGLE = "◀";    // 0x25C0
    String E_RIGHT_POINTING_TRIANGLE = "▶";    // 0x25B6
    String E_LEFTWARDS_ARROW = "⬅";    // 0x2B05
    String E_OLDER_WOMAN = "👵";    // 0x1F475
    String E_TACO = "🌮";    // 0x1F32E
    String E_INPUT_SYMBOL_LATIN_LETTERS = "🔤";    // 0x1F524
    String E_CROSS_MARK = "❌";    // 0x274C
    String E_OLDER_MAN = "👴";    // 0x1F474
    String E_THUMBS_UP_SIGN = "👍";    // 0x1F44D
    String E_THUMBS_DOWN_SIGN = "👎";    // 0x1F44E
    String E_KEYCAP_10 = "🔟";    // 0x1F51F
    String E_HOT_PEPPER = "🌶";    // 0x1F336
    String E_LOLLIPOP = "🍭";    // 0x1F36D
    String E_SOFT_ICE_CREAM = "🍦";    // 0x1F366
    String E_CHIPMUNK = "🐿";    // 0x1F43F
    String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR = "⏭";    // 0x23ED
    String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE = "⏩";    // 0x23E9
    String E_WARNING_SIGN = "⚠";    // 0x26A0
    String E_SCROLL = "📜";    // 0x1F4DC
    String E_EARTH_GLOBE_EUROPE_AFRICA = "🌍";    // 0x1F30D
    String E_NO_ENTRY_SIGN = "🚫";    // 0x1F6AB
    String E_HEAVY_LARGE_CIRCLE = "⭕";    // 0x2B55
    String E_ANGER_SYMBOL = "💢";    // 0x1F4A2
    String E_DANGO = "🍡";    // 0x1F361
    String E_DOWN_POINTING_SMALL_RED_TRIANGLE = "🔽";    // 0x1F53D
    String E_DOVE_OF_PEACE = "🕊";    // 0x1F54A
    String E_WHITE_LARGE_SQUARE = "⬜";    // 0x2B1C
    String E_WHITE_SQUARE_BUTTON = "🔳";    // 0x1F533
    String E_BLACK_LARGE_SQUARE = "⬛";    // 0x2B1B
    String E_LEFT_POINTING_BACKHAND_INDEX = "👈";    // 0x1F448
    String E_RIGHT_POINTING_BACKHAND_INDEX = "👉";    // 0x1F449
    String E_RED_QUESTION_MARK = "❓";    // 0x2753
    String E_BABY_BOTTLE = "🍼";    // 0x1F37C
    String E_WHITE_CIRCLE = "⚪";    // 0x26AA
    String E_CRAYON = "🖍";    // 0x1F58D
    String E_RADIO_BUTTON = "🔘";    // 0x1F518
    String E_BENTO_BOX = "🍱";    // 0x1F371
    String E_POLICE_CARS_LIGHT = "🚨";    // 0x1F6A8
    String E_METRO = "🚇";    // 0x1F687
    String E_TRAFFIC_LIGHT = "🚥";    // 0x1F6A5
    String E_AVOCADO = "🥑";    // 0x1F951
    String E_TRIANGLE_POINTED_DOWN = "🔻";    // 0x1F53B
    String E_RED_EXCLAMATION_MARK = "❗";    // 0x2757
    String E_WHITE_EXCLAMATION_MARK = "❕";    // 0x2755
    String E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK = "⁉";    // 0x2049
    String E_RIGHT_FACING_FIST = "🤜";    // 0x1F91C
    String E_LEFT_FACING_FIST = "🤛";    // 0x1F91B
    String E_NEW_SIGN = "🆕";    // 0x1F195
    
    int E_HOUSE_BUILDING_CODEPOINT = 0x1F3E0;    // 🏠, 0x1F3E0
    int E_GRAPES_CODEPOINT = 0x1F347;    // 🍇, 0x1F347
    int E_CANDY_CODEPOINT = 0x1F36C;    // 🍬, 0x1F36C
    int E_ORANGE_TRIANGLE_CODEPOINT = 0x1F536;    // 🔶, 0x1F536
    int E_RIGHT_ARROW_CURVING_LEFT_CODEPOINT = 0x21A9;    // ↩, 0x21A9
    int E_SHORTCAKE_CODEPOINT = 0x1F370;    // 🍰, 0x1F370
    int E_CUSTARD_CODEPOINT = 0x1F36E;    // 🍮, 0x1F36E
    int E_BANANA_CODEPOINT = 0x1F34C;    // 🍌, 0x1F34C
    int E_COOKIE_CODEPOINT = 0x1F36A;    // 🍪, 0x1F36A
    int E_ICE_CREAM_CODEPOINT = 0x1F368;    // 🍨, 0x1F368
    int E_HONEY_POT_CODEPOINT = 0x1F36F;    // 🍯, 0x1F36F
    int E_AUBERGINE_CODEPOINT = 0x1F346;    // 🍆, 0x1F346
    int E_TANGERINE_CODEPOINT = 0x1F34A;    // 🍊, 0x1F34A
    int E_LEMON_CODEPOINT = 0x1F34B;    // 🍋, 0x1F34B
    int E_STRAWBERRY_CODEPOINT = 0x1F353;    // 🍓, 0x1F353
    int E_WATERMELON_CODEPOINT = 0x1F349;    // 🍉, 0x1F349
    int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_CODEPOINT = 0x1F501;    // 🔁, 0x1F501
    int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY_CODEPOINT = 0x1F502;    // 🔂, 0x1F502
    int E_DOG_CODEPOINT = 0x1F415;    // 🐕, 0x1F415
    int E_LARGE_BLUE_DIAMOND_CODEPOINT = 0x1F537;    // 🔷, 0x1F537
    int E_HIGH_VOLTAGE_SIGN_CODEPOINT = 0x26A1;    // ⚡, 0x26A1
    int E_CLOUD_CODEPOINT = 0x2601;    // ☁, 0x2601
    int E_SPIRAL_SHELL_CODEPOINT = 0x1F41A;    // 🐚, 0x1F41A
    int E_RADIO_CODEPOINT = 0x1F4FB;    // 📻, 0x1F4FB
    int E_RIGHTWARDS_ARROW_CODEPOINT = 0x27A1;    // ➡, 0x27A1
    int E_CROCODILE_CODEPOINT = 0x1F40A;    // 🐊, 0x1F40A
    int E_PIG_CODEPOINT = 0x1F416;    // 🐖, 0x1F416
    int E_CHEQUERED_FLAG_CODEPOINT = 0x1F3C1;    // 🏁, 0x1F3C1
    int E_TURKEY_CODEPOINT = 0x1F983;    // 🦃, 0x1F983
    int E_WALE_CODEPOINT = 0x1F40B;    // 🐋, 0x1F40B
    int E_CRYSTAL_BALL_CODEPOINT = 0x1F52E;    // 🔮, 0x1F52E
    int E_RABBIT_CODEPOINT = 0x1F407;    // 🐇, 0x1F407
    int E_LOCK_WITH_INK_PEN_CODEPOINT = 0x1F50F;    // 🔏, 0x1F50F
    int E_OPEN_LOCK_CODEPOINT = 0x1F513;    // 🔓, 0x1F513
    int E_LOCK_CODEPOINT = 0x1F512;    // 🔒, 0x1F512
    int E_CLOSED_LOCK_WITH_KEY_CODEPOINT = 0x1F510;    // 🔐, 0x1F510
    int E_BLACK_NIB_CODEPOINT = 0x2712;    // ✒, 0x2712
    int E_KEY_CODEPOINT = 0x1F511;    // 🔑, 0x1F511
    int E_PACKAGE_CODEPOINT = 0x1F4E6;    // 📦, 0x1F4E6
    int E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE_CODEPOINT = 0x1F61C;    // 😜, 0x1F61C
    int E_GOAT_CODEPOINT = 0x1F410;    // 🐐, 0x1F410
    int E_BLACK_SQUARE_BUTTON_CODEPOINT = 0x1F532;    // 🔲, 0x1F532
    int E_BEER_MUG_CODEPOINT = 0x1F37A;    // 🍺, 0x1F37A
    int E_DOUGHNUT_CODEPOINT = 0x1F369;    // 🍩, 0x1F369
    int E_NEGATIVE_SQUARED_CROSS_MARK_CODEPOINT = 0x274E;    // ❎, 0x274E
    int E_OPEN_HANDS_CODEPOINT = 0x1F450;    // 👐, 0x1F450
    int E_HANDSHAKE_CODEPOINT = 0x1F91D;    // 🤝, 0x1F91D
    int E_HANDS_RAISED_IN_CELEBRATION_CODEPOINT = 0x1F64C;    // 🙌, 0x1F64C
    int E_PUT_LITTER_IN_ITS_SPACE_CODEPOINT = 0x1F6AE;    // 🚮, 0x1F6AE
    int E_STEAM_LOCOMOTIVE_CODEPOINT = 0x1F682;    // 🚂, 0x1F682
    int E_SPARKLES_CODEPOINT = 0x2728;    // ✨, 0x2728
    int E_OK_HAND_SIGN_CODEPOINT = 0x1F44C;    // 👌, 0x1F44C
    int E_INPUT_SYMBOL_FOR_SYMBOLS_CODEPOINT = 0x1F523;    // 🔣, 0x1F523
    int E_ROCKET_CODEPOINT = 0x1F680;    // 🚀, 0x1F680
    int E_MEDIUM_WHITE_CIRCLE_CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    int E_MEDIUM_BLACK_CIRCLE_CODEPOINT = 0x26AB;    // ⚫, 0x26AB
    int E_LARGE_BLUE_CIRCLE_CODEPOINT = 0x1F535;    // 🔵, 0x1F535
    int E_HEAVY_MINUS_SIGN_CODEPOINT = 0x2796;    // ➖, 0x2796
    int E_HEAVY_PLUS_SIGN_CODEPOINT = 0x2795;    // ➕, 0x2795
    int E_HEAVY_DIVISION_SIGN_CODEPOINT = 0x2797;    // ➗, 0x2797
    int E_HEAVY_MULTIPLICATION_SIGN_CODEPOINT = 0x2716;    // ✖, 0x2716
    int E_LEFT_POINTING_TRIANGLE_CODEPOINT = 0x25C0;    // ◀, 0x25C0
    int E_RIGHT_POINTING_TRIANGLE_CODEPOINT = 0x25B6;    // ▶, 0x25B6
    int E_LEFTWARDS_ARROW_CODEPOINT = 0x2B05;    // ⬅, 0x2B05
    int E_OLDER_WOMAN_CODEPOINT = 0x1F475;    // 👵, 0x1F475
    int E_TACO_CODEPOINT = 0x1F32E;    // 🌮, 0x1F32E
    int E_INPUT_SYMBOL_LATIN_LETTERS_CODEPOINT = 0x1F524;    // 🔤, 0x1F524
    int E_CROSS_MARK_CODEPOINT = 0x274C;    // ❌, 0x274C
    int E_OLDER_MAN_CODEPOINT = 0x1F474;    // 👴, 0x1F474
    int E_THUMBS_UP_SIGN_CODEPOINT = 0x1F44D;    // 👍, 0x1F44D
    int E_THUMBS_DOWN_SIGN_CODEPOINT = 0x1F44E;    // 👎, 0x1F44E
    int E_KEYCAP_10_CODEPOINT = 0x1F51F;    // 🔟, 0x1F51F
    int E_HOT_PEPPER_CODEPOINT = 0x1F336;    // 🌶, 0x1F336
    int E_LOLLIPOP_CODEPOINT = 0x1F36D;    // 🍭, 0x1F36D
    int E_SOFT_ICE_CREAM_CODEPOINT = 0x1F366;    // 🍦, 0x1F366
    int E_CHIPMUNK_CODEPOINT = 0x1F43F;    // 🐿, 0x1F43F
    int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR_CODEPOINT = 0x23ED;    // ⏭, 0x23ED
    int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_CODEPOINT = 0x23E9;    // ⏩, 0x23E9
    int E_WARNING_SIGN_CODEPOINT = 0x26A0;    // ⚠, 0x26A0
    int E_SCROLL_CODEPOINT = 0x1F4DC;    // 📜, 0x1F4DC
    int E_EARTH_GLOBE_EUROPE_AFRICA_CODEPOINT = 0x1F30D;    // 🌍, 0x1F30D
    int E_NO_ENTRY_SIGN_CODEPOINT = 0x1F6AB;    // 🚫, 0x1F6AB
    int E_HEAVY_LARGE_CIRCLE_CODEPOINT = 0x2B55;    // ⭕, 0x2B55
    int E_ANGER_SYMBOL_CODEPOINT = 0x1F4A2;    // 💢, 0x1F4A2
    int E_DANGO_CODEPOINT = 0x1F361;    // 🍡, 0x1F361
    int E_DOWN_POINTING_SMALL_RED_TRIANGLE_CODEPOINT = 0x1F53D;    // 🔽, 0x1F53D
    int E_DOVE_OF_PEACE_CODEPOINT = 0x1F54A;    // 🕊, 0x1F54A
    int E_WHITE_LARGE_SQUARE_CODEPOINT = 0x2B1C;    // ⬜, 0x2B1C
    int E_WHITE_SQUARE_BUTTON_CODEPOINT = 0x1F533;    // 🔳, 0x1F533
    int E_BLACK_LARGE_SQUARE_CODEPOINT = 0x2B1B;    // ⬛, 0x2B1B
    int E_LEFT_POINTING_BACKHAND_INDEX_CODEPOINT = 0x1F448;    // 👈, 0x1F448
    int E_RIGHT_POINTING_BACKHAND_INDEX_CODEPOINT = 0x1F449;    // 👉, 0x1F449
    int E_RED_QUESTION_MARK_CODEPOINT = 0x2753;    // ❓, 0x2753
    int E_BABY_BOTTLE_CODEPOINT = 0x1F37C;    // 🍼, 0x1F37C
    int E_WHITE_CIRCLE_CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    int E_CRAYON_CODEPOINT = 0x1F58D;    // 🖍, 0x1F58D
    int E_RADIO_BUTTON_CODEPOINT = 0x1F518;    // 🔘, 0x1F518
    int E_BENTO_BOX_CODEPOINT = 0x1F371;    // 🍱, 0x1F371
    int E_POLICE_CARS_LIGHT_CODEPOINT = 0x1F6A8;    // 🚨, 0x1F6A8
    int E_METRO_CODEPOINT = 0x1F687;    // 🚇, 0x1F687
    int E_TRAFFIC_LIGHT_CODEPOINT = 0x1F6A5;    // 🚥, 0x1F6A5
    int E_AVOCADO_CODEPOINT = 0x1F951;    // 🥑, 0x1F951
    int E_TRIANGLE_POINTED_DOWN_CODEPOINT = 0x1F53B;    // 🔻, 0x1F53B
    int E_RED_EXCLAMATION_MARK_CODEPOINT = 0x2757;    // ❗, 0x2757
    int E_WHITE_EXCLAMATION_MARK_CODEPOINT = 0x2755;    // ❕, 0x2755
    int E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK_CODEPOINT = 0x2049;    // ⁉, 0x2049
    int E_RIGHT_FACING_FIST_CODEPOINT = 0x1F91C;    // 🤜, 0x1F91C
    int E_LEFT_FACING_FIST_CODEPOINT = 0x1F91B;    // 🤛, 0x1F91B
    int E_NEW_SIGN_CODEPOINT = 0x1F195;    // 🆕, 0x1F195
    
    static String[] EMOJI_ARRAY = {
        "🏠", "🍇", "🍬", "🔶", "↩", "🍰", "🍮", "🍌", "🍪", "🍨", "🍯", "🍆", "🍊",
        "🍋", "🍓", "🍉", "🔁", "🔂", "🐕", "🔷", "⚡", "☁", "🐚", "📻", "➡", "🐊",
        "🐖", "🏁", "🦃", "🐋", "🔮", "🐇", "🔏", "🔓", "🔒", "🔐", "✒", "🔑", "📦",
        "😜", "🐐", "🔲", "🍺", "🍩", "❎", "👐", "🤝", "🙌", "🚮", "🚂", "✨", "👌",
        "🔣", "🚀", "⚪", "⚫", "🔵", "➖", "➕", "➗", "✖", "◀", "▶", "⬅", "👵",
        "🌮", "🔤", "❌", "👴", "👍", "👎", "🔟", "🌶", "🍭", "🍦", "🐿", "⏭", "⏩",
        "⚠", "📜", "🌍", "🚫", "⭕", "💢", "🍡", "🔽", "🕊", "⬜", "🔳", "⬛", "👈",
        "👉", "❓", "🍼", "⚪", "🖍", "🔘", "🍱", "🚨", "🚇", "🚥", "🥑", "🔻", "❗",
        "❕", "⁉", "🤜", "🤛", "🆕"
    };
    
    static final Integer[] EMOJI_WHITESPACES_ARRAY = {
        0x9,
        0xA,
        0xB,
        0xC,
        0xD,
        0x20,
        0x85,
        0xA0,
        0x1680,
        0x2000,
        0x2001,
        0x2002,
        0x2003,
        0x2004,
        0x2005,
        0x2006,
        0x2007,
        0x2008,
        0x2009,
        0x200A,
        0x2028,
        0x2029,
        0x202F,
        0x205F,
        0x3000,
        0xFE0F
    };
    
    final List<Integer> EMOJI_WHITESPACES_LIST = Arrays.asList(EMOJI_WHITESPACES_ARRAY);
    
    /**
     * Return a string containing of the unicode character. This method is used
     * since a single unicode character may consist of two java characters
     * @param codepoint the codepoint to convert to a unicode character
     * @return a string with a unicode character. This string may contain two java characters.
     */
    static String codepointToChars(int codepoint) {
        return new String(Character.toChars(codepoint));
    }
    
    
    /**
     * Checks if a codepoint is a white space
     * @param codePoint the codepoint to check
     * @return true if the codepoint is a white space
     */
    static boolean isWhitespace(int codePoint) {
        return EMOJI_WHITESPACES_LIST.contains(codePoint);
    }
}
