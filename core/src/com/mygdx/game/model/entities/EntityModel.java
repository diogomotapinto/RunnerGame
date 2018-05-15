package com.mygdx.game.model.entities;

import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public abstract class EntityModel {
    public enum ModelType {ENEMY,GOLD,HERO, BULLET};

    /**
     * The x-coordinate of the model in meters
     */
    private float x;

    /**
     * The y-coordinate of the model in meters
     */
    private float y;

    /**
     * Positions
     */
    private Vector2 position;

    /**
     * as the model been flagged for removal
     */
    private boolean flaggedForRemoval = false;

    EntityModel(float x, float y) {
        this.position = new Vector2(x, y);
    }

    EntityModel(){

    }


    /**
     * Gets position in the x-axis
     *
     * @return position in the x-axis
     */
    public float getX() {
        return position.x;
    }

    /**
     * Gets position in the y-axis
     *
     * @return position in the y-axis
     */
    public float getY() {
        return position.y;
    }

    /**
     * Sets position in the x-axis
     */
    public void setX(float x) {
        this.position.x = x;
    }

    /**
     * Sets position in the y-axis
     */
    public void setY(float y) {
        this.position.y = y;
    }

    /**
     * Sets the position of the character in the map
     *
     * @param x position in the x-axis
     * @param y position in the -axis
     */
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }


    /**
     * Sets the position of the character in the map
     *
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public Vector2 getPosition() {
        return position;
    }

    /**
     * Checks is the the model is flagged for removal
     *
     * @return true if it is and false otherwise
     */
    public boolean isFlaggedForRemoval() {
        return flaggedForRemoval;
    }

    /**
     * Sets if the model is flagged for removal
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }

    public abstract ModelType getType();


}
