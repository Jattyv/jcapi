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
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.server.network.Client;
import de.jattyv.jcapi.server.virtual.dataController.DataController;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MReloadHandler {

    private static LinkedList<CReloadHandler> crh;
    private static DataController dc;

    public MReloadHandler(DataController dc) {
        this.dc = dc;
        crh = new LinkedList<>();
    }

    public static void addClient(Client cl) {
        CReloadHandler handler = new CReloadHandler(dc, cl);
        crh.add(handler);
        Thread t = new Thread(handler);
        t.start();
    }

    public static void removeUser(String uName) {
        for (int i = 0; i < crh.size(); i++) {
            if (crh.get(i).getuName().equals(uName)) {
                crh.remove(i);
                return;
            }
        }
    }

    public static void turnOnMessageReload(String uname) {
        for (CReloadHandler cr : crh) {
            if (cr.getuName().equals(uname)) {
                cr.getCl().setNewMessages(true);
                return;
            }
        }
    }

    public static void turnOnGroupMessageReload(String uname) {
        for (CReloadHandler cr : crh) {
            if (cr.getuName().equals(uname)) {
                cr.getCl().setNewGroupMessage(true);
                return;
            }
        }
    }

    public static void turnOnGroupRequestReload(String uname) {
        for (CReloadHandler cr : crh) {
            if (cr.getuName().equals(uname)) {
                cr.getCl().setNewGroupRequest(true);
                return;
            }
        }
    }

    public static void turnOnFriendRequestReload(String uname) {
        for (CReloadHandler cr : crh) {
            if (cr.getuName().equals(uname)) {
                cr.getCl().setNewFriendRequest(true);
                return;
            }
        }
    }

    public static void turnOnNewFGList(String uname) {
        for (CReloadHandler cr : crh) {
            if (cr.getuName().equals(uname)) {
                cr.getCl().setNewFGList(true);
                return;
            }
        }
    }

}
