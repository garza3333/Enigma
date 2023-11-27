package com.mycompany.enigma;

abstract class EncryptIntParameter {
    
    public boolean isAlphaString(String str) {
        return str != null && str.matches("[a-zA-Z ]+");
    }

    public boolean isTwoDigitInt(int number) {
    try {
        String str = String.valueOf(number);
        return str.length() == 2 && number >= 10 && number <= 99;
    } catch (NumberFormatException | NullPointerException e) {
        return false;
    }
}
    
    // Abstract methods
    
    public abstract String encrypt(String message, int key);
    
    public abstract String decrypt(String message, int key);
    
}
