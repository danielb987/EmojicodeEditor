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
package emojicode.compiler;

/**
 * A helper class for strings.
 * @author Daniel Bergqvist
 */
public final class StringHelper {
    
    /**
     * Private constructor since this class should never be instanced.
     */
    private StringHelper() {
        // This class should never be instanced
    }
    
    
    /**
     * Return the last unicode codepoint in the string.
     * A unicode character may take two Java characters so this method is needed to find the last
     * unicode character in the string.
     * @param str the string to be searched
     * @return the unicode codepoint of the last character in the string
     */
    public static int getLastUnicodeChar(final String str) {
        
        final int length = str.length();
        
        int codePoint = -1;
        for (int offset = 0; offset < length;) {
            codePoint = str.codePointAt(offset);
            offset += Character.charCount(codePoint);
        }
        
        return codePoint;
    }
    
}
