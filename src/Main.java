import core.FileManager;
import gui.LoginGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        fileManager.createDataFolder();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}