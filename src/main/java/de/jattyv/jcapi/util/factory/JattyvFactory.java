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
package de.jattyv.jcapi.util.factory;

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.data.jobject.Element;
import de.jattyv.jcapi.util.ChatTags;
import de.jattyv.jcapi.util.crypt.LKeyGenerator;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvFactory implements ChatTags {

    public static Container createLoginContainer(String uname, String upassword) {
        Container login = new Container();
        login.setSuperTag(SESSION_SETTINGS);
        login.addE(new Element(U_LOG_KEY, LKeyGenerator.generateLKey(uname, upassword)));
        login.addE(new Element(U_NAME, uname));
        return login;
    }

    public static Container createLoginFailedContainer() {
        Container fLogin = new Container();
        fLogin.setSuperTag(JERROR);
        fLogin.addE(new Element(ERR_KEY, LOG_FAIL));
        return fLogin;
    }

    public static Container createMessageContainer(String fromUser, String toUser, String message) {
        Container msg = new Container();
        msg.setSuperTag(NEW_MESSAGE);
        msg.addE(new Element(FROM_USER, fromUser));
        msg.addE(new Element(TO_USER, toUser));
        msg.addE(new Element(MESSAGE, message));

        return msg;
    }
}
