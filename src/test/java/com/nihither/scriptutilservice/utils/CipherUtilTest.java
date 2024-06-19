package com.nihither.scriptutilservice.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class CipherUtilTest {

    private CipherUtil cipherUtil;

    @BeforeEach
    void setUp() {
        cipherUtil = new CipherUtil();
        ReflectionTestUtils.setField(cipherUtil, "encryptionKey", "38cnncewjofj+ivb_kjs27dyw1HdnY63");
    }

    @Test
    void encrypt() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, InvalidKeyException {
        String input = "plain_password";
        String cipherText = cipherUtil.encrypt(input);
        System.out.println(cipherText);
        Assertions.assertEquals("RP979Dp8X18UfAbuT8o1hQ==", cipherText);
    }

    @Test
    void decrypt() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, InvalidKeyException {
        String input = "RP979Dp8X18UfAbuT8o1hQ==";
        String plainText = cipherUtil.decrypt(input);
        System.out.println(plainText);
        Assertions.assertEquals("plain_password", plainText);
    }

    @Test
    void encryptAndBack() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, InvalidKeyException {
        String input = "plain_password";
        String cipherText = cipherUtil.encrypt(input);
        System.out.println(cipherText);
        String plainText = cipherUtil.decrypt(cipherText);
        System.out.println(plainText);
        Assertions.assertEquals(input, plainText);
    }
}
