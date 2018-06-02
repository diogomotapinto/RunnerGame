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
 * A view representing the enemy
 */
public class EnemyView extends EntityView {

    /**
     * Duration of the frame
     */
    private static final float FRAME_DURATION = 0.08f;

    /**
     * Number of images
     */
    private static final int IMAGES = 8;

    /**
     * Correction of the image representation
     */
    private static final int BOUNDS_CORRECTION = 8;

    /**
     * Correction of the scale representation
     */
    private static final int SCALE_CORRECTION =  25;
    /**
     * texture of the enemy
     */
    private Texture texture;

    /**
     * Time since the game started
     */
    private float stateTime = 0;

    /**
     * The animation used when the ship is accelerating
     */
    private Animation<TextureRegion> rollingAnimation;

    /**
     * model of the enemy
     */
    private EntityModel model;

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EnemyView(RunnerGame game) {
        super(game);
    }

    /**
     * Creates a sprite representing this enemy.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this enemy
     */
    @Override
    public Sprite createSprite(RunnerGame game) {
        texture = game.getAssetManager().get("enemy.png");
        rollingAnimation = createRollingAnimation(game);
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        return sprite;
    }

    /**
     * Creates the animation used when the enemy is accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the enemy is moving
     */
    private Animation<TextureRegion> createRollingAnimation(RunnerGame game) {
        Texture runTexture = game.getAssetManager().get("enemy.png");
        TextureRegion[][] runRegion = TextureRegion.split(runTexture, runTexture.getWidth() / IMAGES, runTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[IMAGES];
        System.arraycopy(runRegion[0], 0, frames, 0, IMAGES);

        return new Animation<TextureRegion>(FRAME_DURATION, frames);
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

        sprite.setRegion(rollingAnimation.getKeyFrame(stateTime, true));
        sprite.setBounds(model.getPosition().x - BOUNDS_CORRECTION / PIXEL_TO_METER, model.getPosition().y - BOUNDS_CORRECTION / PIXEL_TO_METER, texture.getWidth() / (PIXEL_TO_METER * SCALE_CORRECTION), texture.getWidth() / (PIXEL_TO_METER * SCALE_CORRECTION));

        sprite.draw(batch);
    }

    /**
     * Updates this enemy model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        this.model = model;
    }
}
