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

/**
 *
 * @author Daniel Bergqvist
 */
public final class Version {
    
    /**
     * Major version number.
     */
    public static final int MAJOR_VERSION_NUMBER = 0;
    
    /**
     * Minor version number.
     */
    public static final int MINOR_VERSION_NUMBER = 1;
    
    /**
     * Version string. Built of {@link #MAJOR_VERSION_NUMBER} and
     * {@link #MINOR_VERSION_NUMBER}
     */
    public static final String VERSION =
            String.format("%d.%d", MAJOR_VERSION_NUMBER, MINOR_VERSION_NUMBER);

    /**
     * Private constructor since this class should never be instanced.
     */
    private Version() {
    }
    
}
