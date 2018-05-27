package com.mygdx.game.utils;

import java.util.Random;

public final class Utilities {

    /**
     * Generates Random Number
     *
     * @param lowerBound minimum of possible generated number
     * @param upperBound maximum of possible generated number
     * @return generated random number
     */
    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

}
