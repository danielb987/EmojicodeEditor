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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.ToolTipManager;

/**
 * The main window of the program.
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public final class MainWindow extends javax.swing.JFrame {

    //CHECKSTYLE.OFF: FinalParametersCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    //CHECKSTYLE.OFF: WhitespaceAroundCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    //CHECKSTYLE.OFF: LineLengthCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    /**
     * Creates new form JFrame_Main
     */
    public MainWindow() {
        initComponents();
        
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        
        EmojicodeDocumentation.getInstance();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        java.awt.Container pane = getContentPane();
        pane.setLayout(new java.awt.BorderLayout());
        pane.add(jPanelToolbar, java.awt.BorderLayout.PAGE_START);
        pane.add(jSplitPaneTree,java.awt.BorderLayout.CENTER);
        jSplitPaneTree.setDividerLocation(0.3);
        jSplitPaneOutput.setDividerLocation(0.5);
        
        jPanelToolbar.setLayout(new WrapLayout());
        jPanelToolbar.setSize(500,500);
        
        // This label is only to make the toolbar panel visible in the Netbeans GUI design
        jPanelToolbar.remove(jLabelMakeToolbarVisible);
        
        this.addComponentListener(new java.awt.event.ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                jSplitPaneTree.setDividerLocation(200);
                jSplitPaneOutput.setDividerLocation(0.8);
//                jSplitPane_Output.setDividerLocation(jSplitPane_Output.getHeight()-200);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        
        javax.swing.JToolBar toolbar;
        
        Font font = new Font(Font.DIALOG, Font.PLAIN, 16);
/********************        
        for (EmojicodeSyntax.EmojicodeSyntaxToolbarData emojicodeSyntaxToolbarData : EmojicodeSyntax.getInstance().getEmojicodeSyntaxToolbarDataList()) {
            toolbar = new javax.swing.JToolBar();
            toolbar.setFloatable(false);
            jPanel_Toolbar.add(toolbar);
            JLabel label = new JLabel(emojicodeSyntaxToolbarData.getName());
//            if (emojiToolbarData.getDocumentation() != null)
//                label.setToolTipText(removeExcessWhiteChars(emojiToolbarData.getDocumentation()));
            label.setFont(font);
            toolbar.add(label);
            
            for (EmojicodeSyntax.EmojiSyntaxElement emojiSyntaxElement : emojicodeSyntaxToolbarData.getEmojiSyntaxElements()) {
                ToolbarClickAction action = new ToolbarClickAction(emojiSyntaxElement.getName(), emojiSyntaxElement.getHTMLDocumentation());
                toolbar.add(action);
                toolbar.getComponent(toolbar.getComponentCount()-1).setFont(font);
            }
        }
        
        
        
        for (EmojiToolbarData emojiToolbarData : Emojis.getInstance().getEmojiToolbarDataList()) {
            toolbar = new javax.swing.JToolBar();
            toolbar.setFloatable(false);
            jPanel_Toolbar.add(toolbar);
            JLabel label = new JLabel(emojiToolbarData.getName());
            if (emojiToolbarData.getDocumentation() != null)
                label.setToolTipText(emojiToolbarData.getHTMLDocumentation());
            label.setFont(font);
            toolbar.add(label);
            
            for (Emojis.Emoji emoji : emojiToolbarData.getEmojis()) {
                ToolbarClickAction action = new ToolbarClickAction(emoji.emoji, emoji.htmlDocumentation);
                toolbar.add(action);
                toolbar.getComponent(toolbar.getComponentCount()-1).setFont(font);
            }
        }
        
        toolbar = new javax.swing.JToolBar();
        toolbar.setFloatable(false);
        jPanel_Toolbar.add(toolbar);
        JLabel label = new JLabel("Packages");
        label.setFont(font);
        toolbar.add(label);
        javax.swing.JComboBox<EmojicodePackage> combobox = new javax.swing.JComboBox<>();
        jPanel_Toolbar.add(combobox);
        for (EmojicodePackage emojicodePackage : EmojicodeDocumentation.getInstance().getEmojicodePackages()) {
            combobox.addItem(emojicodePackage);
                
            for (EmojiToolbarData emojiToolbarData : emojicodePackage.getToolbarDataList()) {
                toolbar = new javax.swing.JToolBar();
                toolbar.setFloatable(false);
                emojicodePackage.addToolbar(toolbar);
                jPanel_Toolbar.add(toolbar);
                label = new JLabel(emojiToolbarData.getName());
                if (emojiToolbarData.getDocumentation() != null)
                    label.setToolTipText(emojiToolbarData.getHTMLDocumentation());
                label.setFont(font);
                toolbar.add(label);

                for (Emojis.Emoji emoji : emojiToolbarData.getEmojis()) {
                    ToolbarClickAction action = new ToolbarClickAction(emoji.emoji, emoji.htmlDocumentation);
                    toolbar.add(action);
                    toolbar.getComponent(toolbar.getComponentCount()-1).setFont(font);
                }
            }
        }
        combobox.addActionListener(new ComboboxActionListener());
        combobox.setSelectedIndex(0);
*/        
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get("greeter.emojic"));
            jTextPaneEditor.setText(new String(encoded, StandardCharsets.UTF_8));
            String text = jTextPaneEditor.getText();
            for (int i = 0; i < text.length(); i++) {
                String str = text.substring(i, i + Character.charCount(text.codePointAt(i)));
                System.out.format("%d: %d, %s%n", i, text.codePointAt(i), str);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //CHECKSTYLE.OFF: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelToolbar = new javax.swing.JPanel();
        jLabelMakeToolbarVisible = new javax.swing.JLabel();
        jSplitPaneTree = new javax.swing.JSplitPane();
        jSplitPaneOutput = new javax.swing.JSplitPane();
        jPanelOutput = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneEditor = new EmojiTextPane(this);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jMenuBarMainMenu = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuEdit = new javax.swing.JMenu();
        jMenuCompile = new javax.swing.JMenu();
        jMenuItem_Compile = new javax.swing.JMenuItem();
        jMenuItem_Run = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EmojicodeEditor");

        jLabelMakeToolbarVisible.setText("Label to show toolbar panel");

        javax.swing.GroupLayout jPanelToolbarLayout = new javax.swing.GroupLayout(jPanelToolbar);
        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolbarLayout.createSequentialGroup()
                .addComponent(jLabelMakeToolbarVisible)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelToolbarLayout.setVerticalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMakeToolbarVisible)
        );

        jSplitPaneOutput.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanelOutput.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));

        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setRows(5);
        jScrollPane2.setViewportView(jTextAreaOutput);

        javax.swing.GroupLayout jPanelOutputLayout = new javax.swing.GroupLayout(jPanelOutput);
        jPanelOutput.setLayout(jPanelOutputLayout);
        jPanelOutputLayout.setHorizontalGroup(
            jPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        jPanelOutputLayout.setVerticalGroup(
            jPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        jSplitPaneOutput.setRightComponent(jPanelOutput);

        jScrollPane1.setViewportView(jTextPaneEditor);

        jTabbedPane1.addTab("tab4", jScrollPane1);

        jSplitPane1.setLeftComponent(jTabbedPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel1);

        jSplitPaneOutput.setLeftComponent(jSplitPane1);

        jSplitPaneTree.setRightComponent(jSplitPaneOutput);

        jScrollPane3.setViewportView(jTree1);

        jSplitPaneTree.setLeftComponent(jScrollPane3);

        jMenuFile.setText("File");
        jMenuBarMainMenu.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuBarMainMenu.add(jMenuEdit);

        jMenuCompile.setText("Compile");

        jMenuItem_Compile.setText("Compile");
        jMenuItem_Compile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_CompileActionPerformed(evt);
            }
        });
        jMenuCompile.add(jMenuItem_Compile);

        jMenuItem_Run.setText("Run");
        jMenuItem_Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_RunActionPerformed(evt);
            }
        });
        jMenuCompile.add(jMenuItem_Run);

        jMenuBarMainMenu.add(jMenuCompile);

        setJMenuBar(jMenuBarMainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jSplitPaneTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPaneTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECKSTYLE.ON: MagicNumberCheck - This method is generated by NetBeans and we cannot edit it.
    
    
    private void jMenuItem_CompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_CompileActionPerformed
        String[] commandAndParameters = {"emojicodec", "-j greeter.emojic"};
        BufferedReader reader = null;
        BufferedReader error = null;
        try {
            Process process = Runtime.getRuntime().exec(commandAndParameters);
            InputStream in = process.getInputStream();
//            OutputStream out = process.getOutputStream();
            InputStream err = process.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            error = new BufferedReader(new InputStreamReader(err, StandardCharsets.UTF_8));
//            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
//            writer.println("ls -al");
//            writer.close();
            String line;
            while ((line = reader.readLine()) != null) {
                jTextAreaOutput.append(line);
                jTextAreaOutput.append(System.lineSeparator());
//                System.out.println(line);
            }
            while ((line = error.readLine()) != null) {
                jTextAreaOutput.append(line);
                jTextAreaOutput.append(System.lineSeparator());
//                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (error != null) {
                try {
                    error.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem_CompileActionPerformed

    private void jMenuItem_RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_RunActionPerformed
        String[] commandAndParameters = {"emojicode", "greeter.emojib"};
        BufferedReader reader = null;
        BufferedReader error = null;
        try {
            Process process = Runtime.getRuntime().exec(commandAndParameters);
            InputStream in = process.getInputStream();
//            OutputStream out = process.getOutputStream();
            InputStream err = process.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            error = new BufferedReader(new InputStreamReader(err, StandardCharsets.UTF_8));
//            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
//            writer.println("ls -al");
//            writer.close();
            String line;
            while ((line = reader.readLine()) != null) {
                jTextAreaOutput.append(line);
                jTextAreaOutput.append(System.lineSeparator());
//                System.out.println(line);
            }
            while ((line = error.readLine()) != null) {
                jTextAreaOutput.append(line);
                jTextAreaOutput.append(System.lineSeparator());
//                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (error != null) {
                try {
                    error.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem_RunActionPerformed

    final class ToolbarClickAction extends AbstractAction {
        
        public ToolbarClickAction(String text, String htmlDocumentation) {
            super(text);
            if (htmlDocumentation == null)
                htmlDocumentation = "No description";
            putValue(SHORT_DESCRIPTION, htmlDocumentation);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action [" + getValue(NAME) + "] performed!\n");
            /*            
            try {
                pane.getStyledDocument().insertString(0,
                        "Action [" + getValue(NAME) + "] performed!\n", null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             */
        }
    }
    
    
    private class ComboboxActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
/*            
            JComboBox combobox = (JComboBox) e.getSource();
            EmojicodePackage selectedEmojicodePackage = (EmojicodePackage) combobox.getSelectedItem();
            EmojicodeDocumentation.getInstance().getEmojicodePackages().forEach((emojicodePackage) -> {
                boolean visible = (selectedEmojicodePackage == emojicodePackage);
                emojicodePackage.getToolbars().forEach((toolbar) -> {
                    toolbar.setVisible(visible);
                });
            });
*/
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelMakeToolbarVisible;
    private javax.swing.JMenuBar jMenuBarMainMenu;
    private javax.swing.JMenu jMenuCompile;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem_Compile;
    private javax.swing.JMenuItem jMenuItem_Run;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelOutput;
    private javax.swing.JPanel jPanelToolbar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPaneOutput;
    private javax.swing.JSplitPane jSplitPaneTree;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextPane jTextPaneEditor;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables


    //CHECKSTYLE.ON: LineLengthCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    //CHECKSTYLE.ON: WhitespaceAroundCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    //CHECKSTYLE.ON: FinalParametersCheck - Several methods in this class is created by the
    // NetBeans IDE and we cannot change them.
    
    /**
     * This class may not be serialized so throw an exception.
     * @param oos the object stream
     * @throws IOException this method always throws an IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("This class is NOT serializable.");
    }
    
}
