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

/**
 * HUD of the main screen of the game
 */
class GameHUD {
    /**
     * Pad top for some element in the HUD
     */
    private static int PAD_TOP = 10;

    /**
     * Times label
     */
    private final Label timerLabel;

    /**
     * Score label
     */
    private final Label scoreLabel;

    /**
     * Stage of the game
     */
    public Stage stage;

    /**
     * State of the game
     */
    private boolean pause;

    /**
     * Image button to be used by the pause Button
     */
    private ImageButton button;

    /**
     * Class constructor
     * @param sb
     */
    public GameHUD(SpriteBatch sb) {

        this.pause = false;
        Viewport viewport = new FitViewport(GameController.V_WIDTH, GameController.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);


        Label scoreStrLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label timerStrLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        timerLabel = new Label(Integer.toString(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(Integer.toString(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        addPauseBtn();

        table.add(timerStrLabel).expandX().padTop(PAD_TOP);
        table.add(button).expandX().padTop(PAD_TOP);
        table.add(scoreStrLabel).expandX().padTop(PAD_TOP);
        table.row();
        table.add(timerLabel).expandX();
        table.add();
        table.add(scoreLabel).expandX();
        stage.addActor(table);
    }

    /*
    * Updates the game HUD
    *
    */
    public void update(float acel, float time) {
        this.scoreLabel.setText(Integer.toString((int) acel));
        timerLabel.setText(Integer.toString((int) time));
    }


    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /*
    * Adds a pause button to pause the game and change the screen
    *
    */
    private void addPauseBtn() {

        Texture myTexture = new Texture(Gdx.files.internal("pauseButton.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause = !pause;
            }
        });

        stage = new Stage(new ScreenViewport());
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

    }


    /**
     * @return the state of the game
     */
    public boolean isPause() {
        return pause;
    }
}
