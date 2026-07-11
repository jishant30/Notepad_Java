package com.notepad.utils;

public interface EncryptionStrategy {
	String encrypt(String plainText);

	String decrypt(String cipherText);
}