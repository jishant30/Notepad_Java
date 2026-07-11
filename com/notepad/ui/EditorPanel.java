package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.notepad.settings.PreferencesManager;

public class EditorPanel extends JPanel {
	private PreferencesManager preferencesManager;
	private JTextArea textArea;
	public EditorPanel() {
		this.preferencesManager = PreferencesManager.getInstance();
		this.setLayout(new BorderLayout());
		this.textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		updatePreferences();
	}

	public void updatePreferences() {
		int fontSize = preferencesManager.getFontSize();
		String theme = preferencesManager.getTheme();

		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));

		if("Dark".equals(theme)) {
			textArea.setForeground(Color.WHITE);
			textArea.setBackground(Color.BLACK);
		} else {
			textArea.setForeground(Color.BLACK);
			textArea.setBackground(Color.WHITE);
		}
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}