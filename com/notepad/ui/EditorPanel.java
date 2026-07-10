package com.notepad.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorPanel extends JPanel {
	private JTextArea textArea;
	public EditorPanel() {
		this.setLayout(new BorderLayout());
		this.textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}