package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.RunnerGame;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class GoldView extends EntityView {


    public GoldView(RunnerGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {

        Texture texture = game.getAssetManager().get("gold.png");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        sprite.setBounds(0, 0, texture.getWidth() / PIXEL_TO_METER, texture.getWidth() / PIXEL_TO_METER);
        return sprite;
    }
}
