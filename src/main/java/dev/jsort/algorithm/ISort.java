package dev.jsort.algorithm;

import dev.jsort.window.GraphicsPanel;
import dev.jsort.window.JSortWindow;

public interface ISort {

    String getAlgorithmName();

    void sort(double[] numbers);

    default int getDelay() {
        return JSortWindow.getSpeedSlider().getValue();
    }

    default void setCurrentBarIndex(double currentBarIndex) {
        GraphicsPanel.currentIndex = currentBarIndex;
    }

    default void swap(double[] numbersList, int firstIndex, int secondIndex) {
        double temp = numbersList[firstIndex];
        numbersList[firstIndex] = numbersList[secondIndex];
        numbersList[secondIndex] = temp;
        JSortWindow.getSoundPlayer().makeSound((int) (numbersList[firstIndex] + numbersList[secondIndex])/ 2);
    }

    default void sleepFor(int delay) {
        long timeElapsed;
        long start = System.currentTimeMillis();
        do {
            timeElapsed = System.currentTimeMillis() - start;
        } while (timeElapsed < delay);
        JSortWindow.getGraphicsPanel().repaint();
    }
}
