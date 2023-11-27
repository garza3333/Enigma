package com.mycompany.enigma;

abstract class EncryptStringParameter {
    
    public boolean isAlphaString(String str){
        return str != null && str.matches("[a-zA-Z ]+");
    }
    
    // Abstract methods
    
    public abstract String encrypt(String message, String key);
    
    public abstract String decrypt(String message, String key);
    
}
