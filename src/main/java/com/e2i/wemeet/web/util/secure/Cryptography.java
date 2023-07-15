package com.e2i.wemeet.web.util.secure;

public interface Cryptography {
    String encrypt(String text);
    String decrypt(String cipherText);
}
