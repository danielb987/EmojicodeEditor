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
package emojicodeeditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Daniel Bergqvist
 */
public final class Emojis {
    
    private static final Emojis emojis = new Emojis();
    
    private final Map<String, Emoji> emojiMap = new HashMap<>();
    private final List<EmojiToolbarData> emojiToolbarDataList = new ArrayList<>();
    
    public static Emojis getInstance() {
        return emojis;
    }
    
    private Emojis() {
        
        EmojiToolbarData emojiToolbarData = null;
        // Create a set of actions to use in both the menu and toolbar
        for (String key : Bundle.getInstance().getKeys()) {
            if (key.startsWith("ToolbarGroup_")) {
                key = key.substring("ToolbarGroup_".length());
                emojiToolbarData = new EmojiToolbarData(key);
                emojiToolbarDataList.add(emojiToolbarData);
            } else {
                Emoji emoji = new Emoji(key, Bundle.getInstance().getMessage(key), Bundle.getInstance().getMessage(key));
                emojiMap.put(key, emoji);
                emojiToolbarData.emojis.add(emoji);
            }
        }
    }
    
    public EmojiToolbarData getNewToolbarData(String name) {
        EmojiToolbarData emojiToolbarData = new EmojiToolbarData(name);
        emojiToolbarDataList.add(emojiToolbarData);
        return emojiToolbarData;
    }
    
    public EmojiToolbarData getNewToolbarData(String name, String documentation, String htmlDocumentation) {
        EmojiToolbarData emojiToolbarData = new EmojiToolbarData(name, documentation, htmlDocumentation);
        emojiToolbarDataList.add(emojiToolbarData);
        return emojiToolbarData;
    }
    
    public String getEmojiDocumentation(String key) {
        Emoji emoji = emojiMap.get(key);
        if (emoji != null)
            return emoji.documentation;
        else
            return null;
    }
    
    public String getEmojiHTMLDocumentation(String key) {
        Emoji emoji = emojiMap.get(key);
        if (emoji != null)
            return emoji.htmlDocumentation;
        else
            return null;
    }
    
    public List<EmojiToolbarData> getEmojiToolbarDataList() {
        return new ArrayList<>(emojiToolbarDataList);
    }
    
    
    public static final class Emoji {
        public final String emoji;
        public final String documentation;
        public final String htmlDocumentation;
        
        public Emoji(String emoji, String documentation, String htmlDocumentation) {
            this.emoji = emoji;
            this.documentation = documentation;
            this.htmlDocumentation = htmlDocumentation;
        }
    }
    
    
    
}
