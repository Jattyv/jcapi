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
package de.jattyv.jcapi.server.virtual.dataController.data;

import de.jattyv.jcapi.client.gui.cell.FG;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class User {

    private String userName;
    private String userLogKey;
    private String userPassword;
    private List<FG> fgs;

    public User() {

    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        fgs = new LinkedList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogKey() {
        return userLogKey;
    }

    public void setUserLogKey(String userLogKey) {
        this.userLogKey = userLogKey;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<FG> getFgs() {
        return fgs;
    }

    public void setFgs(List<FG> fgs) {
        this.fgs = fgs;
    }

}
