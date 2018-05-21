package com.mygdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class EnemyView extends EntityView{
    public Texture texture;
    private float stateTime = 0;
    private Animation<TextureRegion> rollingAnimation;
    private EntityModel model;


    public EnemyView(RunnerGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(RunnerGame game) {
        texture = game.getAssetManager().get("enemy.png");
        rollingAnimation = createRollingAnimation(game);
        sprite = new Sprite(texture,  texture.getWidth(), texture.getHeight());
        //sprite.setBounds(0,0, texture.getWidth(), texture.getWidth()/PIXEL_TO_METER);
        return sprite;
    }

    private Animation<TextureRegion> createRollingAnimation(RunnerGame game){
        Texture runTexture = game.getAssetManager().get("enemy.png");
        TextureRegion [][] runRegion = TextureRegion.split(runTexture, runTexture.getWidth() / 8, runTexture.getHeight());

        TextureRegion [] frames = new TextureRegion[8];
        System.arraycopy(runRegion[0], 0, frames,0,8);

        return new Animation<TextureRegion>(0.08f,frames);
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        sprite.setRegion(rollingAnimation.getKeyFrame(stateTime,true));
        sprite.setBounds(model.getX()-8/PIXEL_TO_METER,model.getY()-8/PIXEL_TO_METER, texture.getWidth()/(PIXEL_TO_METER*25), texture.getWidth()/(PIXEL_TO_METER*25));

        sprite.draw(batch);
    }

    @Override
    public void update(EntityModel model) {
        //super.update(model);
        this.model = model;
    }
}
