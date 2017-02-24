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

import com.google.gson.Gson;
import de.jattyv.jcapi.data.jobject.Base;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.handler.Handler;
import de.jattyv.jcapi.server.network.data.JConnection;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.util.ChatTags;
import de.jattyv.jcapi.util.factory.JattyvFactory;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ServerThread extends Thread implements ChatTags {

    private final Handler handler;
    private final JConnection con;
    private Gson gson;
    private boolean connected;
    private DataController dc;

    public ServerThread(JConnection con, DataController dc) {
        gson = new Gson();
        this.con = con;
        this.dc = dc;
        handler = new Handler(this, dc);
    }

    public String init() {
        Container login = handler.initSession(gson.fromJson(con.readMsg(), Container.class));
        if (login != null) {
            con.writeMsg(gson.toJson(new Base(login)));
            connected = true;
            return login.getDataByName(U_NAME);
        } else {
            con.writeMsg(gson.toJson(new Base(JattyvFactory.createLoginFailedContainer())));
        }

        con.closeCon();
        connected = false;
        return null;
    }

    @Override
    public void run() {
        String msg;
        while (connected) {
            msg = con.readMsg();
            if (msg != null) {
                handler.handle(gson.fromJson(msg, Container.class));
            }
        }
    }

    public void write(String msg) {
        con.writeMsg(msg);
    }

    public void writeAsJson(Base b) {
        con.writeMsg(gson.toJson(b));
    }

    public void close() {
        con.closeCon();
    }

}
