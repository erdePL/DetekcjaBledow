package detekcjabledow;

import javax.swing.*;

public class DetekcjaBledow {

    public static void main (String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void createAndShowGUI() throws Exception {
        new MainWindowView();
    }
}

