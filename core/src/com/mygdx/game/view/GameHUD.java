package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;


public class GameHUD {
    public Stage stage;
    private Viewport viewport;
    private float runnerTimer;
    private int gameScore;
    private int acel;
    private boolean pause;
    private RunnerGame game;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    Label timerLabel;
    Label timerStrLabel;
    Label scoreStrLabel;
    Label scoreLabel;
    Label acelerometerLabel;
    Label pauseLabel;



    public GameHUD(RunnerGame game, SpriteBatch sb) {
        runnerTimer = 0;
        this.gameScore = 0;
        this.acel = 0;
        this.game =game;
        this.pause = false;
        viewport = new FitViewport(GameController.V_WIDTH, GameController.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);


        scoreStrLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timerStrLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        timerLabel = new Label(String.format("%04d", (int) runnerTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        acelerometerLabel = new Label(String.format("%04d", (int) acel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", gameScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pauseLabel = new Label(String.format("Pause"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        addPauseBtn();

        table.add(timerStrLabel).expandX().padTop(10);
        table.add(button).expandX().padTop(10);
        table.add(scoreStrLabel).expandX().padTop(10);
        table.row();
        table.add(timerLabel).expandX();
        table.add();
        table.add(scoreLabel).expandX();
        stage.addActor(table);
    }

    public void update(float delta, float acel, float time) {
        acelerometerLabel.setText(String.format("%f", acel));
        this.scoreLabel.setText(String.format("%04d",(int) acel));
        timerLabel.setText(String.format("%04d",(int) time));
    }

    public Stage getStage() {
        return stage;
    }

    protected void addPauseBtn() {

        myTexture = new Texture(Gdx.files.internal("pauseButton.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("click");
                pause = !pause;
            }
        });

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

    }

    public boolean isPause() {
        return pause;
    }
}
