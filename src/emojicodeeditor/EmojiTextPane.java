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

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.Timer;

/**
 * The control with the editor.
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public final class EmojiTextPane extends JTextPane implements MouseListener, MouseMotionListener {

    private final MainWindow jFrameMain;
    private final Timer timer;
//    private final EditorToolTip editorToolTip;
    private final JPopupMenu popup;
//    private final JTextField textField = new JTextField("Testar");
    private final JTextPane textPane = new JTextPane();
    private final Point lastMousePos = new Point();
    
    
    EmojiTextPane(MainWindow jFrameMain) {
        this.jFrameMain = jFrameMain;
//        this.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
//        this.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
//        textPane.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
//        editorToolTip = new EditorToolTip();
//        this.add(textField);
//        textField.setVisible(true);
        popup = new JPopupMenu();
        popup.setLayout(new BorderLayout());
//      popup.add(new JPanel()); // your component
        popup.add(textPane); // your component
        popup.setPopupSize(500, 500);
        popup.setLocation(200,200);
        timer = new Timer(500, (ActionEvent e) -> {
            showToolTip();
        });
//        timer.start();
        addMouseListener(this);
        addMouseMotionListener(this);
//        add(editorToolTip);
//        System.out.println("EmojiTextPane constructor");
    }
    
    
    private String getCharAtPos(int i) {
        int pos = 0;
        String text = getText();
        
        while ((pos < i) && (pos < text.length())) {
            pos += Character.charCount(text.codePointAt(pos));
        }
        
        if (pos < text.length()) {
//            return new String(Character.toChars(text.codePointAt(pos)));
//            System.out.format("getCharAtPos: %d, %s\n",i,new String(Character.toChars(text.codePointAt(pos))));
//            return String.format("%d, %d, %s", i, text.codePointAt(pos), new String(Character.toChars(text.codePointAt(pos))));
            return new String(Character.toChars(text.codePointAt(pos)));
        } else {
            return null;
        }
    }
    
    
    private void showToolTip() {
        String text = getText();
        int count = 0;
        do {
            Point p = lastMousePos;
            p.x -= count;
            int pos = viewToModel(p);
            if (pos < text.length()) {
//                String emoji = getCharAtPos(pos + count);
                String emoji = getCharAtPos(pos);
                String description = Emojis.getInstance().getEmojiHTMLDocumentation(emoji);
                
                if (description != null) {
                    textPane.setText(String.format("%s %s", emoji, description));
                    popup.setVisible(true);
                    return;
                }
            }
        } while (++count < 3);
        
        String emoji = getCharAtPos(viewToModel(lastMousePos));
        textPane.setText(String.format("'%s' %s", emoji, "No emoji"));
        popup.setVisible(true);
//        editorToolTip.setVisible(true);
    }
    
    private void hideToolTip() {
        if (popup.isVisible())
            popup.setVisible(false);
    }
    
    
//    @Override
//    public JToolTip createToolTip() {
//        System.out.println("EmojiTextPane.createToolTip");
//        return new EditorToolTip();
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (timer.isRunning())
            timer.restart();
        hideToolTip();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (timer.isRunning())
            timer.restart();
        hideToolTip();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (timer.isRunning())
            timer.restart();
        hideToolTip();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        timer.start();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        timer.stop();
        hideToolTip();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (timer.isRunning())
            timer.restart();
        hideToolTip();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (timer.isRunning())
            timer.restart();
        hideToolTip();
        lastMousePos.setLocation(e.getPoint());
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * This class may not be serialized so throw an exception.
     * @param oos the object stream
     * @throws IOException this method always throws an IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("This class is NOT serializable.");
    }
    
}
