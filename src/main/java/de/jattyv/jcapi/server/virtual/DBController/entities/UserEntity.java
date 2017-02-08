/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
@Entity(name = "Users")
public class UserEntity implements Serializable{
    
    @Id
    private String UserName;
    
    @Column(nullable = false)
    private String Password;
    
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    LinkedList<UserEntity> friends;
    
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    LinkedList<GroupEntity> groups;
    
    public UserEntity(){
        
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

    public LinkedList<UserEntity> getFriends() {
        return friends;
    }

    public void setFriends(LinkedList<UserEntity> friends) {
        this.friends = friends;
    }

    public LinkedList<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(LinkedList<GroupEntity> groups) {
        this.groups = groups;
    }
    
    
    
    
    
}
