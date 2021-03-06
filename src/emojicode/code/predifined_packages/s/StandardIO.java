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
package emojicode.code.predifined_packages.s;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * StandardIO
 * This class is used to be able to redirect standard in/out
 * 
 * @author Daniel Bergqvist
 */
public class StandardIO {
    
    private static final StandardIO instance = new StandardIO();
    
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
    
    
    public static StandardIO getInstance() {
        return instance;
    }
    
    
    // This class is a singleton
    private StandardIO() {
    }
    
    
    public BufferedReader getInput() {
        return input;
    }
    
    
    public PrintWriter getOutput() {
        return output;
    }
    
    
    public void setOutput(PrintWriter output) {
        this.output = output;
    }
    
    
    public void setOutput(StringReceiver stringReceiver) {
        setOutput(new PrintWriter(new FunctionWriter(stringReceiver)));
    }
    
    
    
    public interface StringReceiver {
        void receive(String string);
    }
    
    
    private class FunctionWriter extends Writer {
        
        private final StringReceiver stringReceiver;
        
        FunctionWriter(StringReceiver stringReceiver) {
            this.stringReceiver = stringReceiver;
        }
        
        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            stringReceiver.receive(new String(cbuf, off, len));
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
        }
    
    }
    
}
