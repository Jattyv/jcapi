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
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.data.jobject.Container;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ErrorHandler extends JattyvHandler {

    public ErrorHandler(Handler handler) {
        super(handler);
    }

    void handle(Container c) {
        String errCode = c.getDataByName(ERR_KEY);

        switch (errCode) {

            case LOG_FAIL:
                handler.getWindow().showError(errCode);
                handler.getCl().close();
                break;

        }
    }

}
