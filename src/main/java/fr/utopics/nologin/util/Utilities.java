/*
 * Copyright 2015 Lifok
 *
 * This file is part of NoLogin.

 * NoLogin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoLogin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NoLogin.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.utopics.nologin.util;

import java.io.File;

public class Utilities {

    public static File getMinecraftDirectory() {
        String os = System.getProperty("os.name").toLowerCase();
        String homeDirectory = System.getProperty("user.home", ".");
        File localFile;
        if(os.contains("win")) {
            String roaming = System.getenv("APPDATA");
            if(roaming != null) {
                localFile = new File(roaming, ".minecraft/");
            } else {
                localFile = new File(homeDirectory, ".minecraft/");
            }
        } else if (os.contains("mac")) {
            localFile = new File(homeDirectory, "Library/Application Support/minecraft");
        } else {
            localFile = new File(homeDirectory, ".minecraft/");
        }
        if ((!localFile.exists()) && (!localFile.mkdirs())) {
            return null;
        }
        return localFile;
    }
}
