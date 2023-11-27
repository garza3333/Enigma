package com.mycompany.enigma;


class MensajeInverso extends Encryption {
    @Override
    public String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            encryptedText.append(text.charAt(i));
        }
        
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String text) {
        // El método de desencriptación para palabra inversa es igual al de encriptación
        return encrypt(text);
    }
}
