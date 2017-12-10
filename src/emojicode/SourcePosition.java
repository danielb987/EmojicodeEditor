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

/**
 * A position in the emojicode source.
 * @author
 */
public final class SourcePosition {
    
    /**
     * The unicode codepoint index in the string that is compiled.
     */
    private final int index;
    
    /**
     * The line number.
     */
    private final int line;
    
    /**
     * The column number.
     */
    private final int column;
    
    /**
     * The file name.
     */
    private final String filename;
    
    
    /**
     * Create an instance.
     *
     * @param aIndex the unicode codepoint index in the string that is compiled
     * @param aLine the line number
     * @param aColumn the column number
     * @param aFileName the file name
     */
    public SourcePosition(final int aIndex,
                          final int aLine,
                          final int aColumn,
                          final String aFileName) {
        this.index = aIndex;
        this.line = aLine;
        this.column = aColumn;
        this.filename = aFileName;
    }
    
    
    /**
     * Get the unicode codepoint index in the string that is compiled.
     *
     * @return the codepoint index
     */
    public int getIndex() {
        return index;
    }
    
    
    /**
     * Get the line number.
     *
     * @return the line number
     */
    public int getLine() {
        return line;
    }
    
    
    /**
     * Get the column number.
     *
     * @return the column number
     */
    public int getColumn() {
        return column;
    }
    
    
    /**
     * Get the file name.
     *
     * @return the file name
     */
    public String getFilename() {
        return filename;
    }
    
}
