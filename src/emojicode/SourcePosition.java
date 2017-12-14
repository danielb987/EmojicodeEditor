/*
 * Copyright 2017 Theo Weidmann. All rights reserved.
 *
 * Converted to Java by Daniel Bergqvist.
 *
 * See the file LICENSE for The Artistic License 2.0
 */
package emojicode;

/**
 * A position in the emojicode source.
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
