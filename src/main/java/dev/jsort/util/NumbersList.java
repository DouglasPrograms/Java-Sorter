package dev.jsort.util;

import java.text.DecimalFormat;
import java.util.Random;


public class NumbersList {

    private double[] generatedNumbers;

    private static final DecimalFormat df = new DecimalFormat("#.####");

    public static NumbersList create(int size) {
        return new NumbersList(size);
    }

    public NumbersList(int size) {
        generatedNumbers = new double[size];
    }

    private static final Random random = new Random();

    public void generateRandomList() {
        for (int i = 0; i < generatedNumbers.length; i++) {
            double max = 720D;
            double min = 1D;
            generatedNumbers[i] = Double.parseDouble(df.format(random.nextDouble(max - min) + min));
        }
    }

    public void initCustomValues(String text) {
        String[] modifiedText = getText(text);
        for (int i = 0; i < modifiedText.length; i++) {
            generatedNumbers[i] = Double.parseDouble(modifiedText[i]);
        }
    }


    public String[] getText(String text) {
        return text.split(",");
    }


    public double[] getGeneratedNumbers() {
        return generatedNumbers;
    }
}
