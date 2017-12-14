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
    private final int fIndex;
    
    /**
     * The line number.
     */
    private final int fLine;
    
    /**
     * The column number.
     */
    private final int fColumn;
    
    /**
     * The file name.
     */
    private final String fFilename;
    
    
    /**
     * Create an instance.
     *
     * @param aIndex the unicode codepoint fIndex in the string that is compiled
     * @param aLine the fLine number
     * @param aColumn the column number
     * @param aFileName the file name
     */
    public SourcePosition(final int aIndex,
                          final int aLine,
                          final int aColumn,
                          final String aFileName) {
        this.fIndex = aIndex;
        this.fLine = aLine;
        this.fColumn = aColumn;
        this.fFilename = aFileName;
    }
    
    
    /**
     * Get the unicode codepoint fIndex in the string that is compiled.
     *
     * @return the codepoint fIndex
     */
    public int getIndex() {
        return fIndex;
    }
    
    
    /**
     * Get the fLine number.
     *
     * @return the fLine number
     */
    public int getLine() {
        return fLine;
    }
    
    
    /**
     * Get the column number.
     *
     * @return the column number
     */
    public int getColumn() {
        return fColumn;
    }
    
    
    /**
     * Get the file name.
     *
     * @return the file name
     */
    public String getFilename() {
        return fFilename;
    }
    
}
