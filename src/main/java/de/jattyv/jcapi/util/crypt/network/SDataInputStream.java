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
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class SDataInputStream {

    private final DataInputStream in;
    private PrivateKey priv;

    public SDataInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    public String receiveUTF() throws IOException {
        if (priv == null) {
            return in.readUTF();
        } else {
            String tmp = in.readUTF();
            String keyAsString = CryptUtils.decrypt(in.readUTF(), priv);
            SecretKey key = CryptUtils.StringToSecretKey(keyAsString);
            return CryptUtils.decrypt(tmp, key);
        }
    }

    public void setPriv(PrivateKey key) {
        this.priv = key;
    }

}
