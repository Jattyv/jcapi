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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class SSLTest extends Thread {

    SSLServer server;
    SSLSocket socket;

    String msg = "Hallo";

    @Test
    public void SSLConnectionTest() {
        String rmsg = "";
        try {
            server = new SSLServer(36988, CryptUtils.generateKeyPair());
        } catch (IOException ex) {
            Logger.getLogger(SSLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread ttest = new Thread(this);
        ttest.start();
        try {
            socket = new SSLSocket("127.0.0.1", 36988);
            socket.getOut().send(msg);
            rmsg = socket.getIn().receiveUTF();
        } catch (IOException ex) {
            Logger.getLogger(SSLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(msg, rmsg);
    }

    @Override
    public void run() {
        try {
            SSLSocket c1 = server.accept();
            String msg = c1.getIn().receiveUTF();
            c1.getOut().send(msg);
        } catch (IOException ex) {
            Logger.getLogger(SSLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
