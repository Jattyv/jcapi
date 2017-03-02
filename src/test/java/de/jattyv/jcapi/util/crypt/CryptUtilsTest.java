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

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.SecretKey;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CryptUtilsTest {

    String text = "I love Cookies";

    @Test
    public void AESTest() {
        SecretKey key = CryptUtils.generateAESKey();
        String cipherText = CryptUtils.encrypt(text, key);
        String decText = CryptUtils.decrypt(cipherText, key);
        assertEquals(text, decText);
    }

    @Test
    public void RSATest() {
        KeyPair keys = CryptUtils.generateKeyPair();
        String cipherText = CryptUtils.encrypt(text, keys.getPublic());
        String decText = CryptUtils.decrypt(cipherText, keys.getPrivate());
        assertEquals(decText, text);
    }
    
    @Test
    public void AESTransformingTest(){
        SecretKey key = CryptUtils.generateAESKey();
        KeyPair keys = CryptUtils.generateKeyPair();
        String cipherText = CryptUtils.encrypt(CryptUtils.KeyToString(key), keys.getPublic());
        SecretKey decKey = CryptUtils.StringToKey(CryptUtils.decrypt(cipherText, keys.getPrivate()));
        String decKeyText = CryptUtils.encrypt(CryptUtils.KeyToString(decKey), keys.getPublic());
        String t1 = CryptUtils.encrypt(text, key);
        String t2 = CryptUtils.encrypt(text, decKey);
        assertEquals(t1, t2);
    }
    
    @Test
    public void RSATransformingTest(){
        KeyPair keys = CryptUtils.generateKeyPair();
        String pubAsString = CryptUtils.PublicKeyToString(keys.getPublic());
        PublicKey StringAsPub = CryptUtils.StringToPublicKey(pubAsString);
        String toTestString = CryptUtils.PublicKeyToString(StringAsPub);
        assertEquals(pubAsString, toTestString);
    }
    
    @Test
    public void SameRSAPairTest(){
        SecretKey key = CryptUtils.generateAESKey();
        KeyPair keys1 = CryptUtils.generateKeyPair(key.getEncoded());
        String encText = CryptUtils.encrypt(text, keys1.getPublic());
        KeyPair keys2 = CryptUtils.generateKeyPair(key.getEncoded());
        String decText = CryptUtils.decrypt(encText, keys2.getPrivate());
        assertEquals(text, decText);
    }

}
