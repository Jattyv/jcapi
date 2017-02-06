/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.entities;

import com.j256.ormlite.field.DatabaseField;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
@Entity(name = "Users")
public class UserEntity {
    
    @Id
    private String UserName;
    
    @Column(nullable = false)
    private String Password;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private GroupEntity group;
    
    UserEntity(){
        
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
    
}
