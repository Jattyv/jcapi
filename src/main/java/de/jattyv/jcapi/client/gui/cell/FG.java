/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.client.gui.cell;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class FG {

    
    /**
     * The name of the friend/group.
     */
    private final String title;
    /**
     * The type. (friend/group)
     */
    private final int type;
    /**
     * The id from the friend/group.
     * friend = friendname.
     * group = groupid.
     */
    private final String id;
    
    /**
     * A key declaring his content as a friend.
     */
    public static final int FG_TYPE_FRIEND = 0;
    /**
     * A key declaring his content as a group.
     */
    public static final int FG_TYPE_GROUP = 1;

    /**
     * A constructor for creating an fg and initializing it.
     * 
     * @param title The needed name.
     * @param type The needed type.
     * @param id The needed id.
     */
    public FG(String title, int type, String id) {
        this.title = title;
        this.type = type;
        this.id = id;
    }

    /**
     * Returns the title/name.
     * @return The title/name.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Returns the type.
     * @return The type.
     */
    public int getType() {
        return type;
    }
    /**
     * Returns the id.
     * @return The id.
     */
    public String getId() {
        return id;
    }

}
