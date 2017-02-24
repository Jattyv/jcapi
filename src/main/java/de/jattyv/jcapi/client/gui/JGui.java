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

    /**
     * The key for the login card.
     */
    public static final String LOGIN_WINDOW = "wlogin";
    /**
     * The key for the registration card.
     */
    public static final String REGISTRATION_WINDOW = "wregist";
    /**
     * the card for the chatting card.
     */
    public static final String CHAT_WINDOW = "cwindow";

    
    /**
     * A key declaring his content as an information for alert types.
     */
    public static final String ALERT_TYPE_INFO = "info";
    /**
     * A key declaring his content as an information about a new certificat for alert types.
     */
    public static final String ALERT_TYPE_CERT = "newcert";

    /**
     * Asks for adding a friend.
     * 
     * @param fName The name of the friend.
     */
    public void addFriend(String fName);

    /**
     * Asks for adding a group.
     * 
     * @param gName The name of the group, for displaying.
     * @param gID The groupid for the answer when acepting.
     */
    public void addGroup(String gName, String gID);

    /**
     * Changes the window.
     * 
     * @param window The window to open.
     */
    public void changeWindow(String window);

    /**
     * Shows an alert that contains an errormessage.
     * 
     * @param errKey The errorkey.
     */
    public void showError(String errKey);

    /**
     * Shows an alert.
     * 
     * @param msg The alertmessage.
     * @param alertType The type of alert.
     * @return The decision.(YES/NO)
     */
    public boolean alert(String msg, String alertType);

    /**
     * Adds a new friendmessage.
     * 
     * @param fName The name of the friend.
     * @param message The message.
     */
    public void addMessage(String fName, String message);

    /**
     * Adds a new groupmessage.
     * 
     * @param gID The id of the group.
     * @param message The message.
     */
    public void addGroupMessage(String gID, String message);
    
    /**
     * Updates the list of friends/groups.
     * 
     * @param fgs The new list of friends/groups.
     */
    public void updateFGList(List<FG> fgs);

}
