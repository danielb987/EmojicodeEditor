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
import java.util.List;
import javax.swing.JToolBar;

/**
 *
 * @author daniel
 */
public final class EmojiToolbarData {
    
    private final String name;
    private final String documentation;
    private final String htmlDocumentation;
    final List<Emojis.Emoji> emojis = new ArrayList<>();
    private JToolBar toolbar;

    public EmojiToolbarData(String name) {
        this.name = name;
        this.documentation = null;
        this.htmlDocumentation = null;
    }

    public EmojiToolbarData(String name, String documentation, String htmlDocumentation) {
        this.name = name;
        this.documentation = documentation;
        this.htmlDocumentation = htmlDocumentation;
    }

    public String getName() {
        return name;
    }

    public String getDocumentation() {
        return documentation;
    }
    
    public String getHTMLDocumentation() {
        return htmlDocumentation;
    }
    
    public void setToolbar(JToolBar toolbar) {
        this.toolbar = toolbar;
    }
    
    public JToolBar getToolbar() {
        return toolbar;
    }

    public List<Emojis.Emoji> getEmojis() {
        return new ArrayList<>(emojis);
    }

    public void add(Emojis.Emoji emoji) {
        emojis.add(emoji);
    }
    
}
