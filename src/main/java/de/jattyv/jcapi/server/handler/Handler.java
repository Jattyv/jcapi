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
package de.jattyv.jcapi.server.handler;

import com.google.gson.Gson;
import de.jattyv.jcapi.data.jobject.Base;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.util.ChatTags;
import de.jattyv.jcapi.server.network.ServerThread;
import de.jattyv.jcapi.server.virtual.DBController.DBController;
import de.jattyv.jcapi.server.virtual.dataController.DataController;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Handler implements ChatTags {

    private final ServerThread con;
    private Base session;
    private final MsgHandler msgHandler;
    private final UserHandler userHandler;
    private final GroupHandler groupHandler;
    private DataController dc;
    private DBController dbc;

    public Handler(ServerThread con, DataController dc, DBController dbc) {
        this.con = con;
        this.dc = dc;
        this.dbc = dbc;
        session = new Base();
        msgHandler = new MsgHandler(dc);
        userHandler = new UserHandler(dc,dbc);
        groupHandler = new GroupHandler(dc,dbc);
        
    }

    public Container initSession(Container c) {
        Gson gson = new Gson();
        Container command = c;
        try {
            session = userHandler.handleFirstInput(command);
        } catch (Exception e) {
            e.printStackTrace();
            con.close();
        }
        if (session != null) {
            return session.getDatabySuperTag(SESSION_SETTINGS);
        }
        return null;
    }

    public void handle(Container c) {
        session.addC(c);

        switch (c.getSuperTag()) {

            case NEW_MESSAGE:
                msgHandler.handle(c);
                break;

            case U_CREATE_GROUP:
                groupHandler.handle(c);
                break;

            case G_REQUEST_TO_USER:
                groupHandler.handle(c);
                break;

            case NEW_GROUP_MESSAGE:
                groupHandler.handle(c);
                break;

        }

    }

}
