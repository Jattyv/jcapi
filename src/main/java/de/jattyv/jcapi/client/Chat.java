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
package de.jattyv.jcapi.client;

import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.handler.Handler;
import de.jattyv.jcapi.client.network.Client;
import de.jattyv.jcapi.client.network.JClient;
import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jfc.JattyvFileHandler;
import de.jattyv.jcapi.data.jfc.data.Settings;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Chat {

    private Handler handler;
    private JClient cl;
    public static JattyvFileController jfc = null;

    public Chat(Settings settings) {
        init(settings);
    }

    public Chat(String ip, int port) {
        handler = new Handler();
        cl = new Client(ip, port);
        cl.setHandler(handler);
        handler.setClient(cl);
    }

    public Chat(JattyvFileHandler fileHandler) {
        jfc = new JattyvFileController(fileHandler);
        Settings settings = jfc.readSettings();
        init(settings);
    }
    
    public void init(Settings settings){
        handler = new Handler();
        cl = new Client(settings);
        cl.setHandler(handler);
        handler.setClient(cl);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setGui(JGui gui) {
        handler.setWindow(gui);
    }

}
