package org.nerdcore.spellbookmanager.databaseInit;

public class Stopwatch {
    private double start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double getElapsedTime() {
        return (System.currentTimeMillis() - start);
    }
}