package com.notepad;

import javax.swing.JFrame;

import com.notepad.ui.NotepadFrame;


public class NotepadApp {
	public static void main(String[] args) {
		JFrame frame = new NotepadFrame();
		frame.setTitle("Notepad");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}	
}