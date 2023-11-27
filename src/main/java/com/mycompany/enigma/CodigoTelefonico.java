package com.mycompany.enigma;

class CodigoTelefonico extends Encryption {
    private final String[] phoneKeys = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
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
                    for (int j = 2; j < phoneKeys.length; j++) {
                        if (phoneKeys[j].indexOf(currentChar) != -1) {
                            int key = j;
                            int position = phoneKeys[j].indexOf(currentChar) + 1;
                            encryptedText.append(key).append(position).append(" ");
                            break;
                        }
                    }
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
            String[] codes = word.trim().split("\\s+");
            for (String code : codes) {
                if (!code.isEmpty()) {
                    int key = Integer.parseInt(code.substring(0, 1));
                    int position = Integer.parseInt(code.substring(1));
                    decryptedText.append(phoneKeys[key].charAt(position - 1));
                } else {
                    decryptedText.append(" ");
                }
            }
            decryptedText.append(" ");
        }
        return decryptedText.toString().trim();
    }
}
