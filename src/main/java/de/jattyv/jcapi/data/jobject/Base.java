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
public class Base {

    /**
     * A List of Containers.
     */
    protected List<Container> c;
    /**
     * A List of Elements.
     */
    protected List<Element> e;

    /**
     * Constructs an empty Base.
     */
    public Base() {
        this.c = new LinkedList<>();
        this.e = e = new LinkedList<>();
    }

    /**
     * Constructs a base and sets the lists with the given ones.
     *
     * @param e The list of elements.
     * @param c The list of containers.
     */
    public Base(List<Element> e, List<Container> c) {
        this.e = e;
        this.c = c;
    }

    /**
     * Constructs a base and sets the list of to the given one.
     *
     * @param e The needed list of elements.
     */
    public Base(List<Element> e) {
        this();
        this.e = e;
    }

    /**
     * Returns the list of c.
     *
     * @return The list of c.
     */
    public List<Container> getC() {
        return c;
    }

    /**
     * Sets the list of c to the given one.
     *
     * @param c The needed list of containers.
     */
    public void setC(List<Container> c) {
        this.c = c;
    }

    /**
     * Sets the list of c to the given container.
     *
     * @param c The needed container.
     */
    public void setC(Container c) {
        LinkedList<Container> listOfC = new LinkedList<>();
        listOfC.add(c);
        this.c = listOfC;
    }

    /**
     * Adds a container to the list of c.
     *
     * @param c The needed container.
     */
    public void addC(Container c) {
        this.c.add(c);
    }

    /**
     * Adds a list of container to the list of c.
     *
     * @param c The needed list of commands.
     */
    public void addC(List<Container> c) {
        this.c.addAll(c);
    }

    /**
     * Creates a new Container and add it to the list c.
     *
     * @param superTag The needed supertag for the container.
     * @param e The needed list of elements for the container.
     */
    public void addC(String superTag, List<Element> e) {
        this.c.add(new Container(superTag, e));
    }

    /**
     * Creates a new container and add it to the list c.
     *
     * @param superTag The needed superTag for the container.
     * @param e The needed element for the container.
     */
    public void addC(String superTag, Element e) {
        this.c.add(new Container(superTag, e));
    }

    /**
     * Adds the given element to c at the given position. If the the position
     * doesn't exist, there will be created as many elements until the position
     * is reached. If the supertag isn't the same as the one at the position,
     * the element won't be added.
     *
     * @param superTag The needed supertag for the query.
     * @param e The needed element to add.
     * @param position The position the element should be add.
     */
    public void addDataToC(String superTag, Element e, int position) {
        if (c.size() > position) {
            if (c.get(position).getSuperTag().equals(superTag)) {
            this.getC().get(position).getE().add(e);
            }
        } else {
            for (int i = c.size(); i <= position; i++) {
                this.getC().add(new Container());
            }
            this.getC().set(position, new Container(superTag, e));
        }
    }

    /**
     * Returns the container that includes the given supertag.
     *
     * @param superTag The needed supertag.
     * @return The container that includes the given supertag.
     */
    public Container getDatabySuperTag(String superTag) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getSuperTag().equals(superTag)) {
                return c.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the list e.
     *
     * @return The list e.
     */
    public List<Element> getE() {
        return e;
    }

    /**
     * Sets the list e to the given one.
     *
     * @param e A list of elements.
     */
    public void setE(List<Element> e) {
        this.e = e;
    }

    /**
     * Adds an element to the list e.
     *
     * @param e The element to add.
     */
    public void addE(Element e) {
        this.e.add(e);
    }

    /**
     * Creates a new element and add it to the list e.
     *
     * @param dataName name of the data.
     * @param data The data.
     */
    public void addE(String dataName, String data) {
        this.e.add(new Element(dataName, data));
    }

}
