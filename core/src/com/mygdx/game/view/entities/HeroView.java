package com.mygdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

/**
 * A view representing the hero
 */
public class HeroView extends EntityView {

    /**
     * texture of the enemy
     */
    private Texture texture;

    /**
     * state of the time
     */
    private float stateTime = 0;

    /**
     * The animation used when the ship is accelerating
     */
    private Animation<TextureRegion> runningAnimation;

    /**
     * model of the enemy
     */
    private EntityModel model;


    /**
     * Constructs a hero view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public HeroView(RunnerGame game) {
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
        texture = game.getAssetManager().get("hello.png");
        runningAnimation = createRunningAnimation(game);
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        //sprite.setBounds(0,0, texture.getWidth()/(PIXEL_TO_METER*3), texture.getWidth()/(PIXEL_TO_METER*20));
        return sprite;
    }

    /**
     * Creates the animation used when the hero is accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the hero is moving
     */
    private Animation<TextureRegion> createRunningAnimation(RunnerGame game) {
        Texture runTexture = game.getAssetManager().get("hello.png");
        TextureRegion[][] runRegion = TextureRegion.split(runTexture, runTexture.getWidth() / 9, runTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[9];
        System.arraycopy(runRegion[0], 0, frames, 0, 9);

        return new Animation<TextureRegion>(0.08f, frames);
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the stateTime
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        sprite.setRegion(runningAnimation.getKeyFrame(stateTime, true));
        sprite.setBounds(model.getPosition().x - 12 / PIXEL_TO_METER, model.getPosition().y - 8 / PIXEL_TO_METER, texture.getWidth() / (PIXEL_TO_METER * 15), texture.getWidth() / (PIXEL_TO_METER * 20));
        sprite.draw(batch);
    }

    /**
     * Updates this hero model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        //super.update(model);
        this.model = model;
    }
}
