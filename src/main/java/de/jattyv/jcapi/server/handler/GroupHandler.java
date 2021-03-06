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
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.util.crypt.LKeyGenerator;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler {

    public GroupHandler(DataController dc) {
        super(dc);
    }

    public void handle(Container c) {
        String gname = c.getDataByName(GROUP_NAME);
        String gID = "";
        String ulkey = "";
        String uname = "";
        switch (c.getSuperTag()) {

            case U_CREATE_GROUP:
                ulkey = c.getDataByName(FROM_USER);
                uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                gID = LKeyGenerator.generateLKey(uname, ulkey);
                dc.getGroupC().createGroup(gname, gID);
                dc.getGroupReqC().createGroupRequest(gname, gID, uname);
                MReloadHandler.turnOnGroupRequestReload(uname);
                break;

            case G_REQUEST_TO_USER:
                gID = c.getDataByName(GROUP_ID);
                gname = dc.getGroupC().getGroup(gID).getGroupName();
                String tname = c.getDataByName(TO_USER);
                if (dc.getGroupC().isUserInGroup(gID, tname)) {
                    return;
                }
                dc.getGroupReqC().createGroupRequest(gname, gID, tname);
                MReloadHandler.turnOnGroupRequestReload(tname);
                break;

            case NEW_GROUP_MESSAGE:
                gID = c.getDataByName(GROUP_ID);
                ulkey = c.getDataByName(FROM_USER);
                uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                if (!dc.getGroupC().isUserInGroup(gID, uname)) {
                    return;
                }
                String msg = c.getDataByName(MESSAGE);
                LinkedList<String> members = new LinkedList<>(dc.getGroupC().getGroup(gID).getMembers());
                dc.getGroupMsgC().createGroupMessage(uname, gID, members, msg);
                for (String memberName : members) {
                    MReloadHandler.turnOnGroupMessageReload(memberName);
                }
                break;

            case U_AGREE_GROUP:
                gID = c.getDataByName(GROUP_ID);
                ulkey = c.getDataByName(FROM_USER);
                uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                if (dc.getGroupC().isUserInGroup(gID, uname)) {
                    return;
                }
                dc.getGroupC().addToGroup(gID, uname);
                dc.getUserC().addGroup(uname, gname, gID);
                MReloadHandler.turnOnNewFGList(uname);
                break;

            case U_REM_GROUP:
                ulkey = c.getDataByName(FROM_USER);
                uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                gID = c.getDataByName(FG_ID);
                dc.getGroupC().remUser(gID, uname);
                dc.getUserC().remGroup(uname, gID);
                MReloadHandler.turnOnNewFGList(uname);
                break;
        }
    }

}
