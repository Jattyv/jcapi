/* 
 * Copyright (C) 2016 Dimitrios Diamantidis <Dimitri.dia@ledimi.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jattyv.jcapi.util.crypt;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CryptUtils {
    
    public static String encrypt(String text, PublicKey pub){
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, pub);
            return Base64.getEncoder().encodeToString(rsaCipher.doFinal(text.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static String decrypt(String cipherText, PrivateKey priv){
                try {
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, priv);
            byte[] base64text = Base64.getDecoder().decode(cipherText.getBytes());
            return new String(rsaCipher.doFinal(base64text));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static String encrypt(String text, SecretKey key){
        try {
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherBytes = aesCipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(cipherBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String decrypt(String cipherText, SecretKey key){
        try {
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] base64text = Base64.getDecoder().decode(cipherText.getBytes());
            return new String(aesCipher.doFinal(base64text));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static SecretKey generateAESKey() {
        SecretKey key = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.init(128, rand);
            key = keyGen.generateKey();
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    public static KeyPair generateKeyPair() {
        KeyPair pair = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, rand);
            pair = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pair;
    }

}
