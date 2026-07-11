package com.notepad.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.*;
import java.awt.Font;

import com.notepad.settings.*;

public class PreferencesDialog extends JDialog {
	private JTextField fontSizeField;
	private JComboBox<String> themeComboBox;
	private PreferencesManager preferencesManager;
	private EditorPanel editorPanel;

	public PreferencesDialog(EditorPanel editorPanel) {
		this.preferencesManager = PreferencesManager.getInstance();
		this.editorPanel = editorPanel;
		this.setTitle("Preferences");
		this.setLayout(new BorderLayout());
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		panel.add(new JLabel("Font Size"));
		fontSizeField = new JTextField();
		fontSizeField.setText(String.valueOf(preferencesManager.getFontSize()));
		panel.add(fontSizeField);

		panel.add(new JLabel("Theme"));
		themeComboBox = new JComboBox<>(new String[] {"Light", "Dark"});
		themeComboBox.setSelectedItem(preferencesManager.getTheme());
		panel.add(themeComboBox);
		panel.add(new JLabel(""));

		JButton applyButton = new JButton("Apply");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(applyButton);
		panel.add(buttonPanel);

		add(panel, BorderLayout.CENTER);

		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyPreferences();
			}
		});
	}

	private void applyPreferences() {
		int fontSize = Integer.parseInt(this.fontSizeField.getText());
		String theme = this.themeComboBox.getSelectedItem().toString();

		preferencesManager.setFontSize(fontSize);
		preferencesManager.setTheme(theme);

		this.editorPanel.updatePreferences();

		dispose();
	}
}