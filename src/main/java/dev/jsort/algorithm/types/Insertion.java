package dev.jsort.algorithm.types;

import dev.jsort.algorithm.ISort;
import dev.jsort.window.JSortWindow;

public class Insertion implements ISort {

    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }

    @Override
    public void sort(double[] numbers) {
        int size = numbers.length;
        for(int i = 1; i < size; i++){
            double key = numbers[i];
            int j = i - 1;
            while(j >= 0 && numbers[j] > key){
                numbers[j+1] = numbers[j];
                j--;
            }

            numbers[j+1] = key;
            setCurrentBarIndex(j);
            JSortWindow.getSoundPlayer().makeSound(j);
            sleepFor(getDelay());
        }
    }
}
