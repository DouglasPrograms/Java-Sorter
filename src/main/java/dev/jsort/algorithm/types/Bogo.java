package dev.jsort.algorithm.types;

import dev.jsort.algorithm.ISort;
import dev.jsort.window.JSortWindow;

public class Bogo implements ISort {

    @Override
    public String getAlgorithmName() {
        return "Bogo Sort";
    }

    public void sort(double[] nums) {
        while (!rightOrder(nums)) {
            if(rightOrder(nums)){
                break;
            }
        }
    }
    private boolean rightOrder(double[] nums) {
        int size = nums.length;
        for (int i = 0; i < size - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
                setCurrentBarIndex(i + 1);
                sleepFor(getDelay());
                return false;
            }
        }

        setCurrentBarIndex(0);
        return true;
    }
}
