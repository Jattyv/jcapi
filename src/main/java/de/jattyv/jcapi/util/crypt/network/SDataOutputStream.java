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
package de.jattyv.jcapi.util.crypt.network;

import de.jattyv.jcapi.util.crypt.CryptUtils;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class SDataOutputStream {

    private DataOutputStream out;
    private PublicKey pub;

    public SDataOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void send(String msg) throws IOException {
        if (pub == null) {
            out.writeUTF(msg);
        } else {
            SecretKey key = CryptUtils.generateAESKey();
            String keyAsString = CryptUtils.KeyToString(key);
            out.writeUTF(CryptUtils.encrypt(msg, key));
            out.writeUTF(CryptUtils.encrypt(keyAsString, pub));
        }
    }

    public void setPub(PublicKey key) {
        this.pub = key;
    }

}
