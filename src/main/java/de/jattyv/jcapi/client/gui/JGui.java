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
package de.jattyv.jcapi.client.gui;

import de.jattyv.jcapi.client.gui.cell.FG;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public interface JGui {

    public static final String LOGIN_WINDOW = "wlogin";
    public static final String REGISTRATION_WINDOW = "wregist";
    public static final String CHAT_WINDOW = "cwindow";

    public static final String ALERT_TYPE_INFO = "info";
    public static final String ALERT_TYPE_CERT = "newcert";

    public void addFriend(String fName);

    public void addGroup(String gName, String gID);

    public void changeWindow(String window);

    public void showError(String errKey);

    public boolean alert(String msg, String alertType);

    public void addMessage(String fName, String message);

    public void addGroupMessage(String gID, String message);
    
    public void updateFGList(List<FG> fgs);

}
