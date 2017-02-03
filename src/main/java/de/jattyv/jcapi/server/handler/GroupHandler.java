/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler{
    
    public GroupHandler(DataController dc) {
        super(dc);
    }
    
    public void handle(Container c){
        String gname = c.getDataByName(GROUP_NAME);
        switch(c.getSuperTag()){
            
            case U_CREATE_GROUP:
                String ulkey = c.getDataByName(U_NAME);
                String uname = dc.getUserC().getUserByLKey(ulkey).getUserName();
                dc.getGroupC().createGroup(gname, uname);
                ReloadHandler.turnOnGroupRequestReload(uname);
                dc.getGroupReqC().createGroupRequest(gname, uname);
                break;

            case G_REQUEST_TO_USER:
                String tname = c.getDataByName(TO_USER);
                ReloadHandler.turnOnGroupRequestReload(tname);
                dc.getGroupReqC().createGroupRequest(gname, tname);
                break;

            case NEW_GROUP_MESSAGE:
                String flkey = c.getDataByName(FROM_USER);
                String fname = dc.getUserC().getUserByLKey(flkey).getUserName();
                String msg = c.getDataByName(MESSAGE);
                LinkedList<String> members = dc.getGroupC().getGroup(gname).getMembers();
                for(String memberName : members){
                    ReloadHandler.turnOnGroupMessageReload(memberName);
                }
                dc.getGroupMsgC().createGroupMessage(fname, gname, members, msg);
                break;
        }
    }
    
}
