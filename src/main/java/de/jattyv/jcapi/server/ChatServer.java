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
package de.jattyv.jcapi.server;

import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jfc.JattyvFileHandler;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.server.network.Server;
import de.jattyv.jcapi.server.virtual.dataController.LocalDataController;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ChatServer {

    private Server server;
    private JattyvServer jServer;
    public static JattyvFileController jfc = null;

    
    public ChatServer(JattyvFileHandler fileHandler) {
        jfc = new JattyvFileController(fileHandler);
        Settings settings = jfc.readSettings();
        server = new Server(settings);
        jServer = new JattyvServer(server);
    }

    public ChatServer(int port) {
        server = new Server(port);
        jServer = new JattyvServer(server);
    }

    public ChatServer(Settings settings) {
        server = new Server(settings);
        jServer = new JattyvServer(server);
    }

    public void start() {
        jServer.start();
    }
    
    public void shutdown(){
        LocalDataController.writeDC();
    }

}
