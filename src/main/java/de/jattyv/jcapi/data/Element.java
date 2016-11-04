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
package de.jattyv.jcapi.data;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Element {

    /**
     * The name of the data.
     */
    protected String dataName;
    /**
     * The value of the data.
     */
    protected String data;

    /**
     * Constructs an empty element.
     */
    public Element() {

    }

    /**
     * Constructs an element.
     *
     * @param dataName The needed dataname.
     * @param data The needed data.
     */
    public Element(String dataName, String data) {
        this.dataName = dataName;
        this.data = data;
    }

    /**
     * Returns the dataname.
     *
     * @return The dataname.
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * Sets the dataname to the given one.
     *
     * @param dataName The needed dataName.
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    /**
     * Returns the data.
     *
     * @return The data.
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data to the given one.
     *
     * @param data The needed data.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Sets the dataname and the data to the given ones.
     *
     * @param dataName The needed dataname.
     * @param data The needed data.
     */
    public void setData(String dataName, String data) {
        this.dataName = dataName;
        this.data = data;
    }
}
