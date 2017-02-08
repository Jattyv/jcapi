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
    
    private LinkedList<Group> groups;

    public GroupController(LinkedList<Group> groups) {
        this.groups = groups;
    }
    
    public void createGroup(String gName,String gID, String uName){
        Group group = new Group();
        group.setGroupName(gName);
        group.setGroupID(gID);
        LinkedList<String> members = new LinkedList<>();
        members.add(uName);
        group.setMembers(members);
        groups.add(group);
    }
    
    public Group getGroup(String gID){
        for(Group group: groups){
            if(group.getGroupID().equals(gID)){
                return group;
            }
        }
        return null;
    }
    
    public void addToGroup(String gID, String uName){
        for(Group group: groups){
            if(group.getGroupID().equals(gID)){
                group.getMembers().add(uName);
                return;
            }
        }
    }
    
}
