package com.mygdx.game.controller;

public class GameController {
    private static GameController instance;
    private float xPosition;

    private GameController() {
        xPosition = 0;
    }


    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public float getCameraPosition() {
        return xPosition;
    }

    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;
    }
}

