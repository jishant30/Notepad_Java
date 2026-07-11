package com.notepad.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.undo.UndoManager;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

import com.notepad.utils.FileHandler;
import com.notepad.utils.EncryptionStrategy;
import com.notepad.utils.CeaserCipher;

public class MenuBar extends JMenuBar {

	private EditorPanel editorPanel;
	private UndoManager undoManager;
	private FileHandler fileHandler;
	private File currentFile;
	private EncryptionStrategy encryptionStrategy;

	public MenuBar(EditorPanel editorPanel) {
		this.createFileMenu();
		this.createEditMenu();
		this.createSettingsMenu();

		this.editorPanel = editorPanel;

		this.encryptionStrategy = new CeaserCipher(3);
		this.fileHandler = new FileHandler(encryptionStrategy);

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

		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});

		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(false);
			}
		});

		saveAsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(true);
			}
		});
	}

	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				String content;
				if(file.getName().endsWith(".temp")) {
					content = this.fileHandler.openFile(file, true);
				} else {
					content = this.fileHandler.openFile(file, false);
				}
				this.editorPanel.getTextArea().setText(content);
				this.currentFile = file;
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}
		} 
	}

	private void saveFile(boolean saveAs) {
		if(saveAs || currentFile == null) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if(! file.getName().endsWith(".temp")) {
					JOptionPane.showMessageDialog(null, "Can't save this File..");
					return;
				}
				this.fileHandler.saveFile(file, this.editorPanel.getTextArea().getText());
				currentFile = file;
			}
		} else {
			this.fileHandler.saveFile(currentFile, this.editorPanel.getTextArea().getText());
		}
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

		preferencesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreferencesDialog preferencesDialog = new PreferencesDialog(editorPanel);
				preferencesDialog.setVisible(true);
			}
		});
	}
}