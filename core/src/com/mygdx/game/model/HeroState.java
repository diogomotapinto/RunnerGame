package com.mygdx.game.model;

public class HeroState {

    private State currentState;

    public HeroState() {
        this.currentState = State.PAUSED;
    }

    public State getState() {
        return currentState;
    }

    public void setState(State currentState) {
        this.currentState = currentState;
    }

    public enum State {RUNNING, PAUSED, JUMPING, DEAD}

}
