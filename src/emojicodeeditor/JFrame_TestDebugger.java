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
import emojicode.code.EmojiPackage_UserDefinied;
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
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Tests the debugger
 * 
 * @author Daniel Bergqvist
 */
public final class JFrame_TestDebugger extends javax.swing.JFrame implements Debugger.DebugActions {
    
    private final Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    private final DefaultStyledDocument sourceCodeDocument = new DefaultStyledDocument();
    
    String source;
    
    private final String filename;
    
    private TokenStream testTokenStream;
    
    
    /**
     * Creates new form TestDebugger
     * 
     * @param filename
     */
    public JFrame_TestDebugger(String filename) {
        initComponents();
        
        jTextPane_SourceCode.setEditorKit(new EmojiStyledEditorKit());
        jTextPane_SourceCode.setDocument(sourceCodeDocument);
        
        enableDebugButtons(true);
        
        this.filename = filename;
        
        StandardIO.getInstance().setOutput((String string) -> {
            java.awt.EventQueue.invokeLater(() -> {
                jTextArea_Output.append(string);
            });
        });
        
        if (1==1) {
            jTextPane_SourceCode.setText("Testar Daniel 123123");
            return;
        }
        
        try {
            byte[] encoded;
            encoded = Files.readAllBytes(Paths.get(filename));
            source = new String(encoded, Charset.forName("UTF-8"));
            jTextPane_SourceCode.setText(source);
            jTextPane_SourceCode.setCaretPosition(0);
        } catch (IOException ex) {
            Logger.getLogger(JFrame_TestDebugger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        testStyle();
    }
    
    
    /**
     * Test different styles
     */
    void testStyle() {
        
        System.out.println("Testar");
        
        jTextPane_SourceCode.setCaretPosition(0);
        
        sourceCodeDocument.setCharacterAttributes(0, sourceCodeDocument.getLength(), defaultStyle, true);
        
        StyleContext cont = StyleContext.getDefaultStyleContext();
        
        MutableAttributeSet attr = new SimpleAttributeSet();
        attr.addAttribute("strike-color", Color.black);
        sourceCodeDocument.setCharacterAttributes(5,10,attr,false);
        
        MutableAttributeSet attr2 = new SimpleAttributeSet();
        attr2.addAttribute("underline-color", Color.red);
        sourceCodeDocument.setCharacterAttributes(25,31,attr2,false);
        
        AttributeSet attrHead = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
        AttributeSet attrTail = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.YELLOW);
        sourceCodeDocument.setCharacterAttributes(10,10,attrHead,false);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_SourceCode = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jButton_Run = new javax.swing.JButton();
        jButton_StepInto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton_Compile = new javax.swing.JButton();
        jButton_StopProgram = new javax.swing.JButton();
        jButton_Tokens = new javax.swing.JButton();
        jButton_RunToCursor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Output = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane_SourceCode);

        jButton_Run.setText("Run");
        jButton_Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RunActionPerformed(evt);
            }
        });

        jButton_StepInto.setText("Step into");
        jButton_StepInto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_StepIntoActionPerformed(evt);
            }
        });

        jButton3.setText("Step over");

        jButton4.setText("Breakpoint");

        jButton_Compile.setText("Compile");
        jButton_Compile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CompileActionPerformed(evt);
            }
        });

        jButton_StopProgram.setText("Stop program");
        jButton_StopProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_StopProgramActionPerformed(evt);
            }
        });

        jButton_Tokens.setText("Tokens");
        jButton_Tokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TokensActionPerformed(evt);
            }
        });

        jButton_RunToCursor.setText("Run to cursor");
        jButton_RunToCursor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RunToCursorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Compile)
                .addGap(18, 18, 18)
                .addComponent(jButton_Run)
                .addGap(18, 18, 18)
                .addComponent(jButton_RunToCursor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_StepInto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jButton_Tokens)
                .addGap(18, 18, 18)
                .addComponent(jButton_StopProgram)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Run)
                    .addComponent(jButton_StepInto)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton_Compile)
                    .addComponent(jButton_StopProgram)
                    .addComponent(jButton_Tokens)
                    .addComponent(jButton_RunToCursor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea_Output.setColumns(20);
        jTextArea_Output.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Output);

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

    private void jButton_RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RunActionPerformed
        
        emojicode.code.EmojiPackage_UserDefinied emojiPackage;
        
        enableDebugButtons(false);
        startProgram(false);
/*        
        try {
            jTextArea_Output.setText("");
            emojiPackage = new emojicode.code.EmojiPackage_UserDefinied("daniel", filename, source);
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
    }//GEN-LAST:event_jButton_RunActionPerformed

    private void jButton_CompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CompileActionPerformed
        try {
            jTextArea_Output.setText("");
            emojicode.code.EmojiPackage_UserDefinied emojiPackage = new emojicode.code.EmojiPackage_UserDefinied("daniel", filename, source);
            emojiPackage.parse();
        } catch (CompilerError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
            jTextPane_SourceCode.setCaretPosition(ex.sourcePosition.index);
            jTextPane_SourceCode.requestFocusInWindow();
            throw new RuntimeException(ex);
        } catch (LogicError ex) {
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
    }//GEN-LAST:event_jButton_CompileActionPerformed

    private void jButton_StepIntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_StepIntoActionPerformed
        
        Debugger debugger = Debugger.getInstance();
        
        if (debugger.isProgramRunning())
            debugger.continueRun();
        else
            startProgram(true);
/*        
        try {
            jTextArea_Output.setText("");
            emojicode.code.EmojiPackage_UserDefinied emojiPackage = new emojicode.code.EmojiPackage_UserDefinied("daniel", filename, source);
            emojiPackage.parse();
        } catch (CompilerError | LogicError ex) {
            Debugger.getInstance().stopDebugger();
            ex.printStackTrace(StandardIO.getInstance().getOutput());
        }
*/
    }//GEN-LAST:event_jButton_StepIntoActionPerformed

    private void jButton_StopProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_StopProgramActionPerformed
        System.out.println("AAA");
        Debugger.getInstance().stopDebugger();
    }//GEN-LAST:event_jButton_StopProgramActionPerformed

    private void jButton_TokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TokensActionPerformed
        
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
//                AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
                AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
//                AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
                AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.BLACK);
                Token token = testTokenStream.consumeToken();
                sourceCodeDocument.setCharacterAttributes(0, sourceCodeDocument.getLength(), defaultStyle, true);
                int length = token.endPosition.index - token.startPosition.index;
//                System.out.format("Token: %s, %s. Start: %d, end: %d\n", token.type.name(), token.toString(), token.startPosition.index, token.endPosition.index);
                sourceCodeDocument.setCharacterAttributes(token.startPosition.index,length,attr,false);
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
    }//GEN-LAST:event_jButton_TokensActionPerformed

    private void jButton_RunToCursorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RunToCursorActionPerformed
        
        int runToCursor = jTextPane_SourceCode.getSelectionStart();
//        System.out.format("Cursor: %d\n", runToCursor);
//        if (1==1)
//            return;
        
        Debugger debugger = Debugger.getInstance();
        
        if (debugger.isProgramRunning())
            debugger.continueRunToCursor(runToCursor);
        else
            startProgram(false, runToCursor);
    }//GEN-LAST:event_jButton_RunToCursorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_Compile;
    private javax.swing.JButton jButton_Run;
    private javax.swing.JButton jButton_RunToCursor;
    private javax.swing.JButton jButton_StepInto;
    private javax.swing.JButton jButton_StopProgram;
    private javax.swing.JButton jButton_Tokens;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_Output;
    private javax.swing.JTextPane jTextPane_SourceCode;
    // End of variables declaration//GEN-END:variables
    
    
    private void enableDebugButtons(boolean enable) {
        jButton_Run.setEnabled(enable);
        jButton_Compile.setEnabled(enable);
        jButton_StepInto.setEnabled(enable);
        jButton_StopProgram.setEnabled(! enable);
    }
    
    
    private void startProgram(boolean pauseOnEveryStep) {
        startProgram(pauseOnEveryStep, -1);
    }
    
    
    private void startProgram(boolean pauseOnEveryStep, int runToCursor) {
        emojicode.code.EmojiPackage_UserDefinied emojiPackage;
        
        enableDebugButtons(false);
        try {
            jTextArea_Output.setText("");
            emojiPackage = new emojicode.code.EmojiPackage_UserDefinied("daniel", filename, source);
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
    
    
    @Override
    public void next(Parent parent, SourcePosition startPosition, SourcePosition middlePosition, SourcePosition endPosition) {
        enableDebugButtons(true);
        StyleContext cont = StyleContext.getDefaultStyleContext();
        AttributeSet attrHead = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.RED);
        AttributeSet attrTail = cont.addAttribute(cont.getEmptySet(), StyleConstants.Background, Color.YELLOW);
        sourceCodeDocument.setCharacterAttributes(0, sourceCodeDocument.getLength(), defaultStyle, true);
//        System.out.format("%d, %d, %d\n", startPosition.index, middlePosition.index, endPosition.index);
        int lengthHead = middlePosition.index - startPosition.index;
        int lengthTail = endPosition.index - middlePosition.index;
        sourceCodeDocument.setCharacterAttributes(startPosition.index,lengthHead,attrHead,false);
        sourceCodeDocument.setCharacterAttributes(middlePosition.index,lengthTail,attrTail,false);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void programEnded() {
        enableDebugButtons(true);
        sourceCodeDocument.setCharacterAttributes(0, sourceCodeDocument.getLength(), defaultStyle, true);
    }

    @Override
    public void programAborted(SourcePosition position) {
        enableDebugButtons(true);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void programError(SourcePosition position) {
        enableDebugButtons(true);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
