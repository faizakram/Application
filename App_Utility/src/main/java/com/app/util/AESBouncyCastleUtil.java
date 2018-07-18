package com.app.util;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.app.util.constant.CommonConstants;
import com.app.util.reader.PropertyReader;

@Component
public class AESBouncyCastleUtil {
    
    
    private PropertyReader systemPropertyReader;
    
    private BlockCipher AESCipher;
    
    private PaddedBufferedBlockCipher pbbc;
    
    private KeyParameter key;
    
    @Autowired
    public AESBouncyCastleUtil(@Qualifier(CommonConstants.APPLICATION_PROPERTY_READER) PropertyReader systemPropertyReader){
        
        this.systemPropertyReader = systemPropertyReader;
        
        AESCipher = new AESEngine();
        pbbc = new PaddedBufferedBlockCipher(AESCipher, new PKCS7Padding());
        SecretKey sk = new SecretKeySpec(this.systemPropertyReader.getProperty("SECRET_KEY").getBytes(), "AES");
        key = new KeyParameter(sk.getEncoded());
    }
    
    public byte[] encrypt(byte[] input)
            throws InvalidCipherTextException {
        return processing(input, true);
    }
 
    public byte[] decrypt(byte[] input)
            throws InvalidCipherTextException {
        return processing(input, false);
    }
 
    private byte[] processing(byte[] input, boolean encrypt)
            throws InvalidCipherTextException {
 
        pbbc.init(encrypt, key);
 
        byte[] output = new byte[pbbc.getOutputSize(input.length)];
        int bytesWrittenOut = pbbc.processBytes(
            input, 0, input.length, output, 0);
 
        pbbc.doFinal(output, bytesWrittenOut);
 
        return output;
 
    }
 
}