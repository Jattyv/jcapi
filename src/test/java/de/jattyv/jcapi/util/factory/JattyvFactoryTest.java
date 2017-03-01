/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.jcapi.util.factory;

import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jcapi.util.ChatTags;
import de.jattyv.jcapi.util.KeyTags;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvFactoryTest implements ChatTags, KeyTags{

    String userName = "Josef";
    String userPassword = "Cookie";
    String message = "hi^-^";
    String friend = "Hans";
    String groupName = "2Friends";
    String groupID = "JmJlfDppljft";

    FG fg0 = new FG("1", 0, "1");
    FG fg1 = new FG("2", 1, "2556");

    @Test
    public void createLoginContainerTest() {
        Container c = JattyvFactory.createLoginContainer(userName, userPassword);
        Assert.assertEquals(SESSION_SETTINGS, c.getSuperTag());
        Assert.assertEquals(userName, c.getDataByName(U_NAME));
        Assert.assertNotNull(c.getDataByName(U_LOG_KEY));
    }

    @Test
    public void createLoginFailedContainerTest() {
        Container c = JattyvFactory.createLoginFailedContainer();
        Assert.assertEquals(JERROR, c.getSuperTag());
        Assert.assertEquals(LOG_FAIL, c.getDataByName(ERR_KEY));
    }

    @Test
    public void createErrorContainerTest() {
        String errkey = "FTEST";
        Container c = JattyvFactory.createErrorContainer(errkey);
        Assert.assertEquals(JERROR, c.getSuperTag());
        Assert.assertEquals(errkey, c.getDataByName(ERR_KEY));
    }

    @Test
    public void createMessageContainerTest() {
        Container c = JattyvFactory.createMessageContainer(userName, friend, message);
        Assert.assertEquals(NEW_MESSAGE, c.getSuperTag());
        Assert.assertEquals(userName, c.getDataByName(FROM_USER));
        Assert.assertEquals(friend, c.getDataByName(TO_USER));
        Assert.assertEquals(message, c.getDataByName(MESSAGE));
    }

    @Test
    public void createGroupRequestContainerTest() {
        Container c = JattyvFactory.createGroupRequestContainer(groupName, groupID);
        Assert.assertEquals(G_REQUEST_TO_USER, c.getSuperTag());
        Assert.assertEquals(groupName, c.getDataByName(GROUP_NAME));
        Assert.assertEquals(groupID, c.getDataByName(GROUP_ID));
    }

    @Test
    public void createGroupMessageContainerTest() {
        Container c = JattyvFactory.createGroupMessageContainer(userName, groupID, message);
        Assert.assertEquals(NEW_GROUP_MESSAGE, c.getSuperTag());
        Assert.assertEquals(userName, c.getDataByName(FROM_USER));
        Assert.assertEquals(groupID, c.getDataByName(GROUP_ID));
        Assert.assertEquals(message, c.getDataByName(MESSAGE));
    }

    @Test
    public void createFriendRequestContainerTest() {
        Container c = JattyvFactory.createFriendRequestContainer(userName, friend);
        Assert.assertEquals(U_FRIENDREQUEST, c.getSuperTag());
        Assert.assertEquals(userName, c.getDataByName(FROM_USER));
        Assert.assertEquals(friend, c.getDataByName(TO_USER));
    }

    @Test
    public void createFGListContainerTest() {
        List<FG> fgs = new LinkedList<>();
        fgs.add(fg0);
        fgs.add(fg1);
        Container c = JattyvFactory.createFGListContainer(fgs);
        Assert.assertEquals(U_FGLIST, c.getSuperTag());
        for (int i = 0; i<c.getC().size(); i++) {
            Assert.assertEquals(fgs.get(i).getTitle(),c.getC().get(i).getDataByName(FG_NAME));
            Assert.assertEquals(""+fgs.get(i).getType(),c.getC().get(i).getDataByName(FG_TYPE));
            Assert.assertEquals(fgs.get(i).getId(),c.getC().get(i).getDataByName(FG_ID));
        }
    }

}
