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
    public static final String E_HOUSE_BUILDING = "🏠";    // 0x1F3E0
    public static final String E_GRAPES = "🍇";    // 0x1F347
    public static final String E_CANDY = "🍬";    // 0x1F36C
    public static final String E_ORANGE_TRIANGLE = "🔶";    // 0x1F536
    public static final String E_RIGHT_ARROW_CURVING_LEFT = "↩";    // 0x21A9
    public static final String E_SHORTCAKE = "🍰";    // 0x1F370
    public static final String E_CUSTARD = "🍮";    // 0x1F36E
    public static final String E_BANANA = "🍌";    // 0x1F34C
    public static final String E_COOKIE = "🍪";    // 0x1F36A
    public static final String E_ICE_CREAM = "🍨";    // 0x1F368
    public static final String E_HONEY_POT = "🍯";    // 0x1F36F
    public static final String E_AUBERGINE = "🍆";    // 0x1F346
    public static final String E_TANGERINE = "🍊";    // 0x1F34A
    public static final String E_LEMON = "🍋";    // 0x1F34B
    public static final String E_STRAWBERRY = "🍓";    // 0x1F353
    public static final String E_WATERMELON = "🍉";    // 0x1F349
    public static final String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS = "🔁";    // 0x1F501
    public static final String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY = "🔂";    // 0x1F502
    public static final String E_DOG = "🐕";    // 0x1F415
    public static final String E_LARGE_BLUE_DIAMOND = "🔷";    // 0x1F537
    public static final String E_HIGH_VOLTAGE_SIGN = "⚡";    // 0x26A1
    public static final String E_CLOUD = "☁";    // 0x2601
    public static final String E_SPIRAL_SHELL = "🐚";    // 0x1F41A
    public static final String E_RADIO = "📻";    // 0x1F4FB
    public static final String E_RIGHTWARDS_ARROW = "➡";    // 0x27A1
    public static final String E_CROCODILE = "🐊";    // 0x1F40A
    public static final String E_PIG = "🐖";    // 0x1F416
    public static final String E_CHEQUERED_FLAG = "🏁";    // 0x1F3C1
    public static final String E_TURKEY = "🦃";    // 0x1F983
    public static final String E_WALE = "🐋";    // 0x1F40B
    public static final String E_CRYSTAL_BALL = "🔮";    // 0x1F52E
    public static final String E_RABBIT = "🐇";    // 0x1F407
    public static final String E_LOCK_WITH_INK_PEN = "🔏";    // 0x1F50F
    public static final String E_OPEN_LOCK = "🔓";    // 0x1F513
    public static final String E_LOCK = "🔒";    // 0x1F512
    public static final String E_CLOSED_LOCK_WITH_KEY = "🔐";    // 0x1F510
    public static final String E_BLACK_NIB = "✒";    // 0x2712
    public static final String E_KEY = "🔑";    // 0x1F511
    public static final String E_PACKAGE = "📦";    // 0x1F4E6
    public static final String E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE = "😜";    // 0x1F61C
    public static final String E_GOAT = "🐐";    // 0x1F410
    public static final String E_BLACK_SQUARE_BUTTON = "🔲";    // 0x1F532
    public static final String E_BEER_MUG = "🍺";    // 0x1F37A
    public static final String E_DOUGHNUT = "🍩";    // 0x1F369
    public static final String E_NEGATIVE_SQUARED_CROSS_MARK = "❎";    // 0x274E
    public static final String E_OPEN_HANDS = "👐";    // 0x1F450
    public static final String E_HANDSHAKE = "🤝";    // 0x1F91D
    public static final String E_HANDS_RAISED_IN_CELEBRATION = "🙌";    // 0x1F64C
    public static final String E_PUT_LITTER_IN_ITS_SPACE = "🚮";    // 0x1F6AE
    public static final String E_STEAM_LOCOMOTIVE = "🚂";    // 0x1F682
    public static final String E_SPARKLES = "✨";    // 0x2728
    public static final String E_OK_HAND_SIGN = "👌";    // 0x1F44C
    public static final String E_INPUT_SYMBOL_FOR_SYMBOLS = "🔣";    // 0x1F523
    public static final String E_ROCKET = "🚀";    // 0x1F680
    public static final String E_MEDIUM_WHITE_CIRCLE = "⚪";    // 0x26AA
    public static final String E_MEDIUM_BLACK_CIRCLE = "⚫";    // 0x26AB
    public static final String E_LARGE_BLUE_CIRCLE = "🔵";    // 0x1F535
    public static final String E_HEAVY_MINUS_SIGN = "➖";    // 0x2796
    public static final String E_HEAVY_PLUS_SIGN = "➕";    // 0x2795
    public static final String E_HEAVY_DIVISION_SIGN = "➗";    // 0x2797
    public static final String E_HEAVY_MULTIPLICATION_SIGN = "✖";    // 0x2716
    public static final String E_LEFT_POINTING_TRIANGLE = "◀";    // 0x25C0
    public static final String E_RIGHT_POINTING_TRIANGLE = "▶";    // 0x25B6
    public static final String E_LEFTWARDS_ARROW = "⬅";    // 0x2B05
    public static final String E_OLDER_WOMAN = "👵";    // 0x1F475
    public static final String E_TACO = "🌮";    // 0x1F32E
    public static final String E_INPUT_SYMBOL_LATIN_LETTERS = "🔤";    // 0x1F524
    public static final String E_CROSS_MARK = "❌";    // 0x274C
    public static final String E_OLDER_MAN = "👴";    // 0x1F474
    public static final String E_THUMBS_UP_SIGN = "👍";    // 0x1F44D
    public static final String E_THUMBS_DOWN_SIGN = "👎";    // 0x1F44E
    public static final String E_KEYCAP_10 = "🔟";    // 0x1F51F
    public static final String E_HOT_PEPPER = "🌶";    // 0x1F336
    public static final String E_LOLLIPOP = "🍭";    // 0x1F36D
    public static final String E_SOFT_ICE_CREAM = "🍦";    // 0x1F366
    public static final String E_CHIPMUNK = "🐿";    // 0x1F43F
    public static final String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR = "⏭";    // 0x23ED
    public static final String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE = "⏩";    // 0x23E9
    public static final String E_WARNING_SIGN = "⚠";    // 0x26A0
    public static final String E_SCROLL = "📜";    // 0x1F4DC
    public static final String E_EARTH_GLOBE_EUROPE_AFRICA = "🌍";    // 0x1F30D
    public static final String E_NO_ENTRY_SIGN = "🚫";    // 0x1F6AB
    public static final String E_HEAVY_LARGE_CIRCLE = "⭕";    // 0x2B55
    public static final String E_ANGER_SYMBOL = "💢";    // 0x1F4A2
    public static final String E_DANGO = "🍡";    // 0x1F361
    public static final String E_DOWN_POINTING_SMALL_RED_TRIANGLE = "🔽";    // 0x1F53D
    public static final String E_DOVE_OF_PEACE = "🕊";    // 0x1F54A
    public static final String E_WHITE_LARGE_SQUARE = "⬜";    // 0x2B1C
    public static final String E_WHITE_SQUARE_BUTTON = "🔳";    // 0x1F533
    public static final String E_BLACK_LARGE_SQUARE = "⬛";    // 0x2B1B
    public static final String E_LEFT_POINTING_BACKHAND_INDEX = "👈";    // 0x1F448
    public static final String E_RIGHT_POINTING_BACKHAND_INDEX = "👉";    // 0x1F449
    public static final String E_RED_QUESTION_MARK = "❓";    // 0x2753
    public static final String E_BABY_BOTTLE = "🍼";    // 0x1F37C
    public static final String E_WHITE_CIRCLE = "⚪";    // 0x26AA
    public static final String E_CRAYON = "🖍";    // 0x1F58D
    public static final String E_RADIO_BUTTON = "🔘";    // 0x1F518
    public static final String E_BENTO_BOX = "🍱";    // 0x1F371
    public static final String E_POLICE_CARS_LIGHT = "🚨";    // 0x1F6A8
    public static final String E_METRO = "🚇";    // 0x1F687
    public static final String E_TRAFFIC_LIGHT = "🚥";    // 0x1F6A5
    public static final String E_AVOCADO = "🥑";    // 0x1F951
    public static final String E_TRIANGLE_POINTED_DOWN = "🔻";    // 0x1F53B
    public static final String E_RED_EXCLAMATION_MARK = "❗";    // 0x2757
    public static final String E_WHITE_EXCLAMATION_MARK = "❕";    // 0x2755
    public static final String E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK = "⁉";    // 0x2049
    public static final String E_RIGHT_FACING_FIST = "🤜";    // 0x1F91C
    public static final String E_LEFT_FACING_FIST = "🤛";    // 0x1F91B
    public static final String E_NEW_SIGN = "🆕";    // 0x1F195
    
    public static final int E_HOUSE_BUILDING__CODEPOINT = 0x1F3E0;    // 🏠, 0x1F3E0
    public static final int E_GRAPES__CODEPOINT = 0x1F347;    // 🍇, 0x1F347
    public static final int E_CANDY__CODEPOINT = 0x1F36C;    // 🍬, 0x1F36C
    public static final int E_ORANGE_TRIANGLE__CODEPOINT = 0x1F536;    // 🔶, 0x1F536
    public static final int E_RIGHT_ARROW_CURVING_LEFT__CODEPOINT = 0x21A9;    // ↩, 0x21A9
    public static final int E_SHORTCAKE__CODEPOINT = 0x1F370;    // 🍰, 0x1F370
    public static final int E_CUSTARD__CODEPOINT = 0x1F36E;    // 🍮, 0x1F36E
    public static final int E_BANANA__CODEPOINT = 0x1F34C;    // 🍌, 0x1F34C
    public static final int E_COOKIE__CODEPOINT = 0x1F36A;    // 🍪, 0x1F36A
    public static final int E_ICE_CREAM__CODEPOINT = 0x1F368;    // 🍨, 0x1F368
    public static final int E_HONEY_POT__CODEPOINT = 0x1F36F;    // 🍯, 0x1F36F
    public static final int E_AUBERGINE__CODEPOINT = 0x1F346;    // 🍆, 0x1F346
    public static final int E_TANGERINE__CODEPOINT = 0x1F34A;    // 🍊, 0x1F34A
    public static final int E_LEMON__CODEPOINT = 0x1F34B;    // 🍋, 0x1F34B
    public static final int E_STRAWBERRY__CODEPOINT = 0x1F353;    // 🍓, 0x1F353
    public static final int E_WATERMELON__CODEPOINT = 0x1F349;    // 🍉, 0x1F349
    public static final int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS__CODEPOINT = 0x1F501;    // 🔁, 0x1F501
    public static final int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY__CODEPOINT = 0x1F502;    // 🔂, 0x1F502
    public static final int E_DOG__CODEPOINT = 0x1F415;    // 🐕, 0x1F415
    public static final int E_LARGE_BLUE_DIAMOND__CODEPOINT = 0x1F537;    // 🔷, 0x1F537
    public static final int E_HIGH_VOLTAGE_SIGN__CODEPOINT = 0x26A1;    // ⚡, 0x26A1
    public static final int E_CLOUD__CODEPOINT = 0x2601;    // ☁, 0x2601
    public static final int E_SPIRAL_SHELL__CODEPOINT = 0x1F41A;    // 🐚, 0x1F41A
    public static final int E_RADIO__CODEPOINT = 0x1F4FB;    // 📻, 0x1F4FB
    public static final int E_RIGHTWARDS_ARROW__CODEPOINT = 0x27A1;    // ➡, 0x27A1
    public static final int E_CROCODILE__CODEPOINT = 0x1F40A;    // 🐊, 0x1F40A
    public static final int E_PIG__CODEPOINT = 0x1F416;    // 🐖, 0x1F416
    public static final int E_CHEQUERED_FLAG__CODEPOINT = 0x1F3C1;    // 🏁, 0x1F3C1
    public static final int E_TURKEY__CODEPOINT = 0x1F983;    // 🦃, 0x1F983
    public static final int E_WALE__CODEPOINT = 0x1F40B;    // 🐋, 0x1F40B
    public static final int E_CRYSTAL_BALL__CODEPOINT = 0x1F52E;    // 🔮, 0x1F52E
    public static final int E_RABBIT__CODEPOINT = 0x1F407;    // 🐇, 0x1F407
    public static final int E_LOCK_WITH_INK_PEN__CODEPOINT = 0x1F50F;    // 🔏, 0x1F50F
    public static final int E_OPEN_LOCK__CODEPOINT = 0x1F513;    // 🔓, 0x1F513
    public static final int E_LOCK__CODEPOINT = 0x1F512;    // 🔒, 0x1F512
    public static final int E_CLOSED_LOCK_WITH_KEY__CODEPOINT = 0x1F510;    // 🔐, 0x1F510
    public static final int E_BLACK_NIB__CODEPOINT = 0x2712;    // ✒, 0x2712
    public static final int E_KEY__CODEPOINT = 0x1F511;    // 🔑, 0x1F511
    public static final int E_PACKAGE__CODEPOINT = 0x1F4E6;    // 📦, 0x1F4E6
    public static final int E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE__CODEPOINT = 0x1F61C;    // 😜, 0x1F61C
    public static final int E_GOAT__CODEPOINT = 0x1F410;    // 🐐, 0x1F410
    public static final int E_BLACK_SQUARE_BUTTON__CODEPOINT = 0x1F532;    // 🔲, 0x1F532
    public static final int E_BEER_MUG__CODEPOINT = 0x1F37A;    // 🍺, 0x1F37A
    public static final int E_DOUGHNUT__CODEPOINT = 0x1F369;    // 🍩, 0x1F369
    public static final int E_NEGATIVE_SQUARED_CROSS_MARK__CODEPOINT = 0x274E;    // ❎, 0x274E
    public static final int E_OPEN_HANDS__CODEPOINT = 0x1F450;    // 👐, 0x1F450
    public static final int E_HANDSHAKE__CODEPOINT = 0x1F91D;    // 🤝, 0x1F91D
    public static final int E_HANDS_RAISED_IN_CELEBRATION__CODEPOINT = 0x1F64C;    // 🙌, 0x1F64C
    public static final int E_PUT_LITTER_IN_ITS_SPACE__CODEPOINT = 0x1F6AE;    // 🚮, 0x1F6AE
    public static final int E_STEAM_LOCOMOTIVE__CODEPOINT = 0x1F682;    // 🚂, 0x1F682
    public static final int E_SPARKLES__CODEPOINT = 0x2728;    // ✨, 0x2728
    public static final int E_OK_HAND_SIGN__CODEPOINT = 0x1F44C;    // 👌, 0x1F44C
    public static final int E_INPUT_SYMBOL_FOR_SYMBOLS__CODEPOINT = 0x1F523;    // 🔣, 0x1F523
    public static final int E_ROCKET__CODEPOINT = 0x1F680;    // 🚀, 0x1F680
    public static final int E_MEDIUM_WHITE_CIRCLE__CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    public static final int E_MEDIUM_BLACK_CIRCLE__CODEPOINT = 0x26AB;    // ⚫, 0x26AB
    public static final int E_LARGE_BLUE_CIRCLE__CODEPOINT = 0x1F535;    // 🔵, 0x1F535
    public static final int E_HEAVY_MINUS_SIGN__CODEPOINT = 0x2796;    // ➖, 0x2796
    public static final int E_HEAVY_PLUS_SIGN__CODEPOINT = 0x2795;    // ➕, 0x2795
    public static final int E_HEAVY_DIVISION_SIGN__CODEPOINT = 0x2797;    // ➗, 0x2797
    public static final int E_HEAVY_MULTIPLICATION_SIGN__CODEPOINT = 0x2716;    // ✖, 0x2716
    public static final int E_LEFT_POINTING_TRIANGLE__CODEPOINT = 0x25C0;    // ◀, 0x25C0
    public static final int E_RIGHT_POINTING_TRIANGLE__CODEPOINT = 0x25B6;    // ▶, 0x25B6
    public static final int E_LEFTWARDS_ARROW__CODEPOINT = 0x2B05;    // ⬅, 0x2B05
    public static final int E_OLDER_WOMAN__CODEPOINT = 0x1F475;    // 👵, 0x1F475
    public static final int E_TACO__CODEPOINT = 0x1F32E;    // 🌮, 0x1F32E
    public static final int E_INPUT_SYMBOL_LATIN_LETTERS__CODEPOINT = 0x1F524;    // 🔤, 0x1F524
    public static final int E_CROSS_MARK__CODEPOINT = 0x274C;    // ❌, 0x274C
    public static final int E_OLDER_MAN__CODEPOINT = 0x1F474;    // 👴, 0x1F474
    public static final int E_THUMBS_UP_SIGN__CODEPOINT = 0x1F44D;    // 👍, 0x1F44D
    public static final int E_THUMBS_DOWN_SIGN__CODEPOINT = 0x1F44E;    // 👎, 0x1F44E
    public static final int E_KEYCAP_10__CODEPOINT = 0x1F51F;    // 🔟, 0x1F51F
    public static final int E_HOT_PEPPER__CODEPOINT = 0x1F336;    // 🌶, 0x1F336
    public static final int E_LOLLIPOP__CODEPOINT = 0x1F36D;    // 🍭, 0x1F36D
    public static final int E_SOFT_ICE_CREAM__CODEPOINT = 0x1F366;    // 🍦, 0x1F366
    public static final int E_CHIPMUNK__CODEPOINT = 0x1F43F;    // 🐿, 0x1F43F
    public static final int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR__CODEPOINT = 0x23ED;    // ⏭, 0x23ED
    public static final int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE__CODEPOINT = 0x23E9;    // ⏩, 0x23E9
    public static final int E_WARNING_SIGN__CODEPOINT = 0x26A0;    // ⚠, 0x26A0
    public static final int E_SCROLL__CODEPOINT = 0x1F4DC;    // 📜, 0x1F4DC
    public static final int E_EARTH_GLOBE_EUROPE_AFRICA__CODEPOINT = 0x1F30D;    // 🌍, 0x1F30D
    public static final int E_NO_ENTRY_SIGN__CODEPOINT = 0x1F6AB;    // 🚫, 0x1F6AB
    public static final int E_HEAVY_LARGE_CIRCLE__CODEPOINT = 0x2B55;    // ⭕, 0x2B55
    public static final int E_ANGER_SYMBOL__CODEPOINT = 0x1F4A2;    // 💢, 0x1F4A2
    public static final int E_DANGO__CODEPOINT = 0x1F361;    // 🍡, 0x1F361
    public static final int E_DOWN_POINTING_SMALL_RED_TRIANGLE__CODEPOINT = 0x1F53D;    // 🔽, 0x1F53D
    public static final int E_DOVE_OF_PEACE__CODEPOINT = 0x1F54A;    // 🕊, 0x1F54A
    public static final int E_WHITE_LARGE_SQUARE__CODEPOINT = 0x2B1C;    // ⬜, 0x2B1C
    public static final int E_WHITE_SQUARE_BUTTON__CODEPOINT = 0x1F533;    // 🔳, 0x1F533
    public static final int E_BLACK_LARGE_SQUARE__CODEPOINT = 0x2B1B;    // ⬛, 0x2B1B
    public static final int E_LEFT_POINTING_BACKHAND_INDEX__CODEPOINT = 0x1F448;    // 👈, 0x1F448
    public static final int E_RIGHT_POINTING_BACKHAND_INDEX__CODEPOINT = 0x1F449;    // 👉, 0x1F449
    public static final int E_RED_QUESTION_MARK__CODEPOINT = 0x2753;    // ❓, 0x2753
    public static final int E_BABY_BOTTLE__CODEPOINT = 0x1F37C;    // 🍼, 0x1F37C
    public static final int E_WHITE_CIRCLE__CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    public static final int E_CRAYON__CODEPOINT = 0x1F58D;    // 🖍, 0x1F58D
    public static final int E_RADIO_BUTTON__CODEPOINT = 0x1F518;    // 🔘, 0x1F518
    public static final int E_BENTO_BOX__CODEPOINT = 0x1F371;    // 🍱, 0x1F371
    public static final int E_POLICE_CARS_LIGHT__CODEPOINT = 0x1F6A8;    // 🚨, 0x1F6A8
    public static final int E_METRO__CODEPOINT = 0x1F687;    // 🚇, 0x1F687
    public static final int E_TRAFFIC_LIGHT__CODEPOINT = 0x1F6A5;    // 🚥, 0x1F6A5
    public static final int E_AVOCADO__CODEPOINT = 0x1F951;    // 🥑, 0x1F951
    public static final int E_TRIANGLE_POINTED_DOWN__CODEPOINT = 0x1F53B;    // 🔻, 0x1F53B
    public static final int E_RED_EXCLAMATION_MARK__CODEPOINT = 0x2757;    // ❗, 0x2757
    public static final int E_WHITE_EXCLAMATION_MARK__CODEPOINT = 0x2755;    // ❕, 0x2755
    public static final int E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK__CODEPOINT = 0x2049;    // ⁉, 0x2049
    public static final int E_RIGHT_FACING_FIST__CODEPOINT = 0x1F91C;    // 🤜, 0x1F91C
    public static final int E_LEFT_FACING_FIST__CODEPOINT = 0x1F91B;    // 🤛, 0x1F91B
    public static final int E_NEW_SIGN__CODEPOINT = 0x1F195;    // 🆕, 0x1F195
    
    public static final String[] EMOJI_ARRAY = {
        "🏠","🍇","🍬","🔶","↩","🍰","🍮","🍌","🍪","🍨","🍯","🍆","🍊","🍋","🍓",
        "🍉","🔁","🔂","🐕","🔷","⚡","☁","🐚","📻","➡","🐊","🐖","🏁","🦃","🐋",
        "🔮","🐇","🔏","🔓","🔒","🔐","✒","🔑","📦","😜","🐐","🔲","🍺","🍩","❎",
        "👐","🤝","🙌","🚮","🚂","✨","👌","🔣","🚀","⚪","⚫","🔵","➖","➕","➗",
        "✖","◀","▶","⬅","👵","🌮","🔤","❌","👴","👍","👎","🔟","🌶","🍭","🍦",
        "🐿","⏭","⏩","⚠","📜","🌍","🚫","⭕","💢","🍡","🔽","🕊","⬜","🔳","⬛",
        "👈","👉","❓","🍼","⚪","🖍","🔘","🍱","🚨","🚇","🚥","🥑","🔻","❗","❕",
        "⁉","🤜","🤛","🆕"
    };
    
    public static final Integer[] EMOJI_WHITESPACES_ARRAY = {
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
    
    static final List<Integer> EMOJI_WHITESPACES_LIST = Arrays.asList(EMOJI_WHITESPACES_ARRAY);
    
    /**
     * Return a string containing of the unicode character. This method is used
     * since a single unicode character may consist of two java characters
     * @param codepoint the codepoint to convert to a unicode character
     * @return a string with a unicode character. This string may contain two java characters.
     */
    static String codepointToChars(int codepoint)
    {
        return new String(Character.toChars(codepoint));
    }
    
    
    /**
     * Checks if a codepoint is a white space
     * @param codePoint the codepoint to check
     * @return true if the codepoint is a white space
     */
    public static boolean isWhitespace(int codePoint) {
        return EMOJI_WHITESPACES_LIST.contains(codePoint);
    }
}
