package com.notepad.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
	private EncryptionStrategy encryptionStrategy;

	public FileHandler(EncryptionStrategy encryptionStrategy) {
		this.encryptionStrategy = encryptionStrategy;
	}

	public void saveFile(File file, String content) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			String encrypted = encryptionStrategy.encrypt(content);
			fileOutputStream.write(encrypted.getBytes());
			fileOutputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();;
		}
	}

	public String openFile(File file, boolean shouldDecrypt) {
		try {
			StringBuffer text = new StringBuffer();
			FileInputStream fileInputStream = new FileInputStream(file);
			int character;
			while ((character = fileInputStream.read()) != -1) {
				text.append((char) character);
			}
			fileInputStream.close();

			return shouldDecrypt ? encryptionStrategy.decrypt(text.toString()) : text.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
