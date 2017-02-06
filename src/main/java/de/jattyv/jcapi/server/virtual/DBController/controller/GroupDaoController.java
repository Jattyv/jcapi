/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.controller;

import com.j256.ormlite.dao.Dao;
import de.jattyv.jcapi.server.virtual.DBController.entities.GroupEntity;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupDaoController {

    Dao<GroupEntity, Integer> groups;

    public GroupDaoController(Dao<GroupEntity, Integer> groups) {
        this.groups = groups;
    }
    
}
