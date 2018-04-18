package com.mygdx.game.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class GameHUD {
    public Stage stage;
    private Viewport viewport;
    private float runnerTimer;
    private int gameScore;
    Label timerLabel;
    Label timerStrLabel;
    Label scoreStrLabel;
    Label scoreLabel;


    public GameHUD(SpriteBatch sb){
        runnerTimer = 0;
        this.gameScore = 0;

        viewport = new FitViewport(RunnerGame.V_WIDTH,RunnerGame.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        timerLabel = new Label(String.format("%04d",(int) runnerTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", gameScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreStrLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timerStrLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(timerStrLabel).expandX().padTop(10);
        table.add(scoreStrLabel).expandX().padTop(10);
        table.row();
        table.add(timerLabel).expandX();
        table.add(scoreLabel).expandX();
        stage.addActor(table);


    }


    public void update(float delta, int gameScore){
        runnerTimer += delta;

        if(runnerTimer >= 1){
            timerLabel.setText(String.format("%03d",(int) runnerTimer));
        }
    }



}
