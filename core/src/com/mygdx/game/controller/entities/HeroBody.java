package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.HeroModel;

/**
 * A concrete representation of an HeroBody representing the hero
 */
public class HeroBody extends EntityBody {

    /**
     * Constructs an hero acording to the hero model.
     *
     * @param world     the physical world this enemy belongs to.
     * @param model     the model representing this enemy
     * @param isDynamic boolean to know if the enemy is static or not
     */
    public HeroBody(World world, HeroModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, 1.27f, 6);
    }

}
