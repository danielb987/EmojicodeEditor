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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

/**
 *
 * @author daniel
 */
public final class JFrame_Main extends javax.swing.JFrame {

    /**
     * Creates new form JFrame_Main
     */
    public JFrame_Main() {
        initComponents();
        
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        
        EmojicodeDocumentation.getInstance();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        java.awt.Container pane = getContentPane();
        pane.setLayout(new java.awt.BorderLayout());
        pane.add(jPanel_Toolbar, java.awt.BorderLayout.PAGE_START);
        pane.add(jSplitPane_Tree,java.awt.BorderLayout.CENTER);
        jSplitPane_Tree.setDividerLocation(0.3);
        jSplitPane_Output.setDividerLocation(0.5);
        
        jPanel_Toolbar.setLayout(new WrapLayout());
        jPanel_Toolbar.setSize(500,500);
        
        // This label is only to make the toolbar panel visible in the Netbeans GUI design
        jPanel_Toolbar.remove(jLabel_MakeToolbarVisible);
        
        this.addComponentListener(new java.awt.event.ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                jSplitPane_Tree.setDividerLocation(200);
                jSplitPane_Output.setDividerLocation(0.8);
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
            jTextPane_Editor.setText(new String(encoded, Charset.forName("UTF-8")));
            String text = jTextPane_Editor.getText();
            for (int i = 0; i < text.length(); i++) {
                String str = text.substring(i, i + Character.charCount(text.codePointAt(i)));
                System.out.format("%d: %d, %s\n", i, text.codePointAt(i), str);
            }
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Toolbar = new javax.swing.JPanel();
        jLabel_MakeToolbarVisible = new javax.swing.JLabel();
        jSplitPane_Tree = new javax.swing.JSplitPane();
        jSplitPane_Output = new javax.swing.JSplitPane();
        jPanel_Output = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_Editor = new EmojiTextPane(this);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu_Compile = new javax.swing.JMenu();
        jMenuItem_Compile = new javax.swing.JMenuItem();
        jMenuItem_Run = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EmojicodeEditor");

        jLabel_MakeToolbarVisible.setText("Label to show toolbar panel");

        javax.swing.GroupLayout jPanel_ToolbarLayout = new javax.swing.GroupLayout(jPanel_Toolbar);
        jPanel_Toolbar.setLayout(jPanel_ToolbarLayout);
        jPanel_ToolbarLayout.setHorizontalGroup(
            jPanel_ToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ToolbarLayout.createSequentialGroup()
                .addComponent(jLabel_MakeToolbarVisible)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_ToolbarLayout.setVerticalGroup(
            jPanel_ToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_MakeToolbarVisible)
        );

        jSplitPane_Output.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel_Output.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel_OutputLayout = new javax.swing.GroupLayout(jPanel_Output);
        jPanel_Output.setLayout(jPanel_OutputLayout);
        jPanel_OutputLayout.setHorizontalGroup(
            jPanel_OutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        jPanel_OutputLayout.setVerticalGroup(
            jPanel_OutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        jSplitPane_Output.setRightComponent(jPanel_Output);

        jScrollPane1.setViewportView(jTextPane_Editor);

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

        jSplitPane_Output.setLeftComponent(jSplitPane1);

        jSplitPane_Tree.setRightComponent(jSplitPane_Output);

        jScrollPane3.setViewportView(jTree1);

        jSplitPane_Tree.setLeftComponent(jScrollPane3);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu_Compile.setText("Compile");

        jMenuItem_Compile.setText("Compile");
        jMenuItem_Compile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_CompileActionPerformed(evt);
            }
        });
        jMenu_Compile.add(jMenuItem_Compile);

        jMenuItem_Run.setText("Run");
        jMenuItem_Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_RunActionPerformed(evt);
            }
        });
        jMenu_Compile.add(jMenuItem_Run);

        jMenuBar1.add(jMenu_Compile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jSplitPane_Tree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane_Tree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_CompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_CompileActionPerformed
        String[] commandAndParameters = {"emojicodec", "-j greeter.emojic"};
        try {
            Process process = Runtime.getRuntime().exec(commandAndParameters);
            InputStream in = process.getInputStream();
//            OutputStream out = process.getOutputStream();
            InputStream err = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedReader error = new BufferedReader(new InputStreamReader(err));
//            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
//            writer.println("ls -al");
//            writer.close();
            String line;
            while ((line = reader.readLine()) != null) {
                jTextArea1.append(line);
                jTextArea1.append(System.lineSeparator());
//                System.out.println(line);
            }
            while ((line = error.readLine()) != null) {
                jTextArea1.append(line);
                jTextArea1.append(System.lineSeparator());
//                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem_CompileActionPerformed

    private void jMenuItem_RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_RunActionPerformed
        String[] commandAndParameters = {"emojicode", "greeter.emojib"};
        try {
            Process process = Runtime.getRuntime().exec(commandAndParameters);
            InputStream in = process.getInputStream();
//            OutputStream out = process.getOutputStream();
            InputStream err = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedReader error = new BufferedReader(new InputStreamReader(err));
//            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
//            writer.println("ls -al");
//            writer.close();
            String line;
            while ((line = reader.readLine()) != null) {
                jTextArea1.append(line);
                jTextArea1.append(System.lineSeparator());
//                System.out.println(line);
            }
            while ((line = error.readLine()) != null) {
                jTextArea1.append(line);
                jTextArea1.append(System.lineSeparator());
//                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Main.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel_MakeToolbarVisible;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Compile;
    private javax.swing.JMenuItem jMenuItem_Run;
    private javax.swing.JMenu jMenu_Compile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Output;
    private javax.swing.JPanel jPanel_Toolbar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane_Output;
    private javax.swing.JSplitPane jSplitPane_Tree;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane_Editor;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
