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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.data.jobject.Element;
import de.jattyv.jcapi.server.ChatServer;
import de.jattyv.jcapi.server.virtual.dataController.data.Group;
import de.jattyv.jcapi.server.virtual.dataController.data.User;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class LocalDataController {

    private static List<User> users;
    private static List<Group> groups;

    public static final String USERS = "USERS";
    public static final String GROUPS = "GROUPS";

    public static void setLists(List<User> u, List<Group> g) {
        users = u;
        groups = g;
    }

    public static void writeDC() {
        Gson gson = new Gson();
        Container c = new Container();
        c.addE(USERS, gson.toJson(users));
        c.addE(GROUPS, gson.toJson(groups));
        ChatServer.jfc.writeDC(c);
    }

    public static void readDC() {
        Gson gson = new Gson();
        Container c = ChatServer.jfc.readDC();
        if (c != null) {
            Type userType = new TypeToken<List<User>>() {
            }.getType();
            users = gson.fromJson(c.getDataByName(USERS), userType);
            Type groupType = new TypeToken<List<Group>>() {
            }.getType();
            groups = gson.fromJson(c.getDataByName(GROUPS), groupType);
        }
    }

    public static boolean isEmpty() {
        if (users == null) {
            return true;
        }
        if (groups == null) {
            return true;
        }
        return false;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Group> getGroups() {
        return groups;
    }

}
