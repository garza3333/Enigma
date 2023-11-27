package com.mycompany.enigma;

class Vigenere extends EncryptIntParameter {
    
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String encrypt(String text, int key) {
        

        // Verificación del texto y la clave
        if (!isAlphaString(text)) {
            return "El texto debe ser una cadena de caracteres y no puede ser numérico.";
        }

        if (!isTwoDigitInt(key)) {
            return "La clave debe ser numérica y contener exactamente 2 dígitos.";
        }

        int first = key/10;
        int second = key%10;
        
        StringBuilder encryptedText = new StringBuilder();
        String[] words = text.split(" "); // Dividir el texto en palabras

        for (String word : words) {
            StringBuilder encodedWord = new StringBuilder();
            int keyIndex = 0;

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                int charValue = alphabet.indexOf(Character.toLowerCase(currentChar)) + 1;

                int offset = (keyIndex % 2 == 0) ? first : second;
                int encodedValue = charValue + offset;

                if (encodedValue > 26) {
                    encodedValue -= 26;
                }

                char encodedChar = alphabet.charAt(encodedValue - 1);
                if (Character.isUpperCase(currentChar)) {
                    encodedChar = Character.toUpperCase(encodedChar);
                }

                encodedWord.append(encodedChar);
                keyIndex++;
            }

            encryptedText.append(encodedWord).append(" ");
        }

        return encryptedText.toString().trim();
    }

    @Override
    public String decrypt(String text, int key) {
        
        StringBuilder decryptedText = new StringBuilder();
        String[] words = text.split(" "); // Dividir el texto en palabras
        
        int first = key/10;
        int second = key%10;

        for (String word : words) {
            StringBuilder decodedWord = new StringBuilder();
            int keyIndex = 0;

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                int charValue = alphabet.indexOf(Character.toLowerCase(currentChar)) + 1;
                
                int offset = (keyIndex % 2 == 0) ? first : second;
                int decodedValue = charValue - offset;

                if (decodedValue <= 0) {
                    decodedValue += 26;
                }

                char decodedChar = alphabet.charAt(decodedValue - 1);
                if (Character.isUpperCase(currentChar)) {
                    decodedChar = Character.toUpperCase(decodedChar);
                }

                decodedWord.append(decodedChar);
                keyIndex++;
            }

            decryptedText.append(decodedWord).append(" ");
        }

        return decryptedText.toString().trim();
    }
}
