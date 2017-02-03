/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.data.jobject.Container;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler {

    public GroupHandler(Handler handler) {
        super(handler);
    }

    public void handle(Container c) {
        String gname = c.getDataByName(GROUP_NAME);
        switch (c.getSuperTag()) {
            
            case G_REQUEST_TO_USER:
                handler.getWindow().addGroup(gname);
                break;

            case NEW_GROUP_MESSAGE:
                String fname = c.getDataByName(FROM_USER);
                String msg = c.getDataByName(MESSAGE);
                handler.getWindow().addGroupMessage(gname, fname+": "+msg);
                break;
        }

    }

}
