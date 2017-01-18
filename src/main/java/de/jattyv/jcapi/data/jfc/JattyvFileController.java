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
package de.jattyv.jcapi.data.jfc;

import com.google.gson.Gson;
import de.jattyv.jcapi.data.jfc.data.ClientSettings;
import de.jattyv.jcapi.data.jfc.data.ServerSettings;
import de.jattyv.jcapi.data.jfc.data.Settings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvFileController {

    private static Gson gson = new Gson();


    public static Settings readSettings(InputStream propContent, JattyvFileHandler jfr) {
        try {
            Properties prop = new Properties();
            prop.load(propContent);
            Settings config = new Settings();
            if (prop.getProperty(Settings.IP_ADDRESS) != null) {
                config.setIp(prop.getProperty(Settings.IP_ADDRESS));
            }
            if (prop.getProperty(Settings.PORT) != null) {
                config.setPort(Integer.parseInt(prop.getProperty(Settings.PORT)));
            }

            if (prop.getProperty(Settings.AUTO_START_SERVER) != null) {
            if (prop.getProperty(Settings.AUTO_START_SERVER).equals("1")) {
                    config.setAutoStartServer(true);
                }
            }
            if (prop.getProperty(Settings.U_NAME) != null) {
                config.setuName(prop.getProperty(Settings.U_NAME));
            }
            if (prop.getProperty(Settings.CLIENT_SETTINGS) != null) {
                config.setClientSettingsPath(prop.getProperty(Settings.CLIENT_SETTINGS));
                String clientFile = jfr.readFile(prop.getProperty(Settings.CLIENT_SETTINGS));
                if (!clientFile.equals("")) {
                    config.setClientSettings(gson.fromJson(clientFile, ClientSettings.class));
                }

            }

            if (prop.getProperty(Settings.SERVER_SETTINGS) != null) {
                config.setServerSettingsPath(prop.getProperty(Settings.SERVER_SETTINGS));
                String serverFile = jfr.readFile(prop.getProperty(Settings.SERVER_SETTINGS));
                if (!serverFile.equals("")) {
                    config.setServerSettings(gson.fromJson(serverFile, ServerSettings.class));
                }
            }

            return config;
        } catch (IOException ex) {
            Logger.getLogger(JattyvFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String writeSettings(Settings settings) {
        Properties prop = new Properties();
        if (settings.isIpAvailable()) {
            prop.put(Settings.IP_ADDRESS, settings.getIp());
        }
        if (settings.isPortAvailable()) {
            prop.put(Settings.PORT, settings.getPort());
        }
        return prop.toString();
    }

    public static Settings readSettings(String propContent, JattyvFileHandler jfr) {
        return readSettings(readConfig(propContent), jfr);
    }

    public static InputStream readConfig(String propContent) {
        InputStream stream = new ByteArrayInputStream(propContent.getBytes(StandardCharsets.UTF_8));
            return stream;
    }

    public static String getFriendsAsJson(List<String> friends) {
        ClientSettings cs = new ClientSettings();
        cs.setFriends(friends);
        return gson.toJson(cs);
    }

}
