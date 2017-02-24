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

import de.jattyv.jcapi.util.Packer;
import de.jattyv.jcapi.util.crypt.PasswordHasher;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class OutputHandler extends JattyvHandler {

    /**
     * Constructs the OutPutHandler.
     * 
     * @param handler The needed Hanlder for handling everything outgoing message.
     */
    public OutputHandler(Handler handler) {
        super(handler);
    }

    /**
     * Sends a new message to an friend.
     * 
     * @param toName The name of the friend.
     * @param message The message.
     */
    public void sendNewMessage(String toName, String message) {
        if (!toName.isEmpty() && !message.isEmpty()) {
            handler.send(Packer.packNewMessage(handler.getUser().getLogKey(), toName, message));
        }
    }

    /**
     * Sends a container for log in.
     * @param uname The name of the user.
     * @param upassword The hashed password of the user.
     */
    public void sendLogin(String uname, String upassword) {
        if (!uname.isEmpty() && !upassword.isEmpty()) {
            handler.start(Packer.packLogin(uname, PasswordHasher.generateLKey(uname, upassword)));
        }
    }

    /**
     * Sends a container for registration.
     * 
     * @param uname The name of the user.
     * @param upassword The password of the user.
     */
    public void sendRegist(String uname, String upassword) {
        if (!uname.isEmpty() && !upassword.isEmpty()) {
            handler.start(Packer.packRegistration(uname, PasswordHasher.generateLKey(uname, upassword)));
        }
    }

    /**
     * Sends a container for creating a group.
     * 
     * @param gName The name of the group.
     */
    public void createGroup(String gName) {
        if (!gName.isEmpty()) {
            handler.send(Packer.packCreateGroup(gName, handler.getUser().getLogKey()));
        }
    }

    /**
     * Adds a request to a group to a friend.
     * 
     * @param gID The id of the group.
     * @param fName The name of the friend.
     */
    public void addUserToGroup(String gID, String fName) {
        if (!(gID.isEmpty()) && !fName.isEmpty()) {
            handler.send(Packer.packAddUserToGroup(gID, fName));
        }
    }

    /**
     * Sends a new message to a group.
     * 
     * @param togID The id of the group.
     * @param msg The message.
     */
    public void sendNewGroupMessage(String togID, String msg) {
        if (!togID.isEmpty() && !msg.isEmpty()) {
            handler.send(Packer.packNewGroupMessage(handler.getUser().getLogKey(), togID, msg));
        }
    }
    
    /**
     * Sends an agreement for a grouprequest.
     * 
     * @param gName The name of the group.
     * @param gid The id of the group.
     */
    public void sendOkayToGroupRequest(String gName, String gid){
        if(!gid.isEmpty()){
            handler.send(Packer.packOkayToGroupRequest(handler.getUser().getLogKey(),gName, gid));
        }
    }
    
    /**
     * Sends a new request to add a new friend.
     * 
     * @param fName The name of the friend.
     */
    public void sendNewFriendRequest(String fName){
        if(!fName.isEmpty()){
            handler.send(Packer.packNewFriendRequest(handler.getUser().getLogKey(), fName));
        }
    }
   
    /**
     * Sends an agreement for a request to add a friend.
     * @param fname The name of the friend.
     */
    public void sendOkayToFriendRequest(String fname){
        if(!fname.isEmpty()){
            handler.send(Packer.packOkayToFriendRequest(handler.getUser().getLogKey(), fname));
        }
    }

}
