package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.RunnerGame;

public class BulletView extends EntityView{

    public BulletView(RunnerGame game){
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {
        Texture texture = game.getAssetManager().get("newcoin.png");

        return new Sprite(texture, texture.getHeight(), texture.getWidth());
    }
}
