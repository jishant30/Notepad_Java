package com.notepad.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class NotepadFrame extends JFrame {
	private EditorPanel editorPanel;
	public NotepadFrame() {
		this.editorPanel = new EditorPanel();
		this.setLayout(new BorderLayout());
		this.add(editorPanel, BorderLayout.CENTER);

		this.setJMenuBar(new MenuBar(editorPanel));
		this.revalidate();
	}
}