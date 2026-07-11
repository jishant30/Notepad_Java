package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FindReplaceDialog extends JDialog implements ActionListener {

    private EditorPanel editorPanel;
    private boolean isReplace;
    private JTextField findTextField;
    private JTextField replaceTextField;
    private JButton findNextButton;
    private JButton replaceButton;
    private JButton replaceAllButton;

    public FindReplaceDialog(EditorPanel editorPanel, boolean isReplace) {
        this.editorPanel = editorPanel;
        this.isReplace = isReplace;

        this.setTitle(this.isReplace ? "Find and Replace" : "Find");
        this.setSize(400, 200);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(editorPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(isReplace ? 3 : 2, 2));

        panel.add(new JLabel("Find"));
        this.findTextField = new JTextField();
        panel.add(this.findTextField);

        if (isReplace) {
            panel.add(new JLabel("Replace"));
            this.replaceTextField = new JTextField();
            panel.add(this.replaceTextField);
        }

        JPanel buttonPanel = new JPanel();

        this.findNextButton = new JButton("Find Next");
        buttonPanel.add(this.findNextButton);

        if (isReplace) {
            this.replaceButton = new JButton("Replace");
            this.replaceAllButton = new JButton("Replace All");

            buttonPanel.add(this.replaceButton);
            buttonPanel.add(this.replaceAllButton);

            this.replaceButton.addActionListener(this);
            this.replaceAllButton.addActionListener(this);
        }

        this.findNextButton.addActionListener(this);

        this.add(panel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == findNextButton) {
            findNext();
        } else if (source == replaceButton) {
            replace();
        } else if (source == replaceAllButton) {
            replaceAll();
        }
    }

    private void findNext() {
        String findText = this.findTextField.getText();
        String text = this.editorPanel.getTextArea().getText();
        int caretPosition = this.editorPanel.getTextArea().getCaretPosition();

        int searchIndex = text.indexOf(findText, caretPosition);

        if (searchIndex == -1) {
            JOptionPane.showMessageDialog(this, "Text Not Found!");
        } else {
            this.editorPanel.getTextArea().setSelectionStart(searchIndex);
            this.editorPanel.getTextArea().setSelectionEnd(searchIndex + findText.length());
        }
    }

    private void replace() {
        String findText = this.findTextField.getText();
        String replaceText = this.replaceTextField.getText();

        String selectedText = this.editorPanel.getTextArea().getSelectedText();

        if (selectedText != null && selectedText.equals(findText)) {
            this.editorPanel.getTextArea().replaceSelection(replaceText);
        }

        findNext();
    }

    private void replaceAll() {
        String findText = this.findTextField.getText();
        String replaceText = this.replaceTextField.getText();

        String text = this.editorPanel.getTextArea().getText();
        text = text.replace(findText, replaceText);

        this.editorPanel.getTextArea().setText(text);

        JOptionPane.showMessageDialog(this, "All Occurrences Replaced!");
    }
}