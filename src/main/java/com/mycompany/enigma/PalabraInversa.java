package com.mycompany.enigma;

class PalabraInversa extends Encryption {
    @Override
    public String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            encryptedText.append(text.charAt(i));
        }
        String[] palabras = encryptedText.toString().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (int i = palabras.length - 1; i >= 0; i--) {
            resultado.append(palabras[i]).append(" ");
        }
        
        return resultado.toString().trim();
    }

    @Override
    public String decrypt(String text) {
        // El método de desencriptación para palabra inversa es igual al de encriptación
        return encrypt(text);
    }
}
