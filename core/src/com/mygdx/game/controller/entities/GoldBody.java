package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.GoldModel;

/**
 * A concrete representation of an GoldBody representing the gold
 */
public class GoldBody extends EntityBody {

    /**
     * Density of the gold body
     */
    private static final float GOLD_DENSITY = 0f;

    /**
     * Size of the gold body
     */
    private static final float GOLD_SIZE = 6;

    /**
     * Class constructor
     *
     * @param world     the physical world this bullet belongs to
     * @param model     the model representing this gold
     * @param isDynamic boolean to know if the body is dynamic or static
     */
    public GoldBody(World world, GoldModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, GOLD_DENSITY, GOLD_SIZE);
    }

}
