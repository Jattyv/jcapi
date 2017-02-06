/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.server.virtual.DBController.entities;

import com.j256.ormlite.dao.ForeignCollection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
@Entity(name = "Groups")
public class GroupEntity {
    
    
    @Id
    private String Gid;
    
    @Column(nullable = false)
    private String GroupName;
    
    @ManyToOne
    private ForeignCollection<UserEntity> Members;
    
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

    public ForeignCollection<UserEntity> getMembers() {
        return Members;
    }

    public void setMembers(ForeignCollection<UserEntity> Members) {
        this.Members = Members;
    }
    
    
    
    
    
}
