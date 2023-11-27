package com.mycompany.enigma;


public class Llave extends EncryptStringParameter {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String encrypt(String text, String keyword) {
        
        if(!(this.isAlphaString(text) && this.isAlphaString(keyword))){
            return "Text and key must be string and not numeric";
        }else{
            
            StringBuilder encryptedText = new StringBuilder();
        String[] words = text.split(" "); // Dividir el texto en palabras

        for (String word : words) {
            int keyIndex = 0;
            StringBuilder encodedWord = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                int keyCharValue = keyword.charAt(keyIndex % keyword.length()) - 'a' + 1;
                int charValue = alphabet.indexOf(Character.toLowerCase(currentChar)) + 1;

                int encodedValue = charValue + keyCharValue;
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
        
        
    }

    @Override
    public String decrypt(String text, String keyword) {
        
        if(!(this.isAlphaString(text) && this.isAlphaString(keyword))){
            return "Text and key must be string and not numeric"; 
        }else{
            
            StringBuilder decryptedText = new StringBuilder();
        String[] words = text.split(" "); // Dividir el texto en palabras

        for (String word : words) {
            int keyIndex = 0;
            StringBuilder decodedWord = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                int keyCharValue = keyword.charAt(keyIndex % keyword.length()) - 'a' + 1;
                int charValue = alphabet.indexOf(Character.toLowerCase(currentChar)) + 1;

                int decodedValue = charValue - keyCharValue;
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
}
