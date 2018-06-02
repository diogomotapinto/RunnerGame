package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

/**
 * A abstract view capable of holding a sprite with a certain position
 * <p>
 * This view is able to update its data based on a entity model.
 */
public abstract class EntityView {

    /**
     * The sprite of the entity
     */
    protected Sprite sprite;

    /**
     * Class constructor
     *
     * @param game game this entity belongs to
     */
    EntityView(RunnerGame game) {
        sprite = createSprite(game);
    }

    /**
     * Draws the sprite from the view
     *
     * @param batch the sprite batch to be draw
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    protected abstract Sprite createSprite(RunnerGame game);


    /**
     * Update na view based on a model
     *
     * @param model entity to be updated
     */
    public void update(EntityModel model) {
        sprite.setCenter(model.getPosition().x, model.getPosition().y);
    }


}
