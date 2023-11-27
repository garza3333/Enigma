package com.mycompany.enigma;

public class EncryptManager {
    public Cesar c;
    public Llave l;
    public Vigenere v;
    public PalabraInversa pi;
    public MensajeInverso mi;
    public CodigoTelefonico ct;
    public Binario bin;
    public RSA rsa;
    public DES des;
    public AES aes;
    public EncryptManager(){
        
        c = new Cesar();
        l = new Llave();
        v = new Vigenere();
        pi = new PalabraInversa();
        mi = new MensajeInverso();
        ct = new CodigoTelefonico();
        bin = new Binario();
        rsa = new RSA();
        des = new DES();
        aes = new AES();
        
    }
    
}
