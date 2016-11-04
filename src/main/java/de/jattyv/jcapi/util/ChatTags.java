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
     * A tag, declaring his content as information about what to do.
     */
    public static final String WORK_TAG = "whatToDo";

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
     * A tag, declaring his content as new logkey that should be set.
     */
    public static final String U_SET_LOG_KEY = "NEWLKEY";
    /**
     * A tag, declaring his content as a registration.
     */
    public static final String U_REGISTRATION = "REGU";
    /**
     * A tag, declaring his content as a login.
     */
    public static final String U_LOGIN = "LOGU";
    /**
     * A tag, declaring his content as the reload information.
     */
    public static final String U_RELOAD = "RELU";
    /**
     * A tag, declaring his content as message.
     */
    public static final String NEW_MESSAGE = "MSG";
    /**
     * A tag, declaring his content as a new friendrequest.
     */
    public static final String NEW_FRIENDREQUEST = "FRQ";
    /**
     * A tag, declaring his content refusing a friendrequest.
     */
    public static final String DECLINE_FRIENDREQUEST = "DFR";
    /**
     * A tag, declaring his content accepting a friendrequest.
     */
    public static final String ACCEPT_FRIENDREQUEST = "AFR";
    /**
     * A tag, declaring his content as deleting a friend.
     */
    public static final String DELETE_FRIEND = "DUF";
    /**
     * A tag, declaring his content as the name of the sender.
     */
    public static final String FROM_USER = "FRU";
    /**
     * A tag, declaring his content as the name of the receiver.
     */
    public static final String TO_USER = "TOU";
    /**
     * A tag, declaring his content as the message.
     */
    public static final String MESSAGE = "MESSAGE";
    /**
     * A tag, declaring his content as the receiver.
     */
    public static final String MSG_TO = "MTO";


}
