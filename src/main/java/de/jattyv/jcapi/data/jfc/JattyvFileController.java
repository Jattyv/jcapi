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
import com.google.gson.reflect.TypeToken;
import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jfc.data.Settings;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
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

    private static final Gson gson = new Gson();

    public final static String J_PROP_FILE = "jattyv.properties";
    public final static String J_FG_EXTENSION = ".json";

    public final static String J_USER_DIR = "Users";

    JattyvFileHandler fileHandler;

    public JattyvFileController(JattyvFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public Settings readSettings() {
        try {
            String settingsAsString = fileHandler.readFile(J_PROP_FILE);
            InputStream propContent = new ByteArrayInputStream(settingsAsString.getBytes(StandardCharsets.US_ASCII));
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

            return config;
        } catch (IOException ex) {
            Logger.getLogger(JattyvFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void writeSettings(Settings settings) {
        String settingsAsString = getSettingsAsString(settings);
        fileHandler.write(J_PROP_FILE, settingsAsString);
    }

    public String getSettingsAsString(Settings settings) {
        Properties prop = new Properties();
        if (settings.isIpAvailable()) {
            prop.put(Settings.IP_ADDRESS, settings.getIp());
        }
        if (settings.isPortAvailable()) {
            prop.put(Settings.PORT, "" + settings.getPort());
        }
        StringWriter writer = new StringWriter();
        prop.list(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }

    public void writeFriends(String userName, List<FG> fgs) {
        String fgsAsString = gson.toJson(fgs);
        fileHandler.write(J_USER_DIR + File.separator + userName + J_FG_EXTENSION, fgsAsString);
    }

    public List<FG> readFriends(String userName) {
        String fgsAsJson = fileHandler.readFile(J_USER_DIR + File.separator + userName + J_FG_EXTENSION);
        Type fgsType = new TypeToken<List<FG>>() {
        }.getType();
        List<FG> fgs = gson.fromJson(fgsAsJson, fgsType);
        return fgs;
    }

}
