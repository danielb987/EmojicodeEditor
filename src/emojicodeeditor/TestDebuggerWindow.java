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

import emojicode.SourcePosition;
import emojicode.Token;
import emojicode.code.Parent;
import emojicode.code.predifined_packages.s.StandardIO;
import emojicode.compiler.CompilerError;
import emojicode.compiler.Lexer;
import emojicode.compiler.LogicError;
import emojicode.compiler.TokenStream;
import emojicode.runtime.Debugger;
import emojicode.runtime.DebuggerException;
import java.awt.Color;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Tests the debugger.
 *
 * @author Daniel Bergqvist
 */
public final class TestDebuggerWindow extends javax.swing.JFrame implements Debugger.DebugActions {

    private static final long serialVersionUID = 1L;
    
    private final Style defaultStyle =
                    StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    
    private final DefaultStyledDocument sourceCodeDocument = new DefaultStyledDocument();
    
    private final String source;
    
    private final String filename;
    
    private TokenStream testTokenStream;
    
    
    //CHECKSTYLE.OFF: FinalParametersCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    /**
     * Creates new form TestDebugger.
     *
     * @param aFilename the file name of the emojicode source file
     */
    public TestDebuggerWindow(final String aFilename) {
        initComponents();
        
        jTextPaneSourceCode.setEditorKit(new EmojiStyledEditorKit());
        jTextPaneSourceCode.setDocument(sourceCodeDocument);
        
        enableDebugButtons(true);
        
        this.filename = aFilename;
        
        StandardIO.getInstance().setOutput((String string) -> {
            java.awt.EventQueue.invokeLater(() -> {
                jTextAreaOutput.append(string);
            });
        });
        
        if (1 == 1) {
            jTextPaneSourceCode.setText("Testar Daniel 123123");
            source = "";
            return;
        }
        
        try {
            byte[] encoded;
            encoded = Files.readAllBytes(Paths.get(aFilename));
            source = new String(encoded, Charset.forName("UTF-8"));
            jTextPaneSourceCode.setText(source);
            jTextPaneSourceCode.setCaretPosition(0);
        } catch (IOException ex) {
            Logger.getLogger(TestDebuggerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        testStyle();
        
//        testStyle();
    }
    
    
    //CHECKSTYLE.OFF: MagicNumberCheck - This method is only temporary and only for testing.
    /**
     * Test different styles.
     */
    void testStyle() {
        
        System.out.println("Testar");
        
        jTextPaneSourceCode.setCaretPosition(0);
        
        sourceCodeDocument.setCharacterAttributes(0,
                                                  sourceCodeDocument.getLength(),
                                                  defaultStyle,
                                                  true);
        
        StyleContext cont = StyleContext.getDefaultStyleContext();
        
        MutableAttributeSet attr = new SimpleAttributeSet();
        attr.addAttribute("strike-color", Color.black);
        sourceCodeDocument.setCharacterAttributes(5, 10, attr, false);
        
        MutableAttributeSet attr2 = new SimpleAttributeSet();
        attr2.addAttribute("underline-color", Color.red);
        sourceCodeDocument.setCharacterAttributes(25, 31, attr2, false);
        
        AttributeSet attrHead =
                cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
        AttributeSet attrTail =
                cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.YELLOW);
        sourceCodeDocument.setCharacterAttributes(10, 10, attrHead, false);
    }
    //CHECKSTYLE.ON: MagicNumberCheck - This method is only temporary and only for testing
    
    
    //CHECKSTYLE.OFF: WhitespaceAroundCheck - This method is generated by NetBeans and we cannot edit it.
    //CHECKSTYLE.OFF: LineLengthCheck - This method is generated by NetBeans and we cannot edit it.
    //CHECKSTYLE.OFF: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneSourceCode = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonRun = new javax.swing.JButton();
        jButtonStepInto = new javax.swing.JButton();
        jButtonStepOver = new javax.swing.JButton();
        jButtonBreakpoint = new javax.swing.JButton();
        jButtonCompile = new javax.swing.JButton();
        jButtonStopProgram = new javax.swing.JButton();
        jButtonTokens = new javax.swing.JButton();
        jButtonRunToCursor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPaneSourceCode);

        jButtonRun.setText("Run");
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });

        jButtonStepInto.setText("Step into");
        jButtonStepInto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStepIntoActionPerformed(evt);
            }
        });

        jButtonStepOver.setText("Step over");

        jButtonBreakpoint.setText("Breakpoint");

        jButtonCompile.setText("Compile");
        jButtonCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompileActionPerformed(evt);
            }
        });

        jButtonStopProgram.setText("Stop program");
        jButtonStopProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopProgramActionPerformed(evt);
            }
        });

        jButtonTokens.setText("Tokens");
        jButtonTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTokensActionPerformed(evt);
            }
        });

        jButtonRunToCursor.setText("Run to cursor");
        jButtonRunToCursor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunToCursorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCompile)
                .addGap(18, 18, 18)
                .addComponent(jButtonRun)
                .addGap(18, 18, 18)
                .addComponent(jButtonRunToCursor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStepInto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStepOver)
                .addGap(18, 18, 18)
                .addComponent(jButtonBreakpoint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jButtonTokens)
                .addGap(18, 18, 18)
                .addComponent(jButtonStopProgram)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRun)
                    .addComponent(jButtonStepInto)
                    .addComponent(jButtonStepOver)
                    .addComponent(jButtonBreakpoint)
                    .addComponent(jButtonCompile)
                    .addComponent(jButtonStopProgram)
                    .addComponent(jButtonTokens)
                    .addComponent(jButtonRunToCursor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setRows(5);
        jScrollPane2.setViewportView(jTextAreaOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE.ON: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
    //CHECKSTYLE.ON: LineLengthCheck - This method is generated by NetBeans and we cannot edit it.
    //CHECKSTYLE.ON: WhitespaceAroundCheck - This method is generated by NetBeans and we cannot edit it.
    
    
    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        
        emojicode.code.EmojiPackageUserDefinied emojiPackage;
        
        enableDebugButtons(false);
        startProgram(false);
/*
        try {
            jTextArea_Output.setText("");
            emojiPackage = new emojicode.code.EmojiPackage_UserDefinied("daniel",filename,source);
            emojiPackage.parse();
//            emojiPackage.run();
        } catch (CompilerError | LogicError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
            enableDebugButtons(true);
            return;
        }
        
        try {
            Debugger debugger = Debugger.getInstance();
            debugger.setPauseOnEveryStep(false);
            debugger.runProgram(emojiPackage, this);
        } catch (DebuggerException ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
*/
    }//GEN-LAST:event_jButtonRunActionPerformed

    private void jButtonCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompileActionPerformed
        try {
            jTextAreaOutput.setText("");
            emojicode.code.EmojiPackageUserDefinied emojiPackage =
                    new emojicode.code.EmojiPackageUserDefinied("daniel", filename, source);
            emojiPackage.parse();
        } catch (CompilerError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
            jTextPaneSourceCode.setCaretPosition(ex.sourcePosition.getIndex());
            jTextPaneSourceCode.requestFocusInWindow();
            throw new RuntimeException(ex);
        } catch (LogicError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
    }//GEN-LAST:event_jButtonCompileActionPerformed

    private void jButtonStepIntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStepIntoActionPerformed
        
        Debugger debugger = Debugger.getInstance();
        
        if (debugger.isProgramRunning()) {
            debugger.continueRun();
        } else {
            startProgram(true);
        }
/*
        try {
            jTextArea_Output.setText("");
            emojicode.code.EmojiPackage_UserDefinied emojiPackage =
                new emojicode.code.EmojiPackage_UserDefinied("daniel", filename, source);
            emojiPackage.parse();
        } catch (CompilerError | LogicError ex) {
            Debugger.getInstance().stopDebugger();
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
*/
    }//GEN-LAST:event_jButtonStepIntoActionPerformed

    private void jButtonStopProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopProgramActionPerformed
        System.out.println("AAA");
        Debugger.getInstance().stopDebugger();
    }//GEN-LAST:event_jButtonStopProgramActionPerformed

    private void jButtonTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTokensActionPerformed
        
        if (testTokenStream == null) {
            Lexer lexer = new Lexer(source, filename);

            try {
                testTokenStream = lexer.lex();
            } catch (CompilerError | LogicError ex) {
                ex.printStackTrace();
            }
        }
        
        if (testTokenStream.hasMoreTokens()) {
            try {
                StyleContext cont = StyleContext.getDefaultStyleContext();
//                AttributeSet attr =
//                    cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
                AttributeSet attr =
                    cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
//                AttributeSet attrBlack =
//                    cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
                AttributeSet attrBlack =
                    cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.BLACK);
                
                Token token = testTokenStream.consumeToken();
                
                sourceCodeDocument.setCharacterAttributes(0,
                                                          sourceCodeDocument.getLength(),
                                                          defaultStyle,
                                                          true);
                
                int length = token.endPosition.getIndex() - token.startPosition.getIndex();
//                System.out.format("Token: %s, %s. Start: %d, end: %d\n", token.type.name(),
//                    token.toString(), token.startPosition.index, token.endPosition.index);
                sourceCodeDocument.setCharacterAttributes(token.startPosition.getIndex(),
                                                          length,
                                                          attr,
                                                          false);
//                sourceCodeDocument.setCharacterAttributes(token.startPosition.index,2,attr,false);
//                sourceCodeDocument.setCharacterAttributes(token.startPosition.index,20,attr,false);
//                sourceCodeDocument.setCharacterAttributes(11,3,attrBlack,false);
//                sourceCodeDocument.setCharacterAttributes(7,10,defaultStyle,false);
//                jTextPane_SourceCode.
            } catch (CompilerError ex) {
                ex.printStackTrace();
            }
        } else {
            testTokenStream = null;
        }
    }//GEN-LAST:event_jButtonTokensActionPerformed

    private void jButtonRunToCursorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunToCursorActionPerformed
        
        int runToCursor = jTextPaneSourceCode.getSelectionStart();
//        System.out.format("Cursor: %d\n", runToCursor);
//        if (1==1)
//            return;
        
        Debugger debugger = Debugger.getInstance();
        
        if (debugger.isProgramRunning())
            debugger.continueRunToCursor(runToCursor);
        else
            startProgram(false, runToCursor);
    }//GEN-LAST:event_jButtonRunToCursorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBreakpoint;
    private javax.swing.JButton jButtonCompile;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JButton jButtonRunToCursor;
    private javax.swing.JButton jButtonStepInto;
    private javax.swing.JButton jButtonStepOver;
    private javax.swing.JButton jButtonStopProgram;
    private javax.swing.JButton jButtonTokens;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextPane jTextPaneSourceCode;
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * Turn on or off the buttons for debugging.
     * @param enable true if buttons should be turned on, false otherwise
     */
    private void enableDebugButtons(final boolean enable) {
        jButtonRun.setEnabled(enable);
        jButtonCompile.setEnabled(enable);
        jButtonStepInto.setEnabled(enable);
        jButtonStopProgram.setEnabled(! enable);
    }
    
    
    /**
     * Start the emojicode program.
     * @param pauseOnEveryStep true if the debugger shall stop the program after every program step
     */
    private void startProgram(final boolean pauseOnEveryStep) {
        startProgram(pauseOnEveryStep, -1);
    }
    
    
    /**
     * Start the emojicode program.
     * @param pauseOnEveryStep true if the debugger shall stop the program after every program step
     * @param runToCursor if >= 0, run the program to the point in the emojicode, measured in
     * unicode codepoints
     */
    private void startProgram(final boolean pauseOnEveryStep, final int runToCursor) {
        emojicode.code.EmojiPackageUserDefinied emojiPackage;
        
        enableDebugButtons(false);
        try {
            jTextAreaOutput.setText("");
            emojiPackage = new emojicode.code.EmojiPackageUserDefinied("daniel", filename, source);
            emojiPackage.parse();
//            emojiPackage.run();
        } catch (CompilerError | LogicError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
            enableDebugButtons(true);
            return;
        }
        
        try {
            Debugger debugger = Debugger.getInstance();
            debugger.setPauseOnEveryStep(pauseOnEveryStep);
            
            if (runToCursor >= 0)
                debugger.runProgramToCursor(emojiPackage, this, runToCursor);
            else
                debugger.runProgram(emojiPackage, this);
        } catch (DebuggerException ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
    }
    
    
    /**
     * Step to next part in the emojicode program.
     * @param parent
     * @param startPosition
     * @param middlePosition
     * @param endPosition 
     */
    @Override
    public void next(final Parent parent,
                     final SourcePosition startPosition,
                     final SourcePosition middlePosition,
                     final SourcePosition endPosition) {
        
        enableDebugButtons(true);
        StyleContext cont = StyleContext.getDefaultStyleContext();
        AttributeSet attrHead =
                cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
        AttributeSet attrTail =
                cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.YELLOW);
        sourceCodeDocument.setCharacterAttributes(0,
                                                  sourceCodeDocument.getLength(),
                                                  defaultStyle,
                                                  true);
//        System.out.format("%d, %d, %d\n",
//                          startPosition.index,
//                          middlePosition.index,
//                          endPosition.index);
        
        int lengthHead = middlePosition.getIndex() - startPosition.getIndex();
        int lengthTail = endPosition.getIndex() - middlePosition.getIndex();
        
        sourceCodeDocument.setCharacterAttributes(startPosition.getIndex(),
                                                  lengthHead,
                                                  attrHead,
                                                  false);
        
        sourceCodeDocument.setCharacterAttributes(middlePosition.getIndex(),
                                                  lengthTail,
                                                  attrTail,
                                                  false);
    }
    
    
    /**
     * Called then the emojicode program ends.
     */
    @Override
    public void programEnded() {
        enableDebugButtons(true);
        sourceCodeDocument.setCharacterAttributes(0, sourceCodeDocument.getLength(), defaultStyle, true);
    }
    
    
    /**
     * Called then the emojicode program is aborted.
     * @param position The position in the source code there the program was aborted
     */
    @Override
    public void programAborted(final SourcePosition position) {
        enableDebugButtons(true);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
     * Called then the emojicode program has a runtime error
     * @param position The position in the source code there the error occurs
     */
    @Override
    public void programError(final SourcePosition position) {
        enableDebugButtons(true);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    //CHECKSTYLE.ON: FinalParametersCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
}
