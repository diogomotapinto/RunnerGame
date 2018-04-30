package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;

public class GoldBody extends  EntityBody{


    public GoldBody(World world, GoldModel model, boolean isDynamic) {
        super(world, model, isDynamic);




    }
}
