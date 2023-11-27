package com.mycompany.enigma;

import java.math.BigInteger;

public class Enigma {

    public static void main(String[] args) throws Exception {
        
        // Tests 
        
        // Test cesar
        Cesar c = new Cesar();
        System.out.println(c.encrypt("tarea programada"));
        
        // Test Llave
        
        Llave l = new Llave();
        System.out.println(l.encrypt("tarea programada de codificacion", "tango"));
        
        // Test Vigenere
        
        Vigenere v = new Vigenere();
        System.out.println(v.encrypt("tarea programada", 23));
        
        // Test Palabra Inversa
        
        PalabraInversa pi = new PalabraInversa();
        System.out.println(pi.encrypt("esto es un secreto no lo puedo decir aserpros"));

        // Test Mensaje Inverso
        
        MensajeInverso mi = new MensajeInverso();
        System.out.println(mi.encrypt("Hola mi nombre es Python"));
        
        // Test Cifrado Telefonico
        
        CodigoTelefonico ct = new CodigoTelefonico();
        System.out.println(ct.encrypt("tarea programada criptografia de datos zygalski Henryk"));
        
        // Test Codificacion Binaria
        
        Binario bin = new Binario();
        System.out.println(bin.encrypt("tarea programada criptografia datos zygalski Henryk"));
        
        
        // Test RSA
        
        RSA rsa = new RSA();
        System.out.println(rsa.encrypt("HOLA"));
        System.out.println(rsa.decrypt(rsa.encrypt("HOLA")));
        
        // Test DES
        
        DES des = new DES();
        System.out.println(des.encrypt("Hola tarea programada"));
        
        // Test AES
        
        AES aes = new AES();
        System.out.println(aes.encrypt("Hola tarea programada"));
        
        
        
        
        
        //new GUI().setVisible(true);
        
    }
}
