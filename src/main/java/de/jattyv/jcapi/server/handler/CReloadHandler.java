/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.server.network.Client;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.server.virtual.dataController.data.FriendRequest;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupMessage;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupRequest;
import de.jattyv.jcapi.server.virtual.dataController.data.Message;
import de.jattyv.jcapi.util.factory.JattyvFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CReloadHandler implements Runnable {

    private final Client cl;
    private final DataController dc;

    public CReloadHandler(DataController dc, Client cl) {
        this.dc = dc;
        this.cl = cl;
    }

    public void reload() {
        if (cl.isNewMessages()) {
            reloadMessages();
        }
        if (cl.isNewGroupMessage()) {
            reloadGroupMessages();
        }
        if (cl.isNewGroupRequest()) {
            reloadGroupRequests();
        }
        if (cl.isNewFriendRequest()) {
            reloadFriendRequests();
        }

    }

    public void reloadMessages() {
        LinkedList<Message> messages = dc.getMsgC().getMessages(cl.getuName());
        for (Message msg : messages) {
            cl.getSt().writeAsJson(JattyvFactory.createMessageContainer(msg.getFromUser(), msg.getToUser(), msg.getMessage()));
            dc.getMsgC().removeMessage(msg);
        }
        cl.setNewMessages(false);
    }

    public void reloadGroupMessages() {
        List<GroupMessage> gmessages = dc.getGroupMsgC().getGroupMessage(cl.getuName());
        for (GroupMessage gmsg : gmessages) {
            cl.getSt().writeAsJson(JattyvFactory.createGroupMessageContainer(gmsg.getFromUser(), gmsg.getToGID(), gmsg.getMessage()));
        }
        dc.getGroupMsgC().removeGroupMessages(cl.getuName());
        cl.setNewGroupMessage(false);
    }

    public void reloadGroupRequests() {
        List<GroupRequest> requests = dc.getGroupReqC().getGroupRequest(cl.getuName());
        for (GroupRequest req : requests) {
            cl.getSt().writeAsJson(JattyvFactory.createGroupRequestContainer(req.getgName(), req.getGid()));
            dc.getGroupReqC().removeGroupRequest(req);
        }
        cl.setNewGroupRequest(false);
    }

    public void reloadFriendRequests() {
        List<FriendRequest> requests = dc.getFriendReqC().getFriendRequest(cl.getuName());
        for (FriendRequest req : requests) {
            cl.getSt().writeAsJson(JattyvFactory.createFriendRequestContainer(req.getuName(), req.getfName()));
            dc.getFriendReqC().removeFriendRequest(req);
        }
        cl.setNewFriendRequest(false);
    }

    public Client getCl() {
        return cl;
    }

    public String getuName() {
        return cl.getuName();
    }

    @Override
    public void run() {
        while (true) {
            try {
                reload();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(CReloadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
