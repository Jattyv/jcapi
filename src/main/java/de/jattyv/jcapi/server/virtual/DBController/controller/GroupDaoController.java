/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.controller;

import com.j256.ormlite.dao.Dao;
import de.jattyv.jcapi.server.virtual.DBController.entities.GroupEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GroupDaoController {

    Dao<GroupEntity, Integer> groups;

    public GroupDaoController(Dao<GroupEntity, Integer> groups) {
        this.groups = groups;
    }

    public List<GroupEntity> getGroups() {
        List<GroupEntity> res = null;
        try {
            res = groups.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

}
