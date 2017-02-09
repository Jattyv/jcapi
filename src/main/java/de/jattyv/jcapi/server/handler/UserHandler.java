/*
 * Copyright (C) 2016 Dimitrios Diamantidis <Dimitri.dia@ledimi.com>
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
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.data.jobject.Base;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.DBController.DBController;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import de.jattyv.jcapi.util.factory.JattyvFactory;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class UserHandler extends JattyvHandler {

    private DBController dbc;

    public UserHandler(DataController dc, DBController dbc) {
        super(dc);
        this.dbc = dbc;
    }

    public Base handleFirstInput(Container c) {
        String uName = c.getDataByName(U_NAME);
        String uPassword = c.getDataByName(U_PASSWORD);
        Base b = new Base();
        boolean userLogin = false;
        switch (c.getSuperTag()) {

            case U_REGISTRATION:
                if (dc.getUserC().createUser(uName, uPassword)) {
                    if (dc.getUserC().checkUser(uName, uPassword)) {
                        userLogin = true;
                        Container login = JattyvFactory.createLoginContainer(uName, uPassword);
                        b.addC(login);
                        dc.getUserC().setLkey(uName, login.getDataByName(U_LOG_KEY));
                        dbc.getUserDao().createUser(uName, uPassword);
                    }
                }
                break;

            case U_LOGIN:
                if (dc.getUserC().checkUser(uName, uPassword)) {
                    userLogin = true;
                    Container login = JattyvFactory.createLoginContainer(uName, uPassword);
                    b.addC(login);
                    dc.getUserC().setLkey(uName, login.getDataByName(U_LOG_KEY));
                }
                break;
        }
        if (userLogin) {
            return b;
        }
        return null;
    }

    public void handle(Container c) {

    }

}
