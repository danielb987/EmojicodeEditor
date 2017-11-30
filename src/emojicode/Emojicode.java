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
    static final String E_HOUSE_BUILDING = "🏠";    // 0x1F3E0
    static final String E_GRAPES = "🍇";    // 0x1F347
    static final String E_CANDY = "🍬";    // 0x1F36C
    static final String E_ORANGE_TRIANGLE = "🔶";    // 0x1F536
    static final String E_RIGHT_ARROW_CURVING_LEFT = "↩";    // 0x21A9
    static final String E_SHORTCAKE = "🍰";    // 0x1F370
    static final String E_CUSTARD = "🍮";    // 0x1F36E
    static final String E_BANANA = "🍌";    // 0x1F34C
    static final String E_COOKIE = "🍪";    // 0x1F36A
    static final String E_ICE_CREAM = "🍨";    // 0x1F368
    static final String E_HONEY_POT = "🍯";    // 0x1F36F
    static final String E_AUBERGINE = "🍆";    // 0x1F346
    static final String E_TANGERINE = "🍊";    // 0x1F34A
    static final String E_LEMON = "🍋";    // 0x1F34B
    static final String E_STRAWBERRY = "🍓";    // 0x1F353
    static final String E_WATERMELON = "🍉";    // 0x1F349
    static final String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS = "🔁";    // 0x1F501
    static final String E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY = "🔂";    // 0x1F502
    static final String E_DOG = "🐕";    // 0x1F415
    static final String E_LARGE_BLUE_DIAMOND = "🔷";    // 0x1F537
    static final String E_HIGH_VOLTAGE_SIGN = "⚡";    // 0x26A1
    static final String E_CLOUD = "☁";    // 0x2601
    static final String E_SPIRAL_SHELL = "🐚";    // 0x1F41A
    static final String E_RADIO = "📻";    // 0x1F4FB
    static final String E_RIGHTWARDS_ARROW = "➡";    // 0x27A1
    static final String E_CROCODILE = "🐊";    // 0x1F40A
    static final String E_PIG = "🐖";    // 0x1F416
    static final String E_CHEQUERED_FLAG = "🏁";    // 0x1F3C1
    static final String E_TURKEY = "🦃";    // 0x1F983
    static final String E_WALE = "🐋";    // 0x1F40B
    static final String E_CRYSTAL_BALL = "🔮";    // 0x1F52E
    static final String E_RABBIT = "🐇";    // 0x1F407
    static final String E_LOCK_WITH_INK_PEN = "🔏";    // 0x1F50F
    static final String E_OPEN_LOCK = "🔓";    // 0x1F513
    static final String E_LOCK = "🔒";    // 0x1F512
    static final String E_CLOSED_LOCK_WITH_KEY = "🔐";    // 0x1F510
    static final String E_BLACK_NIB = "✒";    // 0x2712
    static final String E_KEY = "🔑";    // 0x1F511
    static final String E_PACKAGE = "📦";    // 0x1F4E6
    static final String E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE = "😜";    // 0x1F61C
    static final String E_GOAT = "🐐";    // 0x1F410
    static final String E_BLACK_SQUARE_BUTTON = "🔲";    // 0x1F532
    static final String E_BEER_MUG = "🍺";    // 0x1F37A
    static final String E_DOUGHNUT = "🍩";    // 0x1F369
    static final String E_NEGATIVE_SQUARED_CROSS_MARK = "❎";    // 0x274E
    static final String E_OPEN_HANDS = "👐";    // 0x1F450
    static final String E_HANDSHAKE = "🤝";    // 0x1F91D
    static final String E_HANDS_RAISED_IN_CELEBRATION = "🙌";    // 0x1F64C
    static final String E_PUT_LITTER_IN_ITS_SPACE = "🚮";    // 0x1F6AE
    static final String E_STEAM_LOCOMOTIVE = "🚂";    // 0x1F682
    static final String E_SPARKLES = "✨";    // 0x2728
    static final String E_OK_HAND_SIGN = "👌";    // 0x1F44C
    static final String E_INPUT_SYMBOL_FOR_SYMBOLS = "🔣";    // 0x1F523
    static final String E_ROCKET = "🚀";    // 0x1F680
    static final String E_MEDIUM_WHITE_CIRCLE = "⚪";    // 0x26AA
    static final String E_MEDIUM_BLACK_CIRCLE = "⚫";    // 0x26AB
    static final String E_LARGE_BLUE_CIRCLE = "🔵";    // 0x1F535
    static final String E_HEAVY_MINUS_SIGN = "➖";    // 0x2796
    static final String E_HEAVY_PLUS_SIGN = "➕";    // 0x2795
    static final String E_HEAVY_DIVISION_SIGN = "➗";    // 0x2797
    static final String E_HEAVY_MULTIPLICATION_SIGN = "✖";    // 0x2716
    static final String E_LEFT_POINTING_TRIANGLE = "◀";    // 0x25C0
    static final String E_RIGHT_POINTING_TRIANGLE = "▶";    // 0x25B6
    static final String E_LEFTWARDS_ARROW = "⬅";    // 0x2B05
    static final String E_OLDER_WOMAN = "👵";    // 0x1F475
    static final String E_TACO = "🌮";    // 0x1F32E
    static final String E_INPUT_SYMBOL_LATIN_LETTERS = "🔤";    // 0x1F524
    static final String E_CROSS_MARK = "❌";    // 0x274C
    static final String E_OLDER_MAN = "👴";    // 0x1F474
    static final String E_THUMBS_UP_SIGN = "👍";    // 0x1F44D
    static final String E_THUMBS_DOWN_SIGN = "👎";    // 0x1F44E
    static final String E_KEYCAP_10 = "🔟";    // 0x1F51F
    static final String E_HOT_PEPPER = "🌶";    // 0x1F336
    static final String E_LOLLIPOP = "🍭";    // 0x1F36D
    static final String E_SOFT_ICE_CREAM = "🍦";    // 0x1F366
    static final String E_CHIPMUNK = "🐿";    // 0x1F43F
    static final String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR = "⏭";    // 0x23ED
    static final String E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE = "⏩";    // 0x23E9
    static final String E_WARNING_SIGN = "⚠";    // 0x26A0
    static final String E_SCROLL = "📜";    // 0x1F4DC
    static final String E_EARTH_GLOBE_EUROPE_AFRICA = "🌍";    // 0x1F30D
    static final String E_NO_ENTRY_SIGN = "🚫";    // 0x1F6AB
    static final String E_HEAVY_LARGE_CIRCLE = "⭕";    // 0x2B55
    static final String E_ANGER_SYMBOL = "💢";    // 0x1F4A2
    static final String E_DANGO = "🍡";    // 0x1F361
    static final String E_DOWN_POINTING_SMALL_RED_TRIANGLE = "🔽";    // 0x1F53D
    static final String E_DOVE_OF_PEACE = "🕊";    // 0x1F54A
    static final String E_WHITE_LARGE_SQUARE = "⬜";    // 0x2B1C
    static final String E_WHITE_SQUARE_BUTTON = "🔳";    // 0x1F533
    static final String E_BLACK_LARGE_SQUARE = "⬛";    // 0x2B1B
    static final String E_LEFT_POINTING_BACKHAND_INDEX = "👈";    // 0x1F448
    static final String E_RIGHT_POINTING_BACKHAND_INDEX = "👉";    // 0x1F449
    static final String E_RED_QUESTION_MARK = "❓";    // 0x2753
    static final String E_BABY_BOTTLE = "🍼";    // 0x1F37C
    static final String E_WHITE_CIRCLE = "⚪";    // 0x26AA
    static final String E_CRAYON = "🖍";    // 0x1F58D
    static final String E_RADIO_BUTTON = "🔘";    // 0x1F518
    static final String E_BENTO_BOX = "🍱";    // 0x1F371
    static final String E_POLICE_CARS_LIGHT = "🚨";    // 0x1F6A8
    static final String E_METRO = "🚇";    // 0x1F687
    static final String E_TRAFFIC_LIGHT = "🚥";    // 0x1F6A5
    static final String E_AVOCADO = "🥑";    // 0x1F951
    static final String E_TRIANGLE_POINTED_DOWN = "🔻";    // 0x1F53B
    static final String E_RED_EXCLAMATION_MARK = "❗";    // 0x2757
    static final String E_WHITE_EXCLAMATION_MARK = "❕";    // 0x2755
    static final String E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK = "⁉";    // 0x2049
    static final String E_RIGHT_FACING_FIST = "🤜";    // 0x1F91C
    static final String E_LEFT_FACING_FIST = "🤛";    // 0x1F91B
    static final String E_NEW_SIGN = "🆕";    // 0x1F195
    
    static final int E_HOUSE_BUILDING_CODEPOINT = 0x1F3E0;    // 🏠, 0x1F3E0
    static final int E_GRAPES_CODEPOINT = 0x1F347;    // 🍇, 0x1F347
    static final int E_CANDY_CODEPOINT = 0x1F36C;    // 🍬, 0x1F36C
    static final int E_ORANGE_TRIANGLE_CODEPOINT = 0x1F536;    // 🔶, 0x1F536
    static final int E_RIGHT_ARROW_CURVING_LEFT_CODEPOINT = 0x21A9;    // ↩, 0x21A9
    static final int E_SHORTCAKE_CODEPOINT = 0x1F370;    // 🍰, 0x1F370
    static final int E_CUSTARD_CODEPOINT = 0x1F36E;    // 🍮, 0x1F36E
    static final int E_BANANA_CODEPOINT = 0x1F34C;    // 🍌, 0x1F34C
    static final int E_COOKIE_CODEPOINT = 0x1F36A;    // 🍪, 0x1F36A
    static final int E_ICE_CREAM_CODEPOINT = 0x1F368;    // 🍨, 0x1F368
    static final int E_HONEY_POT_CODEPOINT = 0x1F36F;    // 🍯, 0x1F36F
    static final int E_AUBERGINE_CODEPOINT = 0x1F346;    // 🍆, 0x1F346
    static final int E_TANGERINE_CODEPOINT = 0x1F34A;    // 🍊, 0x1F34A
    static final int E_LEMON_CODEPOINT = 0x1F34B;    // 🍋, 0x1F34B
    static final int E_STRAWBERRY_CODEPOINT = 0x1F353;    // 🍓, 0x1F353
    static final int E_WATERMELON_CODEPOINT = 0x1F349;    // 🍉, 0x1F349
    static final int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_CODEPOINT = 0x1F501;    // 🔁, 0x1F501
    static final int E_CLOCKWISE_RIGHTWARDS_AND_LEFTWARDS_OPEN_CIRCLE_ARROWS_WITH_CIRCLED_ONE_OVERLAY_CODEPOINT = 0x1F502;    // 🔂, 0x1F502
    static final int E_DOG_CODEPOINT = 0x1F415;    // 🐕, 0x1F415
    static final int E_LARGE_BLUE_DIAMOND_CODEPOINT = 0x1F537;    // 🔷, 0x1F537
    static final int E_HIGH_VOLTAGE_SIGN_CODEPOINT = 0x26A1;    // ⚡, 0x26A1
    static final int E_CLOUD_CODEPOINT = 0x2601;    // ☁, 0x2601
    static final int E_SPIRAL_SHELL_CODEPOINT = 0x1F41A;    // 🐚, 0x1F41A
    static final int E_RADIO_CODEPOINT = 0x1F4FB;    // 📻, 0x1F4FB
    static final int E_RIGHTWARDS_ARROW_CODEPOINT = 0x27A1;    // ➡, 0x27A1
    static final int E_CROCODILE_CODEPOINT = 0x1F40A;    // 🐊, 0x1F40A
    static final int E_PIG_CODEPOINT = 0x1F416;    // 🐖, 0x1F416
    static final int E_CHEQUERED_FLAG_CODEPOINT = 0x1F3C1;    // 🏁, 0x1F3C1
    static final int E_TURKEY_CODEPOINT = 0x1F983;    // 🦃, 0x1F983
    static final int E_WALE_CODEPOINT = 0x1F40B;    // 🐋, 0x1F40B
    static final int E_CRYSTAL_BALL_CODEPOINT = 0x1F52E;    // 🔮, 0x1F52E
    static final int E_RABBIT_CODEPOINT = 0x1F407;    // 🐇, 0x1F407
    static final int E_LOCK_WITH_INK_PEN_CODEPOINT = 0x1F50F;    // 🔏, 0x1F50F
    static final int E_OPEN_LOCK_CODEPOINT = 0x1F513;    // 🔓, 0x1F513
    static final int E_LOCK_CODEPOINT = 0x1F512;    // 🔒, 0x1F512
    static final int E_CLOSED_LOCK_WITH_KEY_CODEPOINT = 0x1F510;    // 🔐, 0x1F510
    static final int E_BLACK_NIB_CODEPOINT = 0x2712;    // ✒, 0x2712
    static final int E_KEY_CODEPOINT = 0x1F511;    // 🔑, 0x1F511
    static final int E_PACKAGE_CODEPOINT = 0x1F4E6;    // 📦, 0x1F4E6
    static final int E_FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE_CODEPOINT = 0x1F61C;    // 😜, 0x1F61C
    static final int E_GOAT_CODEPOINT = 0x1F410;    // 🐐, 0x1F410
    static final int E_BLACK_SQUARE_BUTTON_CODEPOINT = 0x1F532;    // 🔲, 0x1F532
    static final int E_BEER_MUG_CODEPOINT = 0x1F37A;    // 🍺, 0x1F37A
    static final int E_DOUGHNUT_CODEPOINT = 0x1F369;    // 🍩, 0x1F369
    static final int E_NEGATIVE_SQUARED_CROSS_MARK_CODEPOINT = 0x274E;    // ❎, 0x274E
    static final int E_OPEN_HANDS_CODEPOINT = 0x1F450;    // 👐, 0x1F450
    static final int E_HANDSHAKE_CODEPOINT = 0x1F91D;    // 🤝, 0x1F91D
    static final int E_HANDS_RAISED_IN_CELEBRATION_CODEPOINT = 0x1F64C;    // 🙌, 0x1F64C
    static final int E_PUT_LITTER_IN_ITS_SPACE_CODEPOINT = 0x1F6AE;    // 🚮, 0x1F6AE
    static final int E_STEAM_LOCOMOTIVE_CODEPOINT = 0x1F682;    // 🚂, 0x1F682
    static final int E_SPARKLES_CODEPOINT = 0x2728;    // ✨, 0x2728
    static final int E_OK_HAND_SIGN_CODEPOINT = 0x1F44C;    // 👌, 0x1F44C
    static final int E_INPUT_SYMBOL_FOR_SYMBOLS_CODEPOINT = 0x1F523;    // 🔣, 0x1F523
    static final int E_ROCKET_CODEPOINT = 0x1F680;    // 🚀, 0x1F680
    static final int E_MEDIUM_WHITE_CIRCLE_CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    static final int E_MEDIUM_BLACK_CIRCLE_CODEPOINT = 0x26AB;    // ⚫, 0x26AB
    static final int E_LARGE_BLUE_CIRCLE_CODEPOINT = 0x1F535;    // 🔵, 0x1F535
    static final int E_HEAVY_MINUS_SIGN_CODEPOINT = 0x2796;    // ➖, 0x2796
    static final int E_HEAVY_PLUS_SIGN_CODEPOINT = 0x2795;    // ➕, 0x2795
    static final int E_HEAVY_DIVISION_SIGN_CODEPOINT = 0x2797;    // ➗, 0x2797
    static final int E_HEAVY_MULTIPLICATION_SIGN_CODEPOINT = 0x2716;    // ✖, 0x2716
    static final int E_LEFT_POINTING_TRIANGLE_CODEPOINT = 0x25C0;    // ◀, 0x25C0
    static final int E_RIGHT_POINTING_TRIANGLE_CODEPOINT = 0x25B6;    // ▶, 0x25B6
    static final int E_LEFTWARDS_ARROW_CODEPOINT = 0x2B05;    // ⬅, 0x2B05
    static final int E_OLDER_WOMAN_CODEPOINT = 0x1F475;    // 👵, 0x1F475
    static final int E_TACO_CODEPOINT = 0x1F32E;    // 🌮, 0x1F32E
    static final int E_INPUT_SYMBOL_LATIN_LETTERS_CODEPOINT = 0x1F524;    // 🔤, 0x1F524
    static final int E_CROSS_MARK_CODEPOINT = 0x274C;    // ❌, 0x274C
    static final int E_OLDER_MAN_CODEPOINT = 0x1F474;    // 👴, 0x1F474
    static final int E_THUMBS_UP_SIGN_CODEPOINT = 0x1F44D;    // 👍, 0x1F44D
    static final int E_THUMBS_DOWN_SIGN_CODEPOINT = 0x1F44E;    // 👎, 0x1F44E
    static final int E_KEYCAP_10_CODEPOINT = 0x1F51F;    // 🔟, 0x1F51F
    static final int E_HOT_PEPPER_CODEPOINT = 0x1F336;    // 🌶, 0x1F336
    static final int E_LOLLIPOP_CODEPOINT = 0x1F36D;    // 🍭, 0x1F36D
    static final int E_SOFT_ICE_CREAM_CODEPOINT = 0x1F366;    // 🍦, 0x1F366
    static final int E_CHIPMUNK_CODEPOINT = 0x1F43F;    // 🐿, 0x1F43F
    static final int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_WITH_VERTICAL_BAR_CODEPOINT = 0x23ED;    // ⏭, 0x23ED
    static final int E_BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE_CODEPOINT = 0x23E9;    // ⏩, 0x23E9
    static final int E_WARNING_SIGN_CODEPOINT = 0x26A0;    // ⚠, 0x26A0
    static final int E_SCROLL_CODEPOINT = 0x1F4DC;    // 📜, 0x1F4DC
    static final int E_EARTH_GLOBE_EUROPE_AFRICA_CODEPOINT = 0x1F30D;    // 🌍, 0x1F30D
    static final int E_NO_ENTRY_SIGN_CODEPOINT = 0x1F6AB;    // 🚫, 0x1F6AB
    static final int E_HEAVY_LARGE_CIRCLE_CODEPOINT = 0x2B55;    // ⭕, 0x2B55
    static final int E_ANGER_SYMBOL_CODEPOINT = 0x1F4A2;    // 💢, 0x1F4A2
    static final int E_DANGO_CODEPOINT = 0x1F361;    // 🍡, 0x1F361
    static final int E_DOWN_POINTING_SMALL_RED_TRIANGLE_CODEPOINT = 0x1F53D;    // 🔽, 0x1F53D
    static final int E_DOVE_OF_PEACE_CODEPOINT = 0x1F54A;    // 🕊, 0x1F54A
    static final int E_WHITE_LARGE_SQUARE_CODEPOINT = 0x2B1C;    // ⬜, 0x2B1C
    static final int E_WHITE_SQUARE_BUTTON_CODEPOINT = 0x1F533;    // 🔳, 0x1F533
    static final int E_BLACK_LARGE_SQUARE_CODEPOINT = 0x2B1B;    // ⬛, 0x2B1B
    static final int E_LEFT_POINTING_BACKHAND_INDEX_CODEPOINT = 0x1F448;    // 👈, 0x1F448
    static final int E_RIGHT_POINTING_BACKHAND_INDEX_CODEPOINT = 0x1F449;    // 👉, 0x1F449
    static final int E_RED_QUESTION_MARK_CODEPOINT = 0x2753;    // ❓, 0x2753
    static final int E_BABY_BOTTLE_CODEPOINT = 0x1F37C;    // 🍼, 0x1F37C
    static final int E_WHITE_CIRCLE_CODEPOINT = 0x26AA;    // ⚪, 0x26AA
    static final int E_CRAYON_CODEPOINT = 0x1F58D;    // 🖍, 0x1F58D
    static final int E_RADIO_BUTTON_CODEPOINT = 0x1F518;    // 🔘, 0x1F518
    static final int E_BENTO_BOX_CODEPOINT = 0x1F371;    // 🍱, 0x1F371
    static final int E_POLICE_CARS_LIGHT_CODEPOINT = 0x1F6A8;    // 🚨, 0x1F6A8
    static final int E_METRO_CODEPOINT = 0x1F687;    // 🚇, 0x1F687
    static final int E_TRAFFIC_LIGHT_CODEPOINT = 0x1F6A5;    // 🚥, 0x1F6A5
    static final int E_AVOCADO_CODEPOINT = 0x1F951;    // 🥑, 0x1F951
    static final int E_TRIANGLE_POINTED_DOWN_CODEPOINT = 0x1F53B;    // 🔻, 0x1F53B
    static final int E_RED_EXCLAMATION_MARK_CODEPOINT = 0x2757;    // ❗, 0x2757
    static final int E_WHITE_EXCLAMATION_MARK_CODEPOINT = 0x2755;    // ❕, 0x2755
    static final int E_RED_EXCLAMATION_MARK_AND_QUESTION_MARK_CODEPOINT = 0x2049;    // ⁉, 0x2049
    static final int E_RIGHT_FACING_FIST_CODEPOINT = 0x1F91C;    // 🤜, 0x1F91C
    static final int E_LEFT_FACING_FIST_CODEPOINT = 0x1F91B;    // 🤛, 0x1F91B
    static final int E_NEW_SIGN_CODEPOINT = 0x1F195;    // 🆕, 0x1F195
    
    static final String[] EMOJI_ARRAY = {
        "🏠", "🍇", "🍬", "🔶", "↩", "🍰", "🍮", "🍌", "🍪", "🍨", "🍯", "🍆", "🍊", "🍋", "🍓",
        "🍉", "🔁", "🔂", "🐕", "🔷", "⚡", "☁", "🐚", "📻", "➡", "🐊", "🐖", "🏁", "🦃", "🐋",
        "🔮", "🐇", "🔏", "🔓", "🔒", "🔐", "✒", "🔑", "📦", "😜", "🐐", "🔲", "🍺", "🍩", "❎",
        "👐", "🤝", "🙌", "🚮", "🚂", "✨", "👌", "🔣", "🚀", "⚪", "⚫", "🔵", "➖", "➕", "➗",
        "✖", "◀", "▶", "⬅", "👵", "🌮", "🔤", "❌", "👴", "👍", "👎", "🔟", "🌶", "🍭", "🍦",
        "🐿", "⏭", "⏩", "⚠", "📜", "🌍", "🚫", "⭕", "💢", "🍡", "🔽", "🕊", "⬜", "🔳", "⬛",
        "👈", "👉", "❓", "🍼", "⚪", "🖍", "🔘", "🍱", "🚨", "🚇", "🚥", "🥑", "🔻", "❗", "❕",
        "⁉", "🤜", "🤛", "🆕"
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
    static boolean isWhitespace(int codePoint) {
        return EMOJI_WHITESPACES_LIST.contains(codePoint);
    }
}
