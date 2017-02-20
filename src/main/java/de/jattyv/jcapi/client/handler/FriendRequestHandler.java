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
public class FriendRequestHandler extends JattyvHandler {

    public FriendRequestHandler(Handler handler) {
        super(handler);
    }
    
    public void handle(Container c){
        switch(c.getSuperTag()){
            case U_FRIENDREQUEST:
                String fName = c.getDataByName(FROM_USER);
                handler.getWindow().addFriend(fName);
                break;
        }
    }

}
