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
package de.jattyv.jcapi.server;

import de.jattyv.jcapi.server.network.JServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvServer extends Thread {

    JServer server;

    public JattyvServer(JServer server) {
        this.server = server;
    }

    public void init() {
        Logger.getLogger(JServer.class.getName()).log(Level.INFO, "Jattyv-Server started");
        server.listen();
    }

    @Override
    public void run() {
        init();
    }

}
