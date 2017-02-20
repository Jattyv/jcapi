/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.FriendRequest;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class FriendRequestController {

    private List<FriendRequest> friendRequests;

    public FriendRequestController(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public void createFriendRequest(String uName, String fName) {
        FriendRequest req = new FriendRequest();
        req.setuName(uName);
        req.setfName(fName);
        friendRequests.add(req);
    }

    public LinkedList<FriendRequest> getFriendRequest(String fName) {
        LinkedList<FriendRequest> requests = new LinkedList<>();
        for (FriendRequest req : friendRequests) {
            if (req.getfName().equals(fName)) {
                requests.add(req);
            }
        }
        return requests;
    }

    public void removeFriendRequests(String uName) {
        for (int i = 0; i < friendRequests.size(); i++) {
            if (friendRequests.get(i).getuName().equals(uName)) {
                friendRequests.remove(i);
            }
        }
    }

    public void removeFriendRequest(FriendRequest req) {
        friendRequests.remove(req);
    }

}
