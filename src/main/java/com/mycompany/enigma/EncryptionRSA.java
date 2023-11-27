package com.mycompany.enigma;

import java.math.BigInteger;
import java.util.Random;

abstract class EncryptionRSA {
    
    // Abstract methods
    
    public abstract String encrypt(String message);
    
    public abstract String decrypt(String message);
    
    public abstract void generateKeys();
    
    public abstract int getRandomPrime(Random rand);
    
    public abstract boolean isPrime(int number);
    
    
    
    
    
}
