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
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.data.jobject.Container;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler {

    private Map<String, LinkedList<String>> messages;

    public GroupHandler(Handler handler) {
        super(handler);
        messages = new HashMap<>();
    }

    public void handle(Container c) {
        String gname = c.getDataByName(GROUP_NAME);
        String gID = c.getDataByName(GROUP_ID);
        switch (c.getSuperTag()) {

            case G_REQUEST_TO_USER:
                handler.getWindow().addGroup(gname, gID);
                break;

            case NEW_GROUP_MESSAGE:
                String fname = c.getDataByName(FROM_USER);
                String msg = c.getDataByName(MESSAGE);
                String fmsg = fname + ": " + msg;
                handler.getWindow().addGroupMessage(gID, fmsg);
                addMessage(gID, fmsg);
                break;
        }
    }

    private void addMessage(String gID, String message) {
        if (messages.containsKey(gID)) {
            messages.get(gID).add(message);
        } else {
            LinkedList<String> tmp = new LinkedList<>();
            tmp.add(message);
            messages.put(gID, tmp);
        }
    }

    public LinkedList<String> getGroupMessages(String gID) {
        LinkedList<String> toReturn = new LinkedList<>();
        if (messages.containsKey(gID)) {
            toReturn = messages.get(gID);
        }
        return toReturn;
    }

}
