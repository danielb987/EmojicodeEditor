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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * Colored Strikethrough text attribute with user defined color in JEditorPane.
 * http://java-sl.com/tip_colored_strikethrough.html
 * 
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public class EmojiStyledEditorKit extends StyledEditorKit {
    
    public static final String ERROR_UNDERLINE_ATTRIBUTE_NAME="error-underline";
    
    
    @Override
    public ViewFactory getViewFactory() {
        return new EmojiViewFactory();
    }
    
    
    private static class EmojiViewFactory implements ViewFactory {

        @Override
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new EmojiLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new NoWrapParagraphView(elem);
//                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }

            }

            // default to text display 
            return new LabelView(elem);
        }
    }
    
    
    private static class EmojiLabelView extends LabelView {
        
        private EmojiLabelView(Element elem) {
            super(elem);
        }
        
        @Override
        public void paint(Graphics g, Shape allocation) {
            super.paint(g, allocation);
            paintStrikeLine(g, allocation);
        }
        
        public void paintStrikeLine(Graphics g, Shape a) {
            Color c = (Color) getElement().getAttributes().getAttribute("strike-color");
            if (c != null) {
                int y = a.getBounds().y + a.getBounds().height - (int) getGlyphPainter().getDescent(this);
                
                int yTop = y - (int) (getGlyphPainter().getAscent(this));
                int yBottom = y + (int) (getGlyphPainter().getDescent(this)) - 1;
                int yHeight = yBottom - yTop;
//                y = y - (int) (getGlyphPainter().getAscent(this));
                y = y - (int) (getGlyphPainter().getAscent(this) * 0.3f);
//                y = y + (int) (getGlyphPainter().getDescent(this)) - 1;
                int x1 = (int) a.getBounds().getX();
                int x2 = (int) (a.getBounds().getX() + a.getBounds().getWidth());
                
                Color old = g.getColor();
                g.setColor(c);
                g.drawLine(x1, y, x2, y);
                g.setColor(old);
                
//                System.out.format("yTop: %d, yBottom: %d, yHeight: %d\n", yTop, yBottom, yHeight);
                
//                for (int x = x1; x+yHeight < x2; x += yHeight/3)
//                    g.drawLine(x, yBottom, x+yHeight, yTop);
                
/*                
                Graphics2D g2 = (Graphics2D)g;
                Stroke saveStroke = g2.getStroke();
                float[] dash = {2,2};
                BasicStroke stroke1 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2);
                BasicStroke stroke2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2);
                g2.setStroke(stroke1);
                g.drawLine(x1, yBottom-1, x2, yBottom-1);
                g2.setStroke(stroke2);
                g.drawLine(x1, yBottom, x2, yBottom);
                g2.setStroke(saveStroke);
*/
            }
            
            Color c2 = (Color) getElement().getAttributes().getAttribute(ERROR_UNDERLINE_ATTRIBUTE_NAME);
            if (c2 != null) {
                int y = a.getBounds().y + a.getBounds().height - (int) getGlyphPainter().getDescent(this);
                
                int yBottom = y + (int) (getGlyphPainter().getDescent(this)) - 1;
                int x1 = (int) a.getBounds().getX();
                int x2 = (int) (a.getBounds().getX() + a.getBounds().getWidth());
                
                Color old = g.getColor();
                g.setColor(c2);
                
                Graphics2D g2 = (Graphics2D)g;
                Stroke saveStroke = g2.getStroke();
                float[] dash = {2,2};
                BasicStroke stroke1 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2);
                BasicStroke stroke2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 0);
                g2.setStroke(stroke1);
                g.drawLine(x1, yBottom-1, x2, yBottom-1);
                g2.setStroke(stroke2);
                g.drawLine(x1, yBottom, x2, yBottom);
                g2.setStroke(saveStroke);
                g.setColor(old);
            }
        }
        
    }
    
    
    
    private static class NoWrapParagraphView extends ParagraphView {

        public NoWrapParagraphView(Element elem) {
            super(elem);
        }

        @Override
        public void layout(int width, int height) {
            super.layout(Short.MAX_VALUE, height);
        }

        @Override
        public float getMinimumSpan(int axis) {
            return super.getPreferredSpan(axis);
        }
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
