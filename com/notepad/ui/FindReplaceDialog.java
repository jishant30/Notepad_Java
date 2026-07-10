package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindReplaceDialog extends JDialog {
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
	

		if(isReplace) {
			panel.add(new JLabel("Replace"));
			this.replaceTextField = new JTextField();
			panel.add(this.replaceTextField);
		}

		JPanel buttonPanel = new JPanel();
		this.findNextButton = new JButton("Find Next");
		buttonPanel.add(this.findNextButton);

		if(isReplace) {
			this.replaceButton = new JButton("Replace");
			this.replaceAllButton = new JButton("Replace All");
			buttonPanel.add(replaceButton);
			buttonPanel.add(replaceAllButton);
		} 

		this.add(panel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}