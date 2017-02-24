/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.server.virtual.dataController.data.User;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class FriendRequestHandler extends JattyvHandler {

    public FriendRequestHandler(DataController dc) {
        super(dc);
    }

    public void handle(Container c) {
        String uLkey = c.getDataByName(FROM_USER);
        User user = dc.getUserC().getUserByLKey(uLkey);
        String fName = c.getDataByName(TO_USER);
        if (!isUserFriendsOk(user, fName)) {
            return;
        }
        switch (c.getSuperTag()) {
            case U_REQUEST_TO_FRIEND:
                dc.getFriendReqC().createFriendRequest(user.getUserName(), fName);
                MReloadHandler.turnOnFriendRequestReload(fName);
                break;

            case U_AGREE_FRIEND:
                dc.getUserC().addFriend(user.getUserName(), fName);
                dc.getUserC().addFriend(fName, user.getUserName());
                MReloadHandler.turnOnNewFGList(fName);
                MReloadHandler.turnOnNewFGList(user.getUserName());
                break;
        }

    }

    private boolean isUserFriendsOk(User user, String fname) {
        if (fname.equals(user.getUserName())) {
            return false;
        }
        for (FG fg : user.getFgs()) {
            if (fg.getTitle().equals(fname) && fg.getType() == FG.FG_TYPE_FRIEND) {
                return false;
            }
        }
        return true;
    }

}
