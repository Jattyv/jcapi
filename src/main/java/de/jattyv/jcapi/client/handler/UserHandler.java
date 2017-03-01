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
package de.jattyv.jcapi.client.handler;

import de.jattyv.jcapi.client.Chat;
import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jobject.Container;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class UserHandler extends JattyvHandler {

    private final List<FG> fgs;

    public UserHandler(Handler handler) {
        super(handler);
        this.fgs = new LinkedList<>();
    }

    public void handle(Container c) {
        List<FG> fgs;

        switch (c.getSuperTag()) {

            case U_FGLIST:
                fgs = new LinkedList<>();
                for (Container cfg : c.getC()) {
                    String fgTitle = cfg.getDataByName(FG_NAME);
                    String fgId = cfg.getDataByName(FG_ID);
                    int fgType = Integer.parseInt(cfg.getDataByName(FG_TYPE));
                    FG fg = new FG(fgTitle, fgType, fgId);
                    fgs.add(fg);
                }
                handler.getWindow().updateFGList(fgs);
                Chat.jfc.writeFriends(handler.getUser().getName(), fgs);
                break;

            case SESSION_SETTINGS:
                handler.getUser().setLogKey(c.getDataByName(U_LOG_KEY));
                handler.getWindow().changeWindow(JGui.CHAT_WINDOW);
                fgs = Chat.jfc.readFriends(handler.getUser().getName());
                if (fgs != null) {
                    handler.getWindow().updateFGList(fgs);
                }
                break;
        }
    }

}
