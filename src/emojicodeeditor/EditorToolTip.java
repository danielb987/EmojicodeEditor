/*
 * The MIT License
 *
 * Copyright 2017 daniel.
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTextArea;

/**
 *
 * @author daniel
 */
public final class EditorToolTip extends JTextArea {
    
//    private final JTextArea tipArea;
    
    
    EditorToolTip() {
        super("Testar");
        setPreferredSize(new Dimension(100,100));
        System.out.println("EditorToolTip constructor");
//        setLayout(new BorderLayout());
//        tipArea = new JTextArea();
//        tipArea.setEditable(false);
//        tipArea.setBackground(new Color(255, 255, 204));
        setBackground(new Color(255, 255, 204));
        setVisible(true);
    }
/*    
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }
*/    
    /**
     * Always returns true since tooltips, by definition, should always be on top of all other windows.
     * @return Always true
     */
    boolean alwaysOnTop() {
        return true;
    }
}
