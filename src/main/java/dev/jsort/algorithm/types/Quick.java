package dev.jsort.algorithm.types;

import dev.jsort.window.JSortWindow;
import dev.jsort.algorithm.ISort;

public class Quick implements ISort {

    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }

    @Override
    public void sort(double[] numbers) {
        initSort(numbers, 0, numbers.length - 1);
    }

    private int partition(double[] numbers, int low, int high) {
        double pivot = numbers[high];
        int index = (low - 1);
        for (int j = low; j < high; j++) {
            if (numbers[j] < pivot) {
                index++;
                swap(numbers, index, j);
                setCurrentBarIndex(j);
                sleepFor(getDelay());
            }
        }

        swap(numbers, index+1,high);
        return index + 1;
    }

    private void initSort(double[] numbers, int low, int high) {
        if (low < high) {
            int p = partition(numbers, low, high);
            initSort(numbers, low, p - 1);
            initSort(numbers, p + 1, high);
        }
    }
}
