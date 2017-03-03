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
package de.jattyv.jcapi.server.network;

import de.jattyv.jcapi.client.Chat;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.server.ChatServer;
import de.jattyv.jcapi.server.handler.MReloadHandler;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.util.crypt.CryptUtils;
import java.security.KeyPair;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JServer {

    protected int port;
    protected boolean running;
    protected MReloadHandler reloadHandler;
    protected DataController dc;
    protected KeyPair keys;

    public JServer(Settings settings) {
        init(settings);
    }

    public JServer(int port) {
        Settings settings = new Settings();
        settings.setPort(port);
        init(settings);

    }

    private void init(Settings settings) {
        this.port = settings.getPort();
        running = true;
        dc = new DataController();
        reloadHandler = new MReloadHandler(dc);
        keys = ChatServer.jfc.readServerKeyPair();
        if(keys == null){
            keys = CryptUtils.generateKeyPair();
            ChatServer.jfc.writeServerKeyPair(keys);
        }
    }

    public void listen() {

    }

}
