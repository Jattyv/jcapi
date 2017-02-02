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
package de.jattyv.jcapi.server.virtual.dataController;

import de.jattyv.jcapi.server.virtual.dataController.controller.MessageController;
import de.jattyv.jcapi.server.virtual.dataController.controller.UserController;
import de.jattyv.jcapi.server.virtual.dataController.data.Message;
import de.jattyv.jcapi.server.virtual.dataController.data.User;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class DataController {

    private LinkedList<User> users;
    private LinkedList<Message> messages;

    private UserController userC;
    private MessageController msgC;

    public DataController() {
        users = new LinkedList<User>();
        messages = new LinkedList<Message>();
        userC = new UserController(users);
        msgC = new MessageController(messages);
    }

    public UserController getUserC() {
        return userC;
    }

    public MessageController getMsgC() {
        return msgC;
    }


}
