package com.mygdx.game.utils;

import java.util.Random;

public final class Utilities {

    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

}
