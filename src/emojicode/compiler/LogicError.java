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

import emojicode.SourcePosition;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Logic error in the emojicode program.
 * This exception may be thrown when the emojicode program is executed.
 * 
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public class LogicError extends Exception {

    public final SourcePosition sourcePosition;

    /**
     * Creates a new instance of <code>CompilerError</code> without detail
     * message.
     */
//    public CompilerError() {
//    }

    /**
     * Constructs an instance of <code>LogicError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LogicError(String msg) {
        super(msg);
        sourcePosition = null;
    }

    /**
     * Constructs an instance of <code>CompilerError</code> with the specified
     * detail message.
     *
     * @param sourcePosition
     * @param msg the detail message.
     */
    public LogicError(SourcePosition sourcePosition, String msg) {
        super(msg);
        this.sourcePosition = sourcePosition;
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
