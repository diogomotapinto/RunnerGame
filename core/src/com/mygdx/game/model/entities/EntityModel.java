package com.mygdx.game.model.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Abstract model representation of an entity model
 */
public abstract class EntityModel {

    /**
     * Positions
     */
    private Vector2 position;

    /**
     * as the model been flagged for removal
     */
    private boolean flaggedForRemoval = false;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    EntityModel(float x, float y) {
        this.position = new Vector2(x, y);
    }

    /**
     * Class Empty Constructor
     */
    EntityModel() {

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
     * Gets current position
     *
     * @return position of the entity
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position of the character in the map
     *
     * @param position position of the entity
     */
    public void setPosition(Vector2 position) {
        this.position.set(position);
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

    /**
     * Types of existing models
     */
    public enum ModelType {
        ENEMY, GOLD, HERO, BULLET
    }


}
