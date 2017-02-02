/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.dataController.controller;

import de.jattyv.jcapi.server.virtual.dataController.data.Group;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupController {
    
    private LinkedList<Group> groups;

    public GroupController(LinkedList<Group> groups) {
        this.groups = groups;
    }
    
}
