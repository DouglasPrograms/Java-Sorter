package dev.jsort.window;

import javax.swing.*;
import java.awt.*;

public class JConsole extends JFrame {

    private static JTextArea textArea;

    public JConsole(){
        initConsoleDesign();
        initDefaultOptions();
    }

    private void initConsoleDesign(){
        textArea = new JTextArea();
        textArea.setBounds(0,0,1920,1080);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        final Font font = new Font("Cascadia Code", Font.PLAIN,16);
        textArea.setFont(font);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        writeInformation("JSort Application [Version 1.0.0]");
        writeInformation("Written by DougCodez!");
        add(scrollPane);
    }

    private void initDefaultOptions() {
        setSize(new Dimension(1980,1080));
        setResizable(true);
        setVisible(true);
        setTitle("JSort Application Console");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/console.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void writeInformation(String information){
        textArea.append(information + "\n");
    }
}
