/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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
package de.jattyv.jcapi.client.network;

import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.data.jobject.Base;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.util.KeyTags;
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
public class Client extends JClient implements KeyTags {

    private SSLSocket socket;
    private SDataOutputStream out;
    private SDataInputStream in;

    protected Reload reload;

    public Client(String host, int port) {
        super(host, port);
    }

    public Client(Settings settings) {
        super(settings);

    }

    @Override
    public void start(Container c) {
        try {
            socket = new SSLSocket(host, port);
            in = socket.getIn();
            out = socket.getOut();
            write(c);
            reload = new Reload(this);
            reload.start();
        } catch (IOException ie) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ie);
            close();
        }

    }

    @Override
    public void write(Container c) {
        try {
            out.send(gson.toJson(c));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
    }

    @Override
    public void write(String s) {
        try {
            out.send(s);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
    }

    @Override
    public String read() {
        String input = "";
        try {
            input = in.receiveUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
        return input;
    }

    @Override
    public void reload() {
        try {
            String input = in.receiveUTF();
            Base b = gson.fromJson(input, Base.class);
            handler.handle(b);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
            handler.getWindow().alert(CON_FAIL, JGui.ALERT_TYPE_INFO);
            reload.stop();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
