package com.mygdx.game.model;

/**
 * Class representation of the hero states
 */
public class HeroState {

    /**
     * Current state of the hero
     */
    private State currentState;

    /**
     * Class Constructor
     */
    public HeroState() {
        this.currentState = State.PAUSED;
    }

    /**
     * Returns the current state of the hero
     *
     * @return Current state of the hero
     */
    public State getState() {
        return currentState;
    }

    /**
     * Sets the value of variable currentState
     *
     * @param currentState currentaState to be set
     */
    public void setState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Possible states of the hero
     */
    public enum State {
        RUNNING, PAUSED, JUMPING, DEAD
    }

}
