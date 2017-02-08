/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.DBController.DBController;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.util.crypt.LKeyGenerator;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler {

    private DBController dbc;

    public GroupHandler(DataController dc, DBController dbc) {
        super(dc);
        this.dbc = dbc;
    }

    public void handle(Container c) {
        String gname = c.getDataByName(GROUP_NAME);
        String gID = "";
        switch (c.getSuperTag()) {

            case U_CREATE_GROUP:
                String ulkey = c.getDataByName(U_NAME);
                String uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                gID = LKeyGenerator.generateLKey(uname, ulkey);
                dc.getGroupC().createGroup(gname, gID, uname);
                dc.getGroupReqC().createGroupRequest(gname, gID, uname);
                ReloadHandler.turnOnGroupRequestReload(uname);

                break;

            case G_REQUEST_TO_USER:
                gID = c.getDataByName(GROUP_ID);
                gname = dc.getGroupC().getGroup(gID).getGroupName();
                String tname = c.getDataByName(TO_USER);
                dc.getGroupC().addToGroup(gID, tname);
                dc.getGroupReqC().createGroupRequest(gname, gID, tname);
                ReloadHandler.turnOnGroupRequestReload(tname);

                break;

            case NEW_GROUP_MESSAGE:
                gID = c.getDataByName(GROUP_ID);
                String flkey = c.getDataByName(FROM_USER);
                String fname = dc.getUserC().getUserByLKey(flkey).getUserName();
                String msg = c.getDataByName(MESSAGE);
                LinkedList<String> members = new LinkedList<>(dc.getGroupC().getGroup(gID).getMembers());
                dc.getGroupMsgC().createGroupMessage(fname, gID, members, msg);
                for (String memberName : members) {
                    ReloadHandler.turnOnGroupMessageReload(memberName);
                }
                break;
        }
    }

}
