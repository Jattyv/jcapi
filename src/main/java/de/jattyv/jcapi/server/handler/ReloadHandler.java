/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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

import de.jattyv.jcapi.util.factory.JattyvFactory;
import de.jattyv.jcapi.server.network.Client;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupMessage;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupRequest;
import de.jattyv.jcapi.server.virtual.dataController.data.Message;
import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ReloadHandler implements Runnable {

    private static LinkedList<Client> cls;
    private DataController dc;

    public ReloadHandler(DataController dc) {
        this.dc = dc;
        cls = new LinkedList<Client>();
    }

    public ReloadHandler() {
        cls = new LinkedList<>();
    }

    public void reload() {
        for (Client cl : cls) {
            if (cl.isNewMessages()) {
                reloadMessages(cl);
            }
            if (cl.isNewGroupMessage()) {
                reloadGroupMessages(cl);
            }
            if (cl.isNewGroupRequest()) {
                reloadGroupRequests(cl);
            }
        }
    }

    public void reloadMessages(Client cl) {
        LinkedList<Message> messages = dc.getMsgC().getMessages(cl.getuName());
        for (Message msg : messages) {
            cl.getSt().writeAsJson(JattyvFactory.createMessageContainer(msg.getFromUser(), msg.getToUser(), msg.getMessage()));
            dc.getMsgC().removeMessage(msg);
        }
        cl.setNewMessages(false);
    }

    public void reloadGroupMessages(Client cl) {
        List<GroupMessage> gmessages = dc.getGroupMsgC().getGroupMessage(cl.getuName());
        for (GroupMessage gmsg : gmessages) {
            cl.getSt().writeAsJson(JattyvFactory.createGroupMessageContainer(gmsg.getFromUser(), gmsg.getToGName(), gmsg.getMessage()));
        }
        dc.getGroupMsgC().removeGroupMessages(cl.getuName());
        cl.setNewGroupMessage(false);
    }

    public void reloadGroupRequests(Client cl) {
        List<GroupRequest> requests = dc.getGroupReqC().getGroupRequest(cl.getuName());
        for(GroupRequest req : requests){
            cl.getSt().writeAsJson(JattyvFactory.createGroupRequestContainer(req.getgName(), cl.getuName()));
            dc.getGroupReqC().removeGroupRequest(req);
        }
        cl.setNewGroupRequest(false);
    }

    @Override
    public void run() {
        while (true) {
            reload();
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ReloadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void removeUser(String uName) {
        for (int i = 0; i < cls.size(); i++) {
            if (cls.get(i).getuName().equals(uName)) {
                cls.remove(i);
                return;
            }
        }
    }

    public List<Client> getClients() {
        return cls;
    }

    public static void removeClient(Client cl) {
        cls.remove(cl);
    }

    public static void addClient(Client cl) {
        cls.add(cl);
    }

    public static void turnOnMessageReload(String uname) {
        for (Client cl : cls) {
            if (cl.getuName().equals(uname)) {
                cl.setNewMessages(true);
                return;
            }
        }
    }

    public static void turnOnGroupMessageReload(String uname) {
        for (Client cl : cls) {
            if (cl.getuName().equals(uname)) {
                cl.setNewGroupRequest(true);
                return;
            }
        }
    }

    public static void turnOnGroupRequestReload(String uname) {
        for (Client cl : cls) {
            if (cl.getuName().equals(uname)) {
                cl.setNewGroupRequest(true);
                return;
            }
        }
    }

}
