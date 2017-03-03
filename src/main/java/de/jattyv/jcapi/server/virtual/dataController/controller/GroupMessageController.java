/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.GroupMessage;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupMessageController {

    private List<GroupMessage> groupMessages;

    public GroupMessageController(List<GroupMessage> groupMessages) {
        this.groupMessages = groupMessages;
    }

    public void createGroupMessage(String fromUser, String GID, LinkedList<String> to, String msg) {
        GroupMessage gmsg = new GroupMessage();
        gmsg.setFromUser(fromUser);
        gmsg.setToGID(GID);
        gmsg.setTo(to);
        gmsg.setMessage(msg);
        groupMessages.add(gmsg);
    }

    public List<GroupMessage> getGroupMessage(String uName) {
        LinkedList<GroupMessage> tmp = new LinkedList<>();
        for (GroupMessage gmsg : groupMessages) {
            for (String name : gmsg.getTo()) {
                if (name.equals(uName)) {
                    tmp.add(gmsg);
                }
            }
        }
        return tmp;
    }

    public void removeGroupMessages(String uName) {
        for (int i = 0; i < groupMessages.size(); i++) {
            for (int i2 = 0; i2 < groupMessages.get(i).getTo().size(); i2++) {
                if (groupMessages.get(i).getTo().get(i2).equals(uName)) {
                    groupMessages.get(i).getTo().remove(i2);
                }
            }
            if (groupMessages.get(i).getTo().isEmpty()) {
                groupMessages.remove(i);
            }
        }
    }

}
