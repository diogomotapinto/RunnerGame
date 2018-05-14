package com.mygdx.game.view.entities;

import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

public class ViewFactory {
    private static Map<EntityModel, EntityView> cache = new HashMap<EntityModel, EntityView>();

    public static EntityView makeView(RunnerGame game, EntityModel){
        
    }
}
