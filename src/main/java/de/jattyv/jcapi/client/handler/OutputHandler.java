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

    public OutputHandler(Handler handler) {
        super(handler);
    }

    public void sendNewMessage(String toName, String message) {
        if (!toName.isEmpty() && !message.isEmpty()) {
            handler.send(Packer.packNewMessage(handler.getUser().getLogKey(), toName, message));
        }
    }

    public void sendLogin(String uname, String upassword) {
        if (!uname.isEmpty() && !upassword.isEmpty()) {
            handler.start(Packer.packLogin(uname, PasswordHasher.generateLKey(uname, upassword)));
        }
    }

    public void sendRegist(String uname, String upassword) {
        if (!uname.isEmpty() && !upassword.isEmpty()) {
            handler.start(Packer.packRegistration(uname, PasswordHasher.generateLKey(uname, upassword)));
        }
    }

    public void createGroup(String gName) {
        if (!gName.isEmpty()) {
            handler.send(Packer.packCreateGroup(gName, handler.getUser().getLogKey()));
        }
    }

    public void addUserToGroup(String gID, String fName) {
        if (!(gID.isEmpty()) && !fName.isEmpty()) {
            handler.send(Packer.packAddUserToGroup(gID, fName));
        }
    }

    public void sendNewGroupMessage(String togID, String msg) {
        if (!togID.isEmpty() && !msg.isEmpty()) {
            handler.send(Packer.packNewGroupMessage(handler.getUser().getLogKey(), togID, msg));
        }
    }
    
    public void sendNewFriendRequest(String fName){
        if(!fName.isEmpty()){
            handler.send(Packer.packNewFriendRequest(handler.getUser().getLogKey(), fName));
        }
    }
    
    public void sendOkayToFriendRequest(String fname){
        if(!fname.isEmpty()){
            handler.send(Packer.packOkayToFriendRequest(handler.getUser().getLogKey(), fname));
        }
    }

}
