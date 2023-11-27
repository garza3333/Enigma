package com.mycompany.enigma;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DES {
    private final String ALGORITHM = "DESede"; // Triple DES algorithm

    // Key must be 24 bytes long
    private final byte[] KEY_BYTES = {
        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
        0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10,
        0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
    };

    // Encrypt function
    public String encrypt(String plaintext) throws Exception {
        SecretKey key = new SecretKeySpec(KEY_BYTES, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt function
    public String decrypt(String encryptedText) throws Exception {
        SecretKey key = new SecretKeySpec(KEY_BYTES, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decrypted);
    }


}
