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
package de.jattyv.jcapi.server.virtual.dataController;

import de.jattyv.jcapi.server.virtual.dataController.controller.FriendRequestController;
import de.jattyv.jcapi.server.virtual.dataController.controller.GroupController;
import de.jattyv.jcapi.server.virtual.dataController.controller.GroupMessageController;
import de.jattyv.jcapi.server.virtual.dataController.controller.GroupRequestController;
import de.jattyv.jcapi.server.virtual.dataController.controller.MessageController;
import de.jattyv.jcapi.server.virtual.dataController.controller.UserController;
import de.jattyv.jcapi.server.virtual.dataController.data.FriendRequest;
import de.jattyv.jcapi.server.virtual.dataController.data.Group;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupMessage;
import de.jattyv.jcapi.server.virtual.dataController.data.GroupRequest;
import de.jattyv.jcapi.server.virtual.dataController.data.Message;
import de.jattyv.jcapi.server.virtual.dataController.data.User;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class DataController {

    private final LinkedList<User> users;
    private final LinkedList<Message> messages;
    private final LinkedList<Group> groups;
    private final LinkedList<GroupMessage> groupMessages;
    private final LinkedList<GroupRequest> groupRequests;
    private final LinkedList<FriendRequest> friendRequests;

    private final UserController userC;
    private final MessageController msgC;
    private final GroupController groupC;
    private final GroupMessageController groupMsgC;
    private final GroupRequestController groupReqC;
    private final FriendRequestController friendReqC;

    public DataController() {
        users = new LinkedList<>();
        messages = new LinkedList<>();
        groups = new LinkedList<>();
        groupMessages = new LinkedList<>();
        groupRequests = new LinkedList<>();
        friendRequests = new LinkedList<>();
        userC = new UserController(users);
        msgC = new MessageController(messages);
        groupC = new GroupController(groups);
        groupMsgC = new GroupMessageController(groupMessages);
        groupReqC = new GroupRequestController(groupRequests);
        friendReqC = new FriendRequestController(friendRequests);
    }

    public UserController getUserC() {
        return userC;
    }

    public MessageController getMsgC() {
        return msgC;
    }

    public GroupController getGroupC() {
        return groupC;
    }

    public GroupMessageController getGroupMsgC() {
        return groupMsgC;
    }

    public GroupRequestController getGroupReqC() {
        return groupReqC;
    }

    public FriendRequestController getFriendReqC() {
        return friendReqC;
    }

}
