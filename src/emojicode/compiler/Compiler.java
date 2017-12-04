/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emojicode.compiler;

import emojicode.Token;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Compiler {
    
    public static void compile(String string, String filename) throws CompilerError {
        
        System.out.format("\n\nCompile file: %s\n", filename);

        Lexer lexer = new Lexer(string, filename);

        TokenStream tokenStream = lexer.lex();

//            System.out.println();
//            System.out.println();
//            System.out.println();

        while (tokenStream.hasMoreTokens()) {
            Token token = tokenStream.nextToken();
            System.out.format("Token: %s, value: %s\n", token.type.toString(), token.toString());
            tokenStream.consumeToken();
        }
    }
    
}
