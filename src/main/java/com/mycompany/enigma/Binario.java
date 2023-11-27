package com.mycompany.enigma;

class Binario extends Encryption {
    private final String[] binaryAlphabet = {
            "00000", "00001", "00010", "00011", "00100", "00101", "00110",
            "00111", "01000", "01001", "01010", "01011", "01100", "01101",
            "01110", "01111", "10000", "10001", "10010", "10011", "10100",
            "10101", "10110", "10111", "11000", "11001"
    };

    @Override
    public String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        String[] words = text.toLowerCase().split("\\s+");

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (currentChar == '*') {
                    encryptedText.append("*");
                } else {
                    int index = currentChar - 'a';
                    encryptedText.append(binaryAlphabet[index]).append(" ");
                }
            }
            encryptedText.append("* ");
        }
        return encryptedText.toString().trim();
    }

    @Override
    public String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();
        String[] words = text.split("\\*");

        for (String word : words) {
            String[] binaryCodes = word.trim().split("\\s+");
            for (String code : binaryCodes) {
                if (!code.isEmpty()) {
                    int index = 0;
                    for (int i = 0; i < binaryAlphabet.length; i++) {
                        if (binaryAlphabet[i].equals(code)) {
                            index = i;
                            break;
                        }
                    }
                    decryptedText.append((char) (index + 'a'));
                } else {
                    decryptedText.append(" ");
                }
            }
            decryptedText.append(" ");
        }
        return decryptedText.toString().trim();
    }
}
