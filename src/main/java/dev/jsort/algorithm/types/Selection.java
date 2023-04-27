package dev.jsort.algorithm.types;

import dev.jsort.window.JSortWindow;
import dev.jsort.algorithm.ISort;

public class Selection implements ISort {

    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }

    @Override
    public void sort(double[] numbers) {
        int n = numbers.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                    setCurrentBarIndex(i);
                    setCurrentBarIndex(j);
                    sleepFor(getDelay());
                }

            swap(numbers, minIndex, i);
        }
    }
}
