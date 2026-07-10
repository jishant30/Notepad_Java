package com.notepad.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.undo.UndoManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar {

	private EditorPanel editorPanel;
	private UndoManager undoManager;

	public MenuBar(EditorPanel editorPanel) {
		this.createFileMenu();
		this.createEditMenu();
		this.createSettingsMenu();

		this.editorPanel = editorPanel;
		this.undoManager = new UndoManager();
		editorPanel.getTextArea().getDocument().addUndoableEditListener(undoManager);
	}

	private void createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem saveAsItem = new JMenuItem("Save As");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		this.add(fileMenu);
	}

	private void createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem cutItem = new JMenuItem("Cut");
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem pasteItem = new JMenuItem("Paste");
		JMenuItem undoItem = new JMenuItem("Undo");
		JMenuItem redoItem = new JMenuItem("Redo");
		JMenuItem findItem = new JMenuItem("Find");
		JMenuItem replaceItem = new JMenuItem("Replace");
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.add(findItem);
		editMenu.add(replaceItem);
		this.add(editMenu);
	

		cutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.getTextArea().cut();
			}
		});

		copyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.getTextArea().copy();
			}
		});

		pasteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorPanel.getTextArea().paste();
			}
		});


		undoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undoManager.canUndo()) {
					undoManager.undo();
				}
			}
		});	

		redoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undoManager.canRedo()) {
					undoManager.redo();
				}
			}
		});

		findItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindReplaceDialog findReplaceDialog = new FindReplaceDialog(editorPanel, false);
				findReplaceDialog.setVisible(true);
			}
		});

		replaceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindReplaceDialog findReplaceDialog = new FindReplaceDialog(editorPanel, true);
				findReplaceDialog.setVisible(true);
			}
		});
	}

	private void createSettingsMenu() {
		JMenu settingsMenu = new JMenu("Settings");
		JMenuItem preferencesItem = new JMenuItem("Preferences");
		settingsMenu.add(preferencesItem);
		this.add(settingsMenu);
	}
}