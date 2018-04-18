package com.mygdx.game.View;

import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private Integer worldTimer;
    private float timerCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label runnerLabel;



    private GameHUD(SpriteBatch sb){
        worldTimer = 300;
        timerCount = 0;
        score = 0;

        viewport = new FitViewport(RunnerGame.V_WIDTH,RunnerGame.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
    }



}
