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
        handler.send(Packer.packNewMessage(handler.getUser().getLogKey(), toName, message));
    }

    public void sendLogin(String uname, String upassword) {
        handler.start(Packer.packLogin(uname, PasswordHasher.generateLKey(uname, upassword)));
    }

    public void sendRegist(String uname, String upassword) {
        handler.start(Packer.packRegistration(uname, PasswordHasher.generateLKey(uname, upassword)));
    }
    
    public void joinGroup(String gName){
        handler.start(Packer.packCreateGroup(gName, handler.getUser().getLogKey()));
    }
    
    public void addUserToGroup(String gName, String fName){
        handler.start(Packer.packAddUserToGroup(gName, fName));
    }
    
    public void sendNewGroupMessage(String toGroup, String msg){
        handler.start(Packer.packNewMessage(handler.getUser().getLogKey(), toGroup, msg));
    }

}
