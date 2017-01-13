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

import de.jattyv.jcapi.data.jfc.data.Settings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class JattyvFileController {

    public static Settings readSettings(InputStream propContent) {
        try {
            Properties prop = new Properties();
            prop.load(propContent);
            Settings config = new Settings();
            config.setIp(prop.getProperty(Settings.IP_ADDRESS));
            config.setPort(Integer.parseInt(prop.getProperty(Settings.PORT)));
            if (prop.getProperty(Settings.AUTO_START_SERVER) != null) {
                if (prop.getProperty(Settings.AUTO_START_SERVER).equals("1")) {
                    config.setAutoStartServer(true);
                } else {
                    config.setAutoStartServer(false);
                }
            } else {
                config.setAutoStartServer(false);
            }
            return config;
        } catch (IOException ex) {
            Logger.getLogger(JattyvFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Settings readSettings(String propContent) {
        Properties prop = readConfig(propContent);
        Settings config = new Settings();
        config.setIp(prop.getProperty(Settings.IP_ADDRESS));
        config.setPort(Integer.parseInt(prop.getProperty(Settings.PORT)));
        return config;
    }

    public static Properties readConfig(String propContent) {
        try {
            InputStream stream = new ByteArrayInputStream(propContent.getBytes(StandardCharsets.UTF_8));
            Properties prop = new Properties();
            prop.load(stream);
            return prop;
        } catch (IOException ex) {
            Logger.getLogger(JattyvFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
