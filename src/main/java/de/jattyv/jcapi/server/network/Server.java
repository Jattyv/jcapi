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
package de.jattyv.jcapi.server.network;

import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.server.network.data.Connection;
import de.jattyv.jcapi.util.crypt.CryptUtils;
import de.jattyv.jcapi.util.crypt.network.SSLServer;
import de.jattyv.jcapi.util.crypt.network.SSLSocket;
import java.io.IOException;
import java.security.KeyPair;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Server extends JServer {

    private SSLServer listener;

    public Server(int port) {
        super(port);
    }

    public Server(Settings settings) {
        super(settings);
    }

    @Override
    public void listen() {
        try {
            listener = new SSLServer(port, keys);
            while (running) {
                SSLSocket s = listener.accept();
                ServerThread st = new ServerThread(dc);
                Connection c = new Connection(st, s);
                st.setCon(c);
                Client cl = new Client(st);
                cl.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(de.jattyv.jcapi.server.network.JServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
