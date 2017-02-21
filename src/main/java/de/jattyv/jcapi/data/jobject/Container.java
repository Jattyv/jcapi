/*
 * Copyright (C) 2016 Dimitrios Diamantidis <Dimitri.dia@ledimi.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jattyv.jcapi.data.jobject;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Container {

    
    /**
     * The tag of the container.
     */
    protected String superTag;
    /**
     * A list of elements.
     */
    protected List<Element> e;
    /**
     * A list of container. Is useful for multi dimensional container.
     */
    protected List<Container> c;

    /**
     * Constructs an empty container.
     */
    public Container() {
        e = new LinkedList<>();
        c = new LinkedList<>();
        this.superTag = "";
    }

    /**
     * Constructs a container. Sets the supertag, the list of c and the list of
     * e to the given * one.
     *
     * @param superTag The needed supertag.
     * @param c The needed list of containers.
     * @param e The needed list of elements.
     */
    public Container(String superTag, List<Container> c, List<Element> e) {
        this();
        this.superTag = superTag;
        this.addE(e);
        this.addC(c);
    }

    /**
     * Constructs a container. Sets the supertag and the list of e to the given
     * one.
     *
     * @param superTag The needed supertag.
     * @param e The needed list of elements.
     */
    public Container(String superTag, List<Element> e) {
        this();
        this.superTag = superTag;
        this.addE(e);
    }

    /**
     * Constructs a container. Sets the supertag and the list of e to the given
     * one. Creates a new list of e.
     *
     * @param superTag The needed supertag.
     * @param e The needed element.
     */
    public Container(String superTag, Element e) {
        this();
        this.superTag = superTag;
        LinkedList<Element> listOfe = new LinkedList<>();
        listOfe.add(e);
        this.addE(e);
    }

    /**
     * Returns the supertag of this container.
     *
     * @return The supertag of this container.
     */
    public String getSuperTag() {
        return superTag;
    }

    /**
     * Sets the supertag to the given one.
     *
     * @param superTag The needed supertag.
     */
    public void setSuperTag(String superTag) {
        if (this.superTag.equals("")) {
            this.superTag = superTag;
        }
    }

    /**
     * Returns the list of e.
     *
     * @return The list of e.
     */
    public List<Element> getE() {
        return e;
    }

    /**
     * Returns the element that include the given dataname.
     *
     * @param dataName The needed dataname.
     * @return The element that include the given dataname.
     */
    public Element getElementByName(String dataName) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getDataName().equals(dataName)) {
                return e.get(i);
            }
        }
        return null;
    }

    public String getDataByName(String dataName) {
        Element res = getElementByName(dataName);
        if (res == null) {
            return null;
        }
        return res.getData();
    }

    /**
     * Clean the list of e and adds than given list to the new list of e.
     *
     * @param e The needed list of elements.
     */
    public void setE(List<Element> e) {
        this.e = new LinkedList<>();
        this.addE(e);
    }

    public void setE(String dataName, String data) {
        this.e = new LinkedList<>();
        this.addE(new Element(dataName, data));
    }

    /**
     * Adds every element from the given list to the list of e.
     *
     * @param e The needed list.
     */
    public void addE(List<Element> e) {
        for (Element tmp : e) {
            this.addE(tmp);
        }
    }

    /**
     * Adds an elemet to the list of e. Counts size.
     *
     * @param e The needed element.
     */
    public void addE(Element e) {
        this.e.add(e);
    }

    /**
     * Creates a new Element and adds it to the list of e.
     *
     * @param dataName The needed dataname for the element.
     * @param data The needed data for the element.
     */
    public void addE(String dataName, String data) {
        this.e.add(new Element(dataName, data));
    }

    /**
     * Sets the data with the given dataname to the new data.
     *
     * @param dataName The needed dataname for the query.
     * @param newData The new data.
     */
    public void setDataByName(String dataName, String newData) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getDataName().equals(dataName)) {
                e.get(i).setData(newData);
            }
        }
    }

    public List<Container> getC() {
        return c;
    }

    public void setC(List<Container> c) {
        this.c = c;
    }

    public void addC(Container c) {
        this.c.add(c);
    }

    public void addC(List<Container> c) {
        this.c.addAll(c);
    }

}
