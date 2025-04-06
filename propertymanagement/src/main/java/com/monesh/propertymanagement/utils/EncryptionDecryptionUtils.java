package com.monesh.propertymanagement.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.monesh.propertymanagement.properties.EncryptionProperties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class EncryptionDecryptionUtils {

    private final EncryptionProperties encryptionProperties;

    public String encrypt(String input) throws Exception {
        SecretKeySpec key = new SecretKeySpec(encryptionProperties.getSecretKey().getBytes(),
                encryptionProperties.getAlgorithm());
        Cipher cipher = Cipher.getInstance(encryptionProperties.getTransformation());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String input) throws Exception {
        SecretKeySpec key = new SecretKeySpec(encryptionProperties.getSecretKey().getBytes(),
                encryptionProperties.getAlgorithm());
        Cipher cipher = Cipher.getInstance(encryptionProperties.getTransformation());
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
