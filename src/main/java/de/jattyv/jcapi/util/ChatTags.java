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
package de.jattyv.jcapi.util;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public interface ChatTags {

    /**
     * A tag, declaring his content as settings.
     */
    public static final String SESSION_SETTINGS = "Settings";
    /**
     * A tag, declaring his content as the user name.
     */
    public static final String U_NAME = "uName";
    /**
     * A tag, declaring his content as the user passowrd.
     */
    public static final String U_PASSWORD = "uPassword";
    /**
     * A tag, declaring his content as the user logkey.
     */
    public static final String U_LOG_KEY = "uLogKey";
    /**
     * A tag, declaring his content as a registration.
     */
    public static final String U_REGISTRATION = "REGU";
    /**
     * A tag, declaring his content as a login.
     */
    public static final String U_LOGIN = "LOGU";
    /**
     * A tag, declaring his content as message.
     */
    public static final String NEW_MESSAGE = "MSG";
    /**
     * A tag, declaring his content as the name of the sender.
     */
    public static final String FROM_USER = "FRU";
    /**
     * A tag, declaring his content as the name of the receiver.
     */
    public static final String TO_USER = "TOU";
    /**
     * A tag, declaring his content as the receiver.
     */
    public static final String MSG_TO = "MTO";
    /**
     * A tag, declaring his content as the message.
     */
    public static final String MESSAGE = "MESSAGE";
    /**
     * A tag, declaring his content as the name of the group.
     */
    public static final String GROUP_NAME = "GN";
    /**
     * A tag, declaring his content as the unique id of the group.
     */
    public static final String GROUP_ID = "GID";
    /**
     * A tag, declaring his content for creation of a group.
     */
    public static final String U_CREATE_GROUP = "UCG";
    /**
     * A tag, declaring his content as a request to join a group.
     */
    public static final String G_REQUEST_TO_USER = "GRTU";
    /**
     * A tag, declaring his content for an successfully join to the group.
     */
    public static final String U_AGREE_GROUP = "UATG";
    /**
     * A tag, delaring his content as an request to remove a group from FG-list.
     */
    public static final String U_REM_GROUP = "URGFFG";
    /**
     * A tag, declaring his content as an groupmessage.
     */
    public static final String NEW_GROUP_MESSAGE = "NGMSG";
    /**
     * A tag, declaring his content as an request to an friend.
     */
    public static final String U_REQUEST_TO_FRIEND = "URTF";
    /**
     * A tag, declaring his content as an friendrequest.
     */
    public static final String U_FRIENDREQUEST = "UFR";
    /**
     * A tag, declaring his cotent as an accept for an friendrequest.
     */
    public static final String U_AGREE_FRIEND = "AFR";
    /**
     * A tag, delaring his content as an request to remove a friend from FG-list.
     */
    public static final String U_REM_FRIEND = "RFFFG";
    /**
     * A tag, declaring his content as a friend/group list.
     */
    public static final String U_FGLIST = "UFGL";
    /**
     * A tag, declaring his content as the name of the FG;
     */
    public static final String FG_NAME = "FGN";
    /**
     * A tag, declaring his content as the id of the FG.
     */
    public static final String FG_ID = "FGID";
    /**
     * A tag, declaring his content as the type of the FG.
     */
    public static final String FG_TYPE = "FGTYPE";
    /**
     * A tag, declaring his content as an error.
     */
    public static final String JERROR = "jerror";
    /**
     * A tag, declaring his content as the errorcode.
     */
    public static final String ERR_KEY = "jerrorkey";
    /**
     * A tag, declaring an error while log in.
     */
    public static final String LOG_FAIL = "LOGF";

}
