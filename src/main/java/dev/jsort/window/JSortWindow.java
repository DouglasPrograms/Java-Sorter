package dev.jsort.window;

import dev.jsort.algorithm.types.*;
import dev.jsort.util.NumbersList;
import dev.jsort.algorithm.ISort;
import dev.jsort.util.SoundPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static dev.jsort.window.JConsole.*;

public class JSortWindow extends JFrame {

    private static NumbersList numbersList = NumbersList.create(ThreadLocalRandom.current().nextInt(10, 100));

    private final String prefix = "JSort >> ";
    public static int WIDTH = 1280, HEIGHT = 720;

    private static final JPanel graphicsPanel;

    private static JSlider speedSlider;
    private JComboBox<String> sortSelections;
    private JButton shuffleButton;

    private JButton customValuesInitButton;
    private JButton start;

    private ISort sort;

    private static SoundPlayer soundPlayer;


    static {
        graphicsPanel = new GraphicsPanel();
    }

    public JSortWindow() {
        initSouthBorder();
        initDefaultOptions();
        initWidgetActions();
        add(graphicsPanel);
        numbersList.generateRandomList();
        graphicsPanel.repaint();
        pack();
    }

    private void initSouthBorder() {
        soundPlayer = new SoundPlayer(GraphicsPanel.currentIndex);
        JPanel southBorder = new JPanel();
        sortSelections = new JComboBox<>(new String[]{"Bogo", "Bubble", "Insertion", "Quick", "Selection"});
        int min = 0, max = 1000, initial = 500;
        speedSlider = new JSlider(JSlider.HORIZONTAL, min, max, initial);
        speedSlider.setMajorTickSpacing(500);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintLabels(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setBackground(Color.LIGHT_GRAY);
        shuffleButton = new JButton();
        shuffleButton.setText("Shuffle");
        customValuesInitButton = new JButton("Set Custom Values");
        start = new JButton();
        start.setText("Start");
        southBorder.add(speedSlider);
        southBorder.add(sortSelections);
        southBorder.add(shuffleButton);
        southBorder.add(customValuesInitButton);
        southBorder.add(start);
        add(southBorder, BorderLayout.SOUTH);
    }

    private void initWidgetActions() {


        //This action will allow the user to adjust the speed of the sorting algorithm
        speedSlider.addChangeListener((ChangeEvent event) -> {
            if (sort != null)
                sort.getDelay();
        });


        //This action will allow the user to generate a list of random numbers with a new size.
        shuffleButton.addActionListener(e -> {
            AtomicInteger size = new AtomicInteger(ThreadLocalRandom.current().nextInt(10, 100));
            numbersList = NumbersList.create(size.get());
            writeInformation(prefix + "Created a new numbers object! Size: " + size);
            numbersList.generateRandomList();
            graphicsPanel.repaint();
            writeInformation(prefix + "Generated a list of random numbers! Sample Set: " + Arrays.toString(numbersList.getGeneratedNumbers()));
            //Init repaint components
        });

        //This action will the user to initialize their own custom values;
        customValuesInitButton.addActionListener(e -> {
            String joption = JOptionPane.showInputDialog("Custom Values Pane", "Enter custom values here!");
            if(joption == null){
                JOptionPane.showMessageDialog(null, "ERROR! This JOptionPane is null!");
                return;
            }
            if (!joption.contains(",")) {
                JOptionPane.showMessageDialog(null, "ERROR! You must put a comma in between each value!");
                return;
            }
            String[] modifiedText = joption.split(",");
            for (String values : modifiedText) {
                if (Double.parseDouble(values) > HEIGHT) {
                    JOptionPane.showMessageDialog(null, "ERROR! Custom values can be no greater than 720!");
                    return;
                }
            }
            numbersList = NumbersList.create(joption.split(",").length);
            numbersList.initCustomValues(joption);
            writeInformation(prefix + "Generated a list of custom values! Sample Set: " + Arrays.toString(numbersList.getGeneratedNumbers()));
            graphicsPanel.repaint();

        });

        //This action will allow the user to start the sorting program
        start.addActionListener(e -> {
            long started = System.currentTimeMillis();
            writeInformation(prefix + "Sorting in progress...");
            //Disabling all widgets
            toggleWidgets(false);

            switch (sortSelections.getSelectedIndex()) {
                case 0 -> sort = new Bogo();
                case 1 -> sort = new Bubble();
                case 2 -> sort = new Insertion();
                case 3 -> sort = new Quick();
                case 4 -> sort = new Selection();
            }

            new Thread(() -> {
                sort.sort(numbersList.getGeneratedNumbers());
                toggleWidgets(true);
                long ended = System.currentTimeMillis() - started;
                writeInformation(prefix + "Done sorting! Results of the test are listed below!");
                writeInformation("Sorting Algorithm: " + sort.getAlgorithmName());
                writeInformation("Sample Set Size: " + numbersList.getGeneratedNumbers().length);
                writeInformation("Sample Set: " + Arrays.toString(numbersList.getGeneratedNumbers()));
                writeInformation("Completion Time: " + ended / 1000 + " seconds");
            }).start();
            graphicsPanel.repaint();
        });
    }

    private void initDefaultOptions() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setVisible(true);
        setTitle("JSort Application");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/sort.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void toggleWidgets(boolean value) {
        shuffleButton.setEnabled(value);
        customValuesInitButton.setEnabled(value);
        sortSelections.setEnabled(value);
        start.setEnabled(value);
    }

    public static JPanel getGraphicsPanel() {
        return graphicsPanel;
    }

    public static JSlider getSpeedSlider() {
        return speedSlider;
    }

    public static NumbersList getNumbersList() {
        return numbersList;
    }

    public static SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }
}
