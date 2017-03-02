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
package de.jattyv.jcapi.server.network.data;

import de.jattyv.jcapi.server.network.ServerThread;
import de.jattyv.jcapi.util.crypt.network.SDataInputStream;
import de.jattyv.jcapi.util.crypt.network.SDataOutputStream;
import de.jattyv.jcapi.util.crypt.network.SSLSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Connection implements JConnection {
    
    private ServerThread st;
    private SSLSocket socket;
    
    private SDataOutputStream out;
    private SDataInputStream in;
    
    public Connection(ServerThread st, SSLSocket socket) {
        this.socket = socket;
        this.st = st;
        out = socket.getOut();
        in = socket.getIn();
    }
    
    @Override
    public void writeMsg(String msg) {
        try {
            out.send(msg);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public String readMsg() {
        try {
            return in.receiveUTF();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void close() {
        st.close();
    }
    
    @Override
    public void closeCon() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
