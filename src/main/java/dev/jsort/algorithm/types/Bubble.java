package dev.jsort.algorithm.types;

import dev.jsort.window.JSortWindow;
import dev.jsort.algorithm.ISort;

public class Bubble implements ISort {

    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }

    @Override
    public void sort(double[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j+1);
                    setCurrentBarIndex(j + 1);
                    sleepFor(getDelay());
                }

        setCurrentBarIndex(0);
    }
}