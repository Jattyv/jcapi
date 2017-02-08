/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
@Entity(name = "Groups")
public class GroupEntity implements Serializable{
    
    
    @Id
    private String Gid;
    
    @Column(nullable = false)
    private String GroupName;
    
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private LinkedList<UserEntity> Members;
    
    public GroupEntity(){
        
    }

    public String getGid() {
        return Gid;
    }

    public void setGid(String Gid) {
        this.Gid = Gid;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public LinkedList<UserEntity> getMembers() {
        return Members;
    }

    public void setMembers(LinkedList<UserEntity> Members) {
        this.Members = Members;
    }
    
    
    
    
    
}
