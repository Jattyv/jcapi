/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.Group;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupController {

    private final LinkedList<Group> groups;

    public GroupController(LinkedList<Group> groups) {
        this.groups = groups;
    }

    public void createGroup(String gName, String gID) {
        Group group = new Group();
        group.setGroupName(gName);
        group.setGroupID(gID);
        LinkedList<String> members = new LinkedList<>();
        group.setMembers(members);
        groups.add(group);
    }

    public Group getGroup(String gID) {
        for (Group group : groups) {
            if (group.getGroupID().equals(gID)) {
                return group;
            }
        }
        return null;
    }

    public void addToGroup(String gID, String uName) {
        for (Group group : groups) {
            if (group.getGroupID().equals(gID)) {
                group.getMembers().add(uName);
                return;
            }
        }
    }
    
    public boolean isUserInGroup(String gID, String uName){
        for(Group group : groups){
            if(group.getGroupID().equals(gID)){
                for(String mem: group.getMembers()){
                    if(mem.equals(uName)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void remUser(String gID, String uname) {
        for(Group group : groups){
            if(group.getGroupID().equals(gID)){
                for(String mem: group.getMembers()){
                    if(mem.equals(uname)){
                        group.getMembers().remove(mem);
                        return;
                    }
                }
            }
        }
    }

}
