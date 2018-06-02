package com.mygdx.game.controller.entities;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;


/**
 * A concrete representation of an BulletBody representing the bullet
 */
public class BulletBody extends EntityBody {

    /**
     * Density of the bullet body
     */
    private static final float BULLET_DENSITY = 7.874f;

    /**
     * Size of the bullet body
     */
    private static final float BULLET_SIZE = 2.5f;

    /**
     * Class constructor
     *
     * @param world     the physical world this bullet belongs to
     * @param model     the model representing this bullet
     * @param isDynamic boolean to know if the body is dynamic or static
     */
    public BulletBody(World world, EntityModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, BULLET_DENSITY, BULLET_SIZE);
    }

}
