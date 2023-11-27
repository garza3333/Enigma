package com.mycompany.enigma;


public class Cesar extends Encryption {
    
    private final String plainAlphabet = "ABCDEFGHIKMNOPQRSTUVWXYZ";
    private final String shiftedAlphabet = "DEFGHIKMNOPQRSTUVWXYZABC";

    @Override
    public String encrypt(String text) {
        text = text.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == 'L' || currentChar == 'Ñ') {
                encryptedText.append(currentChar);
            } else if (currentChar == 'C' && i < text.length() - 1 && text.charAt(i + 1) == 'H') {
                encryptedText.append(currentChar);
            } else if (Character.isLetter(currentChar)) {
                int index = plainAlphabet.indexOf(currentChar);
                char encryptedChar = (index != -1) ? shiftedAlphabet.charAt(index) : currentChar;
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == 'L' || currentChar == 'Ñ') {
                decryptedText.append(currentChar);
            } else if (currentChar == 'C' && i < text.length() - 1 && text.charAt(i + 1) == 'H') {
                decryptedText.append(currentChar);
            } else if (Character.isLetter(currentChar)) {
                int index = shiftedAlphabet.indexOf(currentChar);
                char decryptedChar = (index != -1) ? plainAlphabet.charAt(index) : currentChar;
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(currentChar);
            }
        }
        return decryptedText.toString();
    }
}

