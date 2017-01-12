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
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.network.JClient;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.data.chatobjects.User;
import de.jattyv.jcapi.util.ChatTags;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Handler implements ChatTags {

    private JClient cl;
    private JGui window;
    private User user;

    private MsgHandler msgHandler;
    private UserHandler userHandler;
    private ErrorHandler errHandler;

    public Handler() {
        user = new User();
        msgHandler = new MsgHandler(this);
        userHandler = new UserHandler(this);
        errHandler = new ErrorHandler(this);

    }

    public void handle(Container c) {
        switch (c.getSuperTag()) {

            case NEW_MESSAGE:
                msgHandler.handle(c);
                break;

            case SESSION_SETTINGS:
                userHandler.handle(c);
                break;

            case JERROR:
                errHandler.handle(c);
                break;

        }

    }

    public void handle(String input) {

    }

    public void send(Container c) {
        cl.write(c);
    }

    public void start(Container c) {
        user.setName(c.getDataByName(U_NAME));
        cl.start(c);
    }

    public void setClient(JClient cl) {
        this.cl = cl;
    }

    public void setWindow(JGui window) {
        this.window = window;
    }

    public JGui getWindow() {
        return window;
    }

    public User getUser() {
        return user;
    }

    public JClient getCl() {
        return cl;
    }


    public LinkedList<String> getMessages(String uName) {
        return msgHandler.getMessages(uName);
    }

}
