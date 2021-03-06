/*
 * Copyright (C) 2017 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Hasher {

    /**
     * Rounds for the hashing.
     */
    private static final int ITERATIONS = 1000;
    /**
     * The length of the key.
     */
    private static final int KEY_LENGTH = 1024;

    /**
     * Generates a logkey from the user name and the user password. Uses the
     * time as salt. The logkey is pbkdf2 hashed.
     *
     * @param uName The needed user name.
     * @param uPassword The needed user password.
     * @return The hashed logkey.
     */
    public static String hashPassword(String uName, String uPassword) {
        char[] passwordChars = uPassword.toCharArray();
        byte[] saltBytes = uName.getBytes();
        byte[] hashedPassword = null;
        try {
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedPassword = key.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(LKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return String.format("%x", new BigInteger(hashedPassword));
    }

    public static String generateMD5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
