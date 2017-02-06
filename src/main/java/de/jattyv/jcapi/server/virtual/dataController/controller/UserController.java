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

import de.jattyv.jcapi.server.virtual.dataController.data.User;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class UserController {

    private LinkedList<User> users;

    public UserController(LinkedList<User> users) {
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

}
