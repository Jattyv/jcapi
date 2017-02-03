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

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.dataController.DataController;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MsgHandler extends JattyvHandler {

    public MsgHandler(DataController dc) {
        super(dc);
    }

    public void handle(Container c) {

        String message = c.getDataByName(MESSAGE);
        String fromUser = c.getDataByName(FROM_USER);
        String fromUserName = dc.getUserC().getUserByLKey(fromUser).getUserName();
        String toUser = c.getDataByName(TO_USER);

        dc.getMsgC().createNewMessage(fromUserName, toUser, fromUserName, message);
        dc.getMsgC().createNewMessage(fromUserName, toUser, toUser, message);

        ReloadHandler.turnOnMessageReload(fromUserName);
        ReloadHandler.turnOnMessageReload(toUser);

    }

}
