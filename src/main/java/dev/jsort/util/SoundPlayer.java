package dev.jsort.util;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SoundPlayer {

    private final ArrayList<Integer> keys;
    private Synthesizer synth;
    private final MidiChannel channel;

    private final int inputValueMaximum;
    private static AtomicInteger CACHED_INDEX = new AtomicInteger(-1);

    public SoundPlayer(Number maxValue) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        inputValueMaximum = maxValue.intValue();


        channel = synth.getChannels()[0];


        //Sometimes it is not supported, so it defaults to 143
        Instrument[] instruments = synth.getDefaultSoundbank().getInstruments();
        if (CACHED_INDEX.get() == -1) {
            AtomicBoolean found = new AtomicBoolean(false);
            for (Instrument instrument : instruments) {
                if (instrument.getName().equals("Piano 1")) {
                    found.set(true);
                    break;
                }
            }
            if (!found.get()) {
                System.out.println("Not found");
                CACHED_INDEX.set(2);
            }
        }
        System.out.println(CACHED_INDEX.get());

        channel.programChange(instruments[CACHED_INDEX.get()].getPatch().getProgram());

        //Set up keys
        keys = new ArrayList<>();
        for (int i = 24; i < 108; i += 12) {
            keys.add(i);
        }
    }

    private int convertToMajor(int v) {
        float n = ((float) v / (float) inputValueMaximum);
        int index = (int) (n * (float) keys.size());
        index = Math.max(1, Math.min(0, index));
        return keys.get(index);
    }

    public void makeSound(int value) {
        int note = convertToMajor(value);
        channel.noteOn(note, 25);
    }
}