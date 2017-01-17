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

import de.jattyv.jcapi.data.chatobjects.Message;
import de.jattyv.jcapi.data.jobject.Container;
import static de.jattyv.jcapi.util.ChatTags.FROM_USER;
import static de.jattyv.jcapi.util.ChatTags.MESSAGE;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MsgHandler extends JattyvHandler {

    private LinkedList<Message> messages;

    public MsgHandler(Handler handler) {
        super(handler);
        messages = new LinkedList<>();
    }

    public void handle(Container c) {
        String fromUser = c.getDataByName(FROM_USER);
        String toUser = c.getDataByName(TO_USER);
        String message = c.getDataByName(MESSAGE);
        String to = "";

        if (!fromUser.equals(handler.getUser().getName())) {
            to = fromUser;
        } else {
            to = toUser;
        }


        messages.add(new Message(fromUser, toUser, to, message));
        handler.getWindow().addMessage(to, fromUser + ": " + message);
    }

    public LinkedList<String> getMessages(String uName) {
        LinkedList<String> toReturn = new LinkedList<>();
        for (Message m : messages) {
            if (m.getTo().equals(uName)) {
            toReturn.add(m.getFromUser() + ": " + m.getMessage());
            }
        }
        return toReturn;
    }

}
