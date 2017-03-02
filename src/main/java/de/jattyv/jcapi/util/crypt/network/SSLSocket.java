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

import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.network.JClient;
import de.jattyv.jcapi.util.crypt.CryptUtils;
import de.jattyv.jcapi.util.crypt.Hasher;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public final class SSLSocket {

    private final Socket socket;
    private final SDataInputStream in;
    private final SDataOutputStream out;
    private KeyPair keys;

    private JClient cl;

    private final String SSL_SUCCESS = "HandshakeSucessfull";
    private final String SSL_CERT_RENEW = "RenewCert";

    public SSLSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        in = new SDataInputStream(socket.getInputStream());
        out = new SDataOutputStream(socket.getOutputStream());
        handshakeC();
    }

    public SSLSocket(String ip, int port, JClient cl) throws IOException {
        socket = new Socket(ip, port);
        in = new SDataInputStream(socket.getInputStream());
        out = new SDataOutputStream(socket.getOutputStream());
        this.cl = cl;
        handshakeC();
    }

    public SSLSocket(Socket socket, KeyPair pair) throws IOException {
        this.socket = socket;
        in = new SDataInputStream(socket.getInputStream());
        out = new SDataOutputStream(socket.getOutputStream());
        handshakeS(pair);
    }

    protected void handshakeC() {
        try {
            String pubAsString = in.receiveUTF();
            PublicKey pub = CryptUtils.StringToPublicKey(pubAsString);
            if (cl != null) {
                if (!cl.checkCert(pub)) {
                    close();
                }
            }
            keys = CryptUtils.generateKeyPair();
            out.setPub(pub);
            SecretKey key = CryptUtils.generateAESKey();
            keys = CryptUtils.generateKeyPair(key.getEncoded());
            out.send(CryptUtils.KeyToString(key));
            out.setPub(keys.getPublic());
            in.setPriv(keys.getPrivate());
            out.send(this.SSL_SUCCESS);
            String answer = in.receiveUTF();
            if (!answer.equals(this.SSL_SUCCESS)) {
                close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SSLSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void handshakeS(KeyPair pair) {
        try {
            out.send(CryptUtils.PublicKeyToString(pair.getPublic()));
            in.setPriv(pair.getPrivate());
            SecretKey key = CryptUtils.StringToKey(in.receiveUTF());
            keys = CryptUtils.generateKeyPair(key.getEncoded());
            out.setPub(keys.getPublic());
            in.setPriv(keys.getPrivate());
            String answer = in.receiveUTF();
            if (!answer.equals(this.SSL_SUCCESS)) {
                close();
            }
            out.send(this.SSL_SUCCESS);
        } catch (IOException ex) {
            Logger.getLogger(SSLSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public SDataInputStream getIn() {
        return in;
    }

    public SDataOutputStream getOut() {
        return out;
    }

    public void close() throws IOException {
        socket.close();
    }

}
