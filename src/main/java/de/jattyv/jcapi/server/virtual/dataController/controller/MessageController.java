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
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.Message;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MessageController {

    private List<Message> messages;

    public MessageController(List<Message> messages) {
        this.messages = messages;
    }

    public void createNewMessage(String fromName, String toName, String to, String message) {
        Message newMessage = new Message(fromName, toName, to, message);
        messages.add(newMessage);
    }

    public LinkedList<Message> getMessages(String toName) {
        LinkedList<Message> usersMessages = new LinkedList<>();
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getTo().equals(toName)) {
                usersMessages.add(messages.get(i));
            }
        }
        return usersMessages;
    }

    public void removeMessage(Message msg) {
        messages.remove(msg);
    }

}
