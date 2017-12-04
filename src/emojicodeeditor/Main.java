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

import util.CheckStyleAnalyzer;
import util.FindFilesWithNullChar;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point of the program.
 *
 * @author Daniel Bergqvist
 */
public final class Main {
    
    /**
     * Private constructor since this class should never be instanced.
     */
    private Main() {
    }
    
    //CHECKSTYLE.OFF: MagicNumberCheck - Magic numbers is used for the number of parameters that
    // the program expects and there is no need to have constants for these.
    /**
     * The starting point of the program.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        
        if ((args.length == 3) && (args[0].toLowerCase().equals("checkstyle"))) {
            new CheckStyleAnalyzer(args[1], args[2]).analyze();
            System.exit(0);
        }
        
        if ((args.length == 1) && (args[0].toLowerCase().equals("findnull"))) {
            FindFilesWithNullChar.find();
            System.exit(0);
        }
        
        if (1 == 0) {
            GenerateTests.generate();
            System.exit(0);
        }
        
        if (1 == 1) {
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    String filename = "emojicode_test/compilation/hello.emojic";
//                    filename = "emojicode_test/compilation/extension.emojic";
//                    filename = "emojicode_test/compilation/class.emojic";
////                    filename = "emojicode_test/compilation/class.emojic";
                    new TestDebuggerWindow(filename).setVisible(true);
                }
            });
            return;
        }
        if (1 == 1) {
            try {
//                int code = "🔡".codePointAt(0);
//                System.out.format("🔡 : 0x%X\n", code);
//                new TestCompiler().testFile_new("test/emojicode_tests/compilation/hello.emojic");
////////                new TestCompiler().testCompileAndRun(
////////                                        "test/emojicode_tests/compilation/hello.emojic");
//                new TestCompiler().testFile("test/emojicode_tests/compilation/hello.emojic");
                if (1 == 0) {
                    throw new IOException("");
                }
//////                new TestCompiler().testAllFiles("test/emojicode_tests/compilation");
//                new TestCompiler().testAllFiles("test/emojicode_tests/reject");
//            } catch (CompilerError | LogicError | IOException ex) {
            } catch (Throwable ex) {
                Logger.getLogger(emojicode.compiler.Compiler.class.getName())
                        .log(Level.SEVERE, null, ex);
            } finally {
                System.exit(0);
            }
        }
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and
         * feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    //CHECKSTYLE.ON: MagicNumberCheck - Magic numbers is used for the number of parameters that
    // the program expects and there is no need to have constants for these.
    
}
