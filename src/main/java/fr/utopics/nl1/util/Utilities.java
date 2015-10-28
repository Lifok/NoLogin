package fr.utopics.nl1.util;

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
