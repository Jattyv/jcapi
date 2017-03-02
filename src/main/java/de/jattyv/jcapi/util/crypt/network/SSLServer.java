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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class SSLServer {
    
    private ServerSocket server;
    private KeyPair keys;
    
    public SSLServer(int port, KeyPair keys) throws IOException{
        server = new ServerSocket(port);
        this.keys = keys;
    }
    
    public SSLSocket accept() throws IOException{
        Socket s = server.accept();
        SSLSocket socket = new SSLSocket(s, keys);
        return socket;
    }
    
    public void close() throws IOException{
        server.close();
    }
    
}
