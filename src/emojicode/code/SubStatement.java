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
package emojicode.code;

import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import java.util.List;

/**
 * Base class for statements like ForIn.
 * It is used by the Statement class.
 */
public abstract class SubStatement extends Parent {
    
    public SubStatement(Parent aParent, HasVariables hasVariables) {
        super(aParent, hasVariables);
    }
    
    
    /**
     * Parse this statement.
     * @param tokenStream the stream of tokens.
     * @throws CompilerError thrown on compilation error
     */
    public abstract void parse(final TokenStream tokenStream) throws CompilerError;
    
    
    /**
     * Run the statement.
     * @param variableStack 
     */
    public abstract void run(List<List<Variable>> variableStack);
    
}
