/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.controller;

import com.j256.ormlite.dao.Dao;
import de.jattyv.jcapi.server.virtual.DBController.entities.UserEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class UserDaoController {
    
    private Dao<UserEntity, Integer> users;

    public UserDaoController(Dao<UserEntity, Integer> users) {
        this.users = users;
    }
    
    public List<UserEntity> getUsers(){
        List<UserEntity> res = null;
        try {
            res = users.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void createUser(String uName, String uPassword){
        try {
            UserEntity entity = new UserEntity();
            entity.setUserName(uName);
            entity.setPassword(uPassword);
            users.create(entity);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
