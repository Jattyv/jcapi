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
package de.jattyv.jcapi.server.network;

import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.server.handler.ReloadHandler;
import de.jattyv.jcapi.server.virtual.DBController.DBController;
import de.jattyv.jcapi.server.virtual.dataController.DataController;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JServer {

    protected int port;
    protected boolean running;
    protected ReloadHandler reloadHandler;
    protected DataController dc;
    protected DBController dbc;

    public JServer(Settings settings) {
        init(settings);
    }

    public JServer(int port) {
        Settings settings = new Settings();
        settings.setPort(port);
        init(settings);

    }

    private void init(Settings settings) {
        this.port = settings.getPort();
        running = true;
        dc = new DataController();
        String db = "jdbc:hsqldb:file:~/jattyv/jattyv";
        String dbUname = "jattyv";
        String dbPassword = "jattyv";
        if (settings.isServerDBAvailable()) {
            db = settings.getServerDB();
            if (settings.isServerDBUserNameAvailable()) {
                dbUname = settings.getServerDBUserName();
                if (settings.isServerDBPasswordAvailable()) {
                    dbPassword = settings.getServerDBPassword();
                }
            }
        }
        dbc = new DBController(db, dbUname, dbPassword);
        dbc.init();
        dc.loadDataFromDB(dbc);
        reloadHandler = new ReloadHandler(dc);
    }

    public void listen() {

    }

    public void startServices() {
        new Thread(reloadHandler).start();
    }

}
