package com.mycompany.enigma;

import java.math.BigInteger;
import java.util.Random;


class RSA extends EncryptionRSA {
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public RSA() {
        generateKeys();
    }

    public void generateKeys() {
        Random rand = new Random();
        BigInteger p = BigInteger.valueOf(getRandomPrime(rand));
        BigInteger q = BigInteger.valueOf(getRandomPrime(rand));

        n = p.multiply(q);

        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            e = BigInteger.valueOf(rand.nextInt(phiN.intValue())).add(BigInteger.ONE);
        } while (!e.gcd(phiN).equals(BigInteger.ONE));

        d = e.modInverse(phiN);
    }

    public int getRandomPrime(Random rand) {
        int primeCandidate;
        do {
            primeCandidate = rand.nextInt(1000) + 50;
        } while (!isPrime(primeCandidate));

        return primeCandidate;
    }

    public boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i = i + 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }

        return true;
    }

    @Override
    public String encrypt(String message) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : message.toCharArray()) {
            BigInteger plaintext = BigInteger.valueOf((int) character);
            BigInteger encrypted = plaintext.modPow(e, n);
            encryptedText.append(encrypted).append("*");
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedMessage) {
        StringBuilder decryptedText = new StringBuilder();
        String[] parts = encryptedMessage.split("\\*");

        for (String part : parts) {
            BigInteger encrypted = new BigInteger(part);
            BigInteger decrypted = encrypted.modPow(d, n);
            decryptedText.append((char) decrypted.intValue());
        }

        return decryptedText.toString();
    }
}
