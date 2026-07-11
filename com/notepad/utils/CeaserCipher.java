package com.notepad.utils;

public class CeaserCipher implements EncryptionStrategy {
	private int shift;
	public CeaserCipher(int shift) {
		this.shift = shift;
	}
	public String encrypt(String plainText) {
		StringBuilder encrypted = new StringBuilder();
		for(char c: plainText.toCharArray()) {
			char shifted = (char)(c + shift);
			encrypted.append(shifted);
		}
		return encrypted.toString();
	}

	public String decrypt(String cipherText) {
		StringBuilder decrypted = new StringBuilder();
		for(char c: cipherText.toCharArray()) {
			char shifted = (char)(c - shift);
			decrypted.append(shifted);
		}
		return decrypted.toString();
	}
}