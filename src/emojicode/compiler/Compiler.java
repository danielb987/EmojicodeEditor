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

import emojicode.Token;

/**
 * An emojicode compiler.
 * @author Daniel Bergqvist
 */
public final class Compiler {
    
    /**
     * An emojicode compiler.
     * @param string the emojicode program that is to be compiled
     * @param filename the filename of the file that is to be compiled
     * @throws CompilerError a compiler error
     */
    public static void compile(final String string, final String filename) throws CompilerError {
        
        System.out.format("%n%nCompile file: %s%n", filename);

        Lexer lexer = new Lexer(string, filename);

        TokenStream tokenStream = lexer.lex();

//            System.out.println();
//            System.out.println();
//            System.out.println();

        while (tokenStream.hasMoreTokens()) {
            Token token = tokenStream.nextToken();
            System.out.format("Token: %s, value: %s%n", token.fType.toString(), token.toString());
            tokenStream.consumeToken();
        }
    }
    
    
    /**
     * Private constructor since this class should never be instanced.
     */
    private Compiler() {
    }
    
}
