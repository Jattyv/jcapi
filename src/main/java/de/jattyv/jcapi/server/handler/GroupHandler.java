/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.handler;

import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.server.virtual.dataController.DataController;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler{
    
    public GroupHandler(DataController dc) {
        super(dc);
    }
    
    public void handle(Container c){
        switch(c.getSuperTag()){
            
            case U_CREATE_GROUP:
                break;

            case G_REQUEST_TO_USER:
                break;

            case NEW_GROUP_MESSAGE:
                break;
        }
    }
    
}
