/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.GroupRequest;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupRequestController {
    
    private List<GroupRequest> groupRequests;
    
    public GroupRequestController(List<GroupRequest> groupRequests){
        this.groupRequests = groupRequests;
    }
    
    public void createGroupRequest(String gName, String uName){
        GroupRequest req = new GroupRequest();
        req.setgName(gName);
        req.setuName(uName);
        groupRequests.add(req);
    }
    
    public LinkedList<GroupRequest> getGroupRequest(String uName){
        LinkedList<GroupRequest> requests = new LinkedList<>();
        for(GroupRequest req : groupRequests){
            if(req.getuName().equals(uName)){
                requests.add(req);
            }
        }
        return requests;
    }
    
    public void removeGroupRequest(String uName){
        for(int i=0; i<groupRequests.size(); i++){
            if(groupRequests.get(i).getuName().equals(uName)){
                groupRequests.remove(i);
            }
        }
    }
    
}
