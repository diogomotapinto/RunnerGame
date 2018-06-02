package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

/**
 * A concrete representation of an EnemyBody representing the enemy
 */
public class EnemyBody extends EntityBody {

    /**
     * Density of the enemy body
     */
    private static final float ENEMY_DENSITY = 1.27f;

    /**
     * Size of the enemy body
     */
    private static final float ENEMY_SIZE = 6;

    /**
     * Constructs an enemy acording to the enemy model.
     *
     * @param world     the physical world this enemy belongs to.
     * @param model     the model representing this enemy
     * @param isDynamic boolean to know if the enemy is static or not
     */
    public EnemyBody(World world, EntityModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, ENEMY_DENSITY, ENEMY_SIZE);
    }

}
