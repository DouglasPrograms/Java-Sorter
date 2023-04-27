package dev.jsort;

import dev.jsort.window.JConsole;
import dev.jsort.window.JSortWindow;

import javax.swing.*;

public class JSortMainMenu {

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            new JSortWindow();
            new JConsole();
        });
    }
}
