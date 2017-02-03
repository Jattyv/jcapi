/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.data.jobject.Container;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupHandler extends JattyvHandler {

    private Map<String, LinkedList<String>> messages;

    public GroupHandler(Handler handler) {
        super(handler);
        messages = new HashMap<>();
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
                String fmsg = fname + ": " + msg;
                handler.getWindow().addGroupMessage(gname, fmsg);
                addMessage(gname, fmsg);
                break;
        }
    }

    private void addMessage(String gname, String message) {
        if(messages.containsKey(gname)){
            messages.get(gname).add(message);
        }else{
            LinkedList<String> tmp = new LinkedList<>();
            tmp.add(message);
            messages.put(gname, tmp);
        }
    }
    
    public LinkedList<String> getGroupMessages(String gname){
        if(messages.containsKey(gname)){
            return messages.get(gname);
        }
        return new LinkedList<String>();
    }

}
