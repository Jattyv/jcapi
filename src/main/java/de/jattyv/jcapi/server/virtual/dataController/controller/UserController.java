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
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.server.virtual.dataController.data.User;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class UserController {

    private List<User> users;

    public UserController(List<User> users) {
        this.users = users;
    }

    public boolean createUser(String userName, String userPassword) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return false;
            }
        }
        User newUser = new User(userName, userPassword);
        users.add(newUser);
        return true;
    }

    public boolean checkUser(String userName, String userPassword) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                if (user.getUserPassword().equals(userPassword)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setLkey(String userName, String lkey) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                user.setUserLogKey(lkey);
            }
        }
    }

    public User getUserByLKey(String lkey) {
        for (User user : users) {
            if (user.getUserLogKey() != null && user.getUserLogKey().equals(lkey)) {
                return user;
            }
        }
        return null;
    }

    protected User getUserByName(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void addFriend(String uName, String fname) {
        for (User user : users) {
            if (user.getUserName().equals(uName)) {
                FG fg = new FG(fname, FG.FG_TYPE_FRIEND, fname);
                user.getFgs().add(fg);
                return;
            }
        }
    }

    public void addGroup(String uName, String gName, String gid) {
        for (User user : users) {
            if (user.getUserName().equals(uName)) {
                FG fg = new FG(gName, FG.FG_TYPE_GROUP, gid);
                user.getFgs().add(fg);
                return;
            }
        }
    }

    public List<FG> getFGList(String uName) {
        for (User user : users) {
            if (user.getUserName().equals(uName)) {
                return user.getFgs();
            }
        }
        return null;
    }

    public void remGroup(String uname, String gID) {
        for (User user : users) {
            if (user.getUserName().equals(uname)) {
                for (FG fg : user.getFgs()) {
                    if (fg.getId().equals(gID)) {
                        if (fg.getType() == FG.FG_TYPE_GROUP) {
                            user.getFgs().remove(fg);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void remFriend(String userName, String fName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                for (FG fg : user.getFgs()) {
                    if (fg.getTitle().equals(fName)) {
                        if (fg.getType() == FG.FG_TYPE_FRIEND) {
                            user.getFgs().remove(fg);
                            return;
                        }
                    }
                }
            }
        }
    }

}
