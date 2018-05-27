package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.RunnerGame;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

/**
 * A view representing a bullet
 */
public class BulletView extends EntityView {

    /**
     * Constructs a bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BulletView(RunnerGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this hero.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this hero
     */
    @Override
    public Sprite createSprite(RunnerGame game) {
        Texture texture = game.getAssetManager().get("bullet.png");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        sprite.setBounds(0, 0, texture.getWidth() / PIXEL_TO_METER, texture.getWidth() / PIXEL_TO_METER);
        return sprite;
    }
}
