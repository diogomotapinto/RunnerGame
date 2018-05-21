package com.mygdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

import sun.util.resources.cldr.en.TimeZoneNames_en_ZA;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class HeroView extends EntityView {
    public Texture texture;
    private float stateTime = 0;
    private Animation<TextureRegion> runningAnimation;
    private EntityModel model;


    public HeroView(RunnerGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {
        texture = game.getAssetManager().get("hello.png");
        runningAnimation = createRunningAnimation(game);
        sprite = new Sprite(texture,  texture.getWidth(), texture.getHeight());
        //sprite.setBounds(0,0, texture.getWidth()/(PIXEL_TO_METER*3), texture.getWidth()/(PIXEL_TO_METER*20));
        return sprite;
    }

    private Animation<TextureRegion> createRunningAnimation(RunnerGame game){
        Texture runTexture = game.getAssetManager().get("hello.png");
        TextureRegion [][] runRegion = TextureRegion.split(runTexture, runTexture.getWidth() / 9, runTexture.getHeight());

        TextureRegion [] frames = new TextureRegion[9];
        System.arraycopy(runRegion[0], 0, frames,0,9);

        return new Animation<TextureRegion>(0.08f,frames);
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        sprite.setRegion(runningAnimation.getKeyFrame(stateTime,true));
        sprite.setBounds(model.getX()-12/PIXEL_TO_METER,model.getY()-8/PIXEL_TO_METER, texture.getWidth()/(PIXEL_TO_METER*15), texture.getWidth()/(PIXEL_TO_METER*20));
        sprite.draw(batch);
    }

    @Override
    public void update(EntityModel model) {
        //super.update(model);
        this.model = model;
    }
}
