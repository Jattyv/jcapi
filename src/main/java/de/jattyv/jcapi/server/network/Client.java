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
package de.jattyv.jcapi.server.network;

import de.jattyv.jcapi.server.handler.ReloadHandler;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Client {

    private ServerThread st;
    private String uName;
    private boolean newMessages;
    private boolean newGroupMessage;
    private boolean newGroupRequest;

    public Client(ServerThread st) {
        this.st = st;
        newMessages = false;
    }

    public void start() {
        uName = st.init();
        ReloadHandler.removeUser(uName);
        if (uName == null) {
            ReloadHandler.removeClient(this);
            return;
        }
        ReloadHandler.addClient(this);
        st.start();
    }

    public ServerThread getSt() {
        return st;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }


    public boolean isNewMessages() {
        return newMessages;
    }

    public void setNewMessages(boolean newMessages) {
        this.newMessages = newMessages;
    }

    public boolean isNewGroupMessage() {
        return newGroupMessage;
    }

    public void setNewGroupMessage(boolean newGroupMessage) {
        this.newGroupMessage = newGroupMessage;
    }

    public boolean isNewGroupRequest() {
        return newGroupRequest;
    }

    public void setNewGroupRequest(boolean newGroupRequest) {
        this.newGroupRequest = newGroupRequest;
    }
    
    

}
