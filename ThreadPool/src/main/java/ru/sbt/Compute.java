package ru.sbt;

public class Compute {

    public Double doWork(double a) {
        for (int i = 0; i < 100000; i++) {
            a = a + Math.tan(a);
        }

        return a;
    }
}
