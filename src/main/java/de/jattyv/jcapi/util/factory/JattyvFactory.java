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
package de.jattyv.jcapi.util.factory;

import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.data.jobject.Element;
import de.jattyv.jcapi.util.ChatTags;
import de.jattyv.jcapi.util.KeyTags;
import de.jattyv.jcapi.util.crypt.LKeyGenerator;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvFactory implements ChatTags, KeyTags {

    /**
     * Creates a logincontainer, that will contain the loginkey.
     * 
     * @param uname The needed username.
     * @param upassword The needed password.
     * @return a container with the needed information for the client.
     */
    public static Container createLoginContainer(String uname, String upassword) {
        Container login = new Container();
        login.setSuperTag(SESSION_SETTINGS);
        login.addE(new Element(U_LOG_KEY, LKeyGenerator.generateLKey(uname, upassword)));
        login.addE(new Element(U_NAME, uname));
        return login;
    }

    /**
     * Creates a container wich contains the errorkey for logins.
     * 
     * @return a container wich contains the errorkey for logins.
     */
    public static Container createLoginFailedContainer() {
        Container fLogin = new Container();
        fLogin.setSuperTag(JERROR);
        fLogin.addE(new Element(ERR_KEY, LOG_FAIL));
        return fLogin;
    }

    /**
     * Creates an errorcontainer with the given errkey.
     * 
     * @param errCode The needed errorcode.
     * @return an errorcontainer with the given errkey.
     */
    public static Container createErrorContainer(String errCode) {
        Container err = new Container();
        err.setSuperTag(JERROR);
        err.addE(new Element(ERR_KEY, errCode));
        return err;
    }

    /**
     * Creates an messagecontainer.
     * 
     * @param uName The name from the sender.
     * @param toName The name from the receiver.
     * @param message The message.
     * @return an messagecontainer.
     */
    public static Container createMessageContainer(String uName, String toName, String message) {
        Container msg = new Container();
        msg.setSuperTag(NEW_MESSAGE);
        msg.addE(new Element(FROM_USER, uName));
        msg.addE(new Element(TO_USER, toName));
        msg.addE(new Element(MESSAGE, message));

        return msg;
    }

    /**
     * Creates a container wich contains information for a grouprequest.
     * 
     * @param gName The name of the group.
     * @param gID The id of the group.
     * @return a container wich contains information for a grouprequest.
     */
    public static Container createGroupRequestContainer(String gName, String gID) {
        Container c = new Container();
        c.setSuperTag(G_REQUEST_TO_USER);
        c.addE(GROUP_NAME, gName);
        c.addE(GROUP_ID, gID);
        return c;
    }

    /**
     * Creates a container wich contains information for a groupmessage.
     * 
     * @param uName The name of the sender.
     * @param gID The groupid.
     * @param msg The message.
     * @return a container wich contains information for a groupmessage.
     */
    public static Container createGroupMessageContainer(String uName, String gID, String msg) {
        Container c = new Container();
        c.setSuperTag(NEW_GROUP_MESSAGE);
        c.addE(FROM_USER, uName);
        c.addE(GROUP_ID, gID);
        c.addE(MESSAGE, msg);
        return c;
    }
    
    /**
     * Creates a container wich contains information for a friendrequest.
     * 
     * @param uName The name of the sender(person who wants to be friend with).
     * @param fName The name of the receiver(peron who will be asked to be friend with).
     * @return a container wich contains information for a friendrequest.
     */
    public static Container createFriendRequestContainer(String uName, String fName){
        Container c = new Container();
        c.setSuperTag(U_FRIENDREQUEST);
        c.addE(FROM_USER, uName);
        c.addE(TO_USER, fName);
        return c;
    }

    /**
     * Creates a container wich contains the users friend and grouplist.
     * 
     * @param fgs The friend/group list.
     * @return a container wich contains the users friend and grouplist.
     */
    public static Container createFGListContainer(List<FG> fgs) {
        Container c = new Container();
        c.setSuperTag(U_FGLIST);
        for(FG fg : fgs){
            Container temp = new Container();
            temp.setSuperTag(U_FGLIST);
            temp.addE(FG_NAME, fg.getTitle());
            temp.addE(FG_TYPE, ""+fg.getType());
            temp.addE(FG_ID, fg.getId());
            c.addC(temp);
        }
        return c;
    }
}
