/*
 * Copyright (C) 2017 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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
package de.jattyv.jcapi.data.jfc.data;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Settings {

    public static final String IP_ADDRESS = "ip";
    public static final String PORT = "port";
    public static final String AUTO_START_SERVER = "AutoStartServer";
    public static final String U_NAME = "UserName";
    public static final String SERVER_SETTINGS = "ServerSettingsPath";
    
    private String ip;
    private int port;
    private boolean autoStartServer;
    private String uName;
    private String serverSettingsPath;

    private boolean ipAvailable;
    private boolean portAvailable;
    private boolean autoStartServerAvailable;
    private boolean uNameAvailable;

    public Settings() {
        ipAvailable = false;
        portAvailable = false;
        autoStartServerAvailable = false;
        uNameAvailable = false;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        ipAvailable = true;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        portAvailable = true;
    }

    public boolean isAutoStartServer() {
        return autoStartServer;
    }

    public void setAutoStartServer(boolean autoStartServer) {
        this.autoStartServer = autoStartServer;
        autoStartServerAvailable = true;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
        uNameAvailable = true;
    }

    public boolean isIpAvailable() {
        return ipAvailable;
    }

    public void setIpAvailable(boolean ipAvailable) {
        this.ipAvailable = ipAvailable;
    }

    public boolean isPortAvailable() {
        return portAvailable;
    }

    public void setPortAvailable(boolean portAvailable) {
        this.portAvailable = portAvailable;
    }

    public boolean isAutoStartServerAvailable() {
        return autoStartServerAvailable;
    }

    public void setAutoStartServerAvailable(boolean autoStartServerAvailable) {
        this.autoStartServerAvailable = autoStartServerAvailable;
    }

    public boolean isuNameAvailable() {
        return uNameAvailable;
    }

    public void setuNameAvailable(boolean uNameAvailable) {
        this.uNameAvailable = uNameAvailable;
    }

    public String getServerSettingsPath() {
        return serverSettingsPath;
    }

}
