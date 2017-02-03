/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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
package de.jattyv.jcapi.util;

import de.jattyv.jcapi.data.jobject.Container;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Packer implements ChatTags {

    public static Container packNewMessage(String userLkey, String toUser, String msg) {
        Container c = new Container();
        c.setSuperTag(NEW_MESSAGE);
        c.addE(FROM_USER, userLkey);
        c.addE(TO_USER, toUser);
        c.addE(MESSAGE, msg);
        return c;
    }

    public static Container packLogin(String userName, String userPassword) {
        Container c = new Container();
        c.setSuperTag(U_LOGIN);
        c.addE(U_NAME, userName);
        c.addE(U_PASSWORD, userPassword);
        return c;
    }

    public static Container packRegistration(String userName, String userPassword) {
        Container c = new Container();
        c.setSuperTag(U_REGISTRATION);
        c.addE(U_NAME, userName);
        c.addE(U_PASSWORD, userPassword);
        return c;
    }
    
    public static Container packCreateGroup(String gName, String uName){
        Container c = new Container();
        c.setSuperTag(U_CREATE_GROUP);
        c.addE(GROUP_NAME, gName);
        c.addE(U_NAME, uName);
        return c;
    }
    
    public static Container packAddUserToGroup(String gName, String fName){
        Container c = new Container();
        c.setSuperTag(G_REQUEST_TO_USER);
        c.addE(GROUP_NAME, gName);
        c.addE(TO_USER, fName);
        return c;
    }
    
    public static Container packNewGroupMessage(String userLkey, String gName, String msg){
        Container c = new Container();
        c.setSuperTag(NEW_GROUP_MESSAGE);
        c.addE(FROM_USER, userLkey);
        c.addE(GROUP_NAME, gName);
        c.addE(MESSAGE, msg);
        return c;
    }

}
