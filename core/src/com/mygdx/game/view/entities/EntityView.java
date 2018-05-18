package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.HeroModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public abstract class EntityView {

    /**
     * The sprite of the entity
     */
    Sprite sprite;

    /**
     * Class constructor
     *
     * @param game
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

    public abstract Sprite createSprite(RunnerGame game);


    /**
     * Update na view based on a model
     *
     * @param model
     */
    public void update(EntityModel model) {
        sprite.setCenter(model.getX()  , model.getY() );
    }


}
