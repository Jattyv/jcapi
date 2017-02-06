/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.HsqldbDatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.jattyv.jcapi.server.virtual.DBController.controller.GroupDaoController;
import de.jattyv.jcapi.server.virtual.DBController.controller.UserDaoController;
import de.jattyv.jcapi.server.virtual.DBController.entities.GroupEntity;
import de.jattyv.jcapi.server.virtual.DBController.entities.UserEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class DBController {

    private GroupDaoController groupDao;
    private UserDaoController userDao;

    private ConnectionSource connectionSource;

    public DBController(String db, String dbUname, String dbPassword) {
        try {
            connectionSource = 
                    new JdbcConnectionSource(db, dbUname, dbPassword);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        try {
            TableUtils.createTable(connectionSource, UserEntity.class);
            TableUtils.createTable(connectionSource, GroupEntity.class);

            Dao<UserEntity, Integer> users = DaoManager.createDao(connectionSource, UserEntity.class);
            Dao<GroupEntity, Integer> groups = DaoManager.createDao(connectionSource, GroupEntity.class);

            userDao = new UserDaoController(users);
            groupDao = new GroupDaoController(groups);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {
        try {
            connectionSource.close();
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GroupDaoController getGroupDao() {
        return groupDao;
    }

    public UserDaoController getUserDao() {
        return userDao;
    }

}
