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
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CryptUtils {

    /**
     * Encrypts an with RSA.
     * @param text The text to encrypt.
     * @param pub The needed publickey.
     * @return The encrypted text.
     */
    public static String encrypt(String text, PublicKey pub) {
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

    /**
     * Decrypts an encrypted text with RSA.
     * @param cipherText The encrypted text.
     * @param priv The needed private key.
     * @return The decrypted text.
     */
    public static String decrypt(String cipherText, PrivateKey priv) {
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

    /**
     * Encrypts an text with AES.
     * 
     * @param text The to encrypt.
     * @param key The needed key.
     * @return An encrypted text.
     */
    public static String encrypt(String text, SecretKey key) {
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

    /**
     * Decrypts an encrypted text with AES.
     * 
     * @param cipherText The encrypted text.
     * @param key The needed key.
     * @return An decrypted text.
     */
    public static String decrypt(String cipherText, SecretKey key) {
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

    /**
     * Generates an AES key.
     * 
     * @return An AES key.
     */
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

    /**
     * Generates an RSA keypair.
     * 
     * @return An RSA keypair.
     */
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

    /**
     * Converts an Array of bytes into an key.
     * @param key The key to convert.
     * @return The key.
     */
    public static SecretKey toKey(byte[] key) {
        return new SecretKeySpec(key, 0, key.length, "AES");
    }
    
    /**
     * Converts an Key into String.
     * 
     * @param key The key to convert.
     * @return A string that contains the keyinformation.
     */
    public static String KeyToString(SecretKey key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    /**
     * Converts an String into a Key.
     * 
     * @param key The key to convert.
     * @return The key.
     */
    public static SecretKey StringToKey(String key){
        byte[] decKey = Base64.getDecoder().decode(key);
        return toKey(decKey);
    }
    
    /**
     * Converts a string into a public key.
     * 
     * @param key The needed public key.
     * @return The converted key.
     */
    public static PublicKey StringToPublicKey(String key){
        byte[] decKey = Base64.getDecoder().decode(key);
        return toPublicKey(decKey);
    }
    
    /**
     * Converts an public key into a string.
     * 
     * @param key The needed key to convert.
     * @return The converted key.
     */
    public static String PublicKeyToString(PublicKey key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * Converts an array of bytes into a key.
     * 
     * @param key The needed key to convert.
     * @return The converted key.
     */
    public static PublicKey toPublicKey(byte[] key) {
        KeyFactory kf;
        try {
            kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(new X509EncodedKeySpec(key));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(CryptUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
