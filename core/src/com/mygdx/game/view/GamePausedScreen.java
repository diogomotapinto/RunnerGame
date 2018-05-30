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

class GamePausedScreen extends Stage implements Screen {
    private final OrthographicCamera pauseCamera;
    private final RunnerGame game;
    private Stage stage;
    private int screenWidth;
    private int screenHeight;

    /**
     * Class constructor
     * @param game The game passed as parameter
     * @param viewport the viewport previous screen
     */
    public GamePausedScreen(RunnerGame game, Viewport viewport) {
        this.game = game;
        Table table = new Table();
        this.stage = new Stage(viewport, this.game.getBatch());
        screenWidth= Gdx.graphics.getWidth();
        screenHeight =Gdx.graphics.getHeight();



        pauseCamera = new OrthographicCamera();
        pauseCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);

        stage = new Stage(new ScreenViewport());
        addResumeBtn();
    }

    /**
     * Loads the images to be used by the screen
     */
    public void loadMenuAssets() {
        this.game.getAssetManager().load("restartButton.png", Texture.class);
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


    /**
     * Adds a button to resume the game and change it's state
     */
    private void addResumeBtn() {

        Texture myTextureResume = new Texture(Gdx.files.internal("restartButton.png"));
        TextureRegion myTextureRegionResume = new TextureRegion(myTextureResume);
        TextureRegionDrawable myTexRegionDrawableResume = new TextureRegionDrawable(myTextureRegionResume);
        ImageButton buttonPlay = new ImageButton(myTexRegionDrawableResume); //Set the button up
        buttonPlay.setPosition(screenWidth/2-40, screenHeight/2-40);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameView view = new GameView(game);
                view.setPause(!view.isPause());
                game.setScreen(view);
                dispose();
            }
        });


        stage.addActor(buttonPlay);
        Gdx.input.setInputProcessor(stage);

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
