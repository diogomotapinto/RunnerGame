package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.view.entities.EntityView;


public class HeroView extends EntityView{

    public HeroView(RunnerGame game){
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {
        return null;
    }
}
