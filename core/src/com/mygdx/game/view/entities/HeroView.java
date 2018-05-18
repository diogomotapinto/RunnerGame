package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.RunnerGame;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class HeroView extends EntityView {
    public Texture texture;
    public HeroView(RunnerGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {
        texture = game.getAssetManager().get("sonic.png");
        sprite = new Sprite(texture,  texture.getWidth(), texture.getHeight());
        sprite.setBounds(0,0, texture.getWidth()/PIXEL_TO_METER, texture.getWidth()/PIXEL_TO_METER);
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }
}
