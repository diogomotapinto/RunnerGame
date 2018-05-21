package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;

import javax.swing.text.View;

public class GamePausedScreen extends Stage implements Screen {
    private OrthographicCamera pauseCamera;
    private RunnerGame game;
    private Viewport viewport;
    private Table table;
    private Stage stage;
    private Texture myTextureResume;
    private TextureRegion myTextureRegionResume;
    private TextureRegionDrawable myTexRegionDrawableResume;
    private Texture myTextureMenu;
    private TextureRegion myTextureRegionMenu;
    private TextureRegionDrawable myTexRegionDrawableMenu;
    private ImageButton buttonPlay;
    private ImageButton buttonMenu;


    public GamePausedScreen(RunnerGame game, Viewport viewport) {
        this.game = game;
        this.viewport = viewport;
        this.table = new Table();
        this.stage = new Stage(viewport, this.game.getBatch());

        pauseCamera = new OrthographicCamera();
        pauseCamera.setToOrtho(false, GameController.V_WIDTH,GameController.V_HEIGHT);

        stage = new Stage(new ScreenViewport());
        addResumeBtn();

        addMainMenuBtn();

    }

    public void loadMenuAssets(){
        this.game.getAssetManager().load("playButton.png", Texture.class);
        this.game.getAssetManager().load("gameOver.jpg", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        pauseCamera.update();
        game.getBatch().setProjectionMatrix(pauseCamera.combined);

        Gdx.gl.glClearColor(0, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();

    }

    protected void addResumeBtn() {

        myTextureResume = new Texture(Gdx.files.internal("playButton.png"));
        myTextureRegionResume = new TextureRegion(myTextureResume);
        myTexRegionDrawableResume = new TextureRegionDrawable(myTextureRegionResume);
        buttonPlay = new ImageButton(myTexRegionDrawableResume); //Set the button up
        buttonPlay.setPosition(204,200);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameView view = new GameView(game);
                view.setPause(!view.isPause());
                game.setScreen(view);
                dispose();
            }
        });

       // stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(buttonPlay); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

    }


    protected void addMainMenuBtn() {

        myTextureMenu = new Texture(Gdx.files.internal("gameOver.jpg"));
        myTextureRegionMenu = new TextureRegion(myTextureMenu);
        myTexRegionDrawableMenu = new TextureRegionDrawable(myTextureRegionMenu);
        buttonMenu = new ImageButton(myTexRegionDrawableMenu); //Set the button up
        buttonMenu.setPosition(408,100);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuView view = new MainMenuView(game);
                game.setScreen(view);
                dispose();
            }
        });

        //stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(buttonMenu); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
