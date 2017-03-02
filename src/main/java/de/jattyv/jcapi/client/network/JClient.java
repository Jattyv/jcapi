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

import com.google.gson.Gson;
import de.jattyv.jcapi.client.handler.Handler;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.data.jobject.Container;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JClient {

    protected int port;
    protected String host;
    protected Gson gson;
    protected Handler handler;

    public JClient(String host, int port) {
        this.port = port;
        this.host = host;
        this.gson = new Gson();
    }

    public JClient(Settings settings) {
        if (settings.isIpAvailable() && settings.isPortAvailable()) {
            this.host = settings.getIp();
            this.port = settings.getPort();
            gson = new Gson();
        }
    }

    public void start(Container c) {

    }

    public void write(Container c) {

    }

    public void write(String s) {

    }

    public String read() {
        return "";
    }

    public void reload() {

    }
    
    public boolean checkCert(String fingerprint){
        return false;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void close() {

    }

}
