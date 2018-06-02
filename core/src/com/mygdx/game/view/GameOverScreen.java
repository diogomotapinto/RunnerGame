package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
import com.mygdx.game.model.GameModel;

/**
 * Screen to be presented when the game is over
 */
class GameOverScreen extends Stage implements Screen {

    /**
     * Correction for the position of the achievement image
     */
    private static final int ACHIEVEMENT_CORRECTION = 50;

    /**
     * Correction for the position of the leaderboard image
     */
    private static final int LEADERBOARD_CORRECTION = 100;

    /**
     * Orthographic Camera of the game over screen
     */
    private final OrthographicCamera overCamera;

    /**
     * The game this screen belongs to
     */
    private final RunnerGame game;

    /**
     * Stage of the Game over screen
     */
    private final Stage stage;

    /**
     * Width of the window
     */
    private final int screenWidth;

    /**
     * Height of the window
     */
    private final int screenHeight;

    /**
     * Game Controller instance
     */
    private final GameController gameController;

    /**
     * Game Model instance
     */
    private final GameModel gameModel;

    /**
     * Class constructor
     *
     * @param game     The game passed as parameter
     * @param viewport the viewport previous screen
     */
    public GameOverScreen(RunnerGame game, Viewport viewport, GameModel gameModel, GameController gameController) {
        this.game = game;
        Table table = new Table();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        this.gameController = gameController;
        this.gameModel = gameModel;

        stage = new Stage(new ScreenViewport());
        addRestartBtn();
        addAchievmentBtn();
        addLeaderBtn();
        overCamera = new OrthographicCamera();
        overCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        overCamera.update();
        game.getBatch().setProjectionMatrix(overCamera.combined);

        Gdx.gl.glClearColor(0, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();
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

    }


    /**
     * Adds a button to restart the game when he is clicked
     */
    private void addRestartBtn() {

        ImageButton buttonRestart = createButton("restartButton.png", 0, screenHeight / 2);
        buttonRestart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TmxMapLoader mapLoader = new TmxMapLoader();
                TiledMap map = mapLoader.load("mapa.tmx");
                GameModel gameModel = new GameModel();
                MainMenuView view = new MainMenuView(game, new GameController(map, gameModel, game.getGameServices()), gameModel);
                game.setScreen(view);
                dispose();
            }
        });


        stage.addActor(buttonRestart); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

    }

    /**
     * Adds a button to check the achievements
     */
    private void addAchievmentBtn() {
        ImageButton buttonAch = createButton("achievement.png", screenWidth / 2 - ACHIEVEMENT_CORRECTION, screenHeight / 2);
        buttonAch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameServices().submitScore(gameController.getScore());
                game.getGameServices().showAchievements();

            }
        });

        stage.addActor(buttonAch);
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * Adds a button to check the leaderboard
     */
    private void addLeaderBtn() {
        ImageButton buttonAch = createButton("leaderboard.png", screenWidth - LEADERBOARD_CORRECTION, screenHeight / 2);
        buttonAch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameServices().submitScore(gameController.getScore());
                game.getGameServices().showScores(gameController.getScore());

            }
        });

        stage.addActor(buttonAch);
        Gdx.input.setInputProcessor(stage);
    }


    /**
     * Creates an image button
     *
     * @param path      of the image
     * @param xPosition x position on the screen
     * @param yPosition y position on the screen
     * @return ImageButton
     */
    private ImageButton createButton(String path, int xPosition, int yPosition) {
        Texture myTextureAch = new Texture(Gdx.files.internal(path));
        TextureRegion myTextureRegionAch = new TextureRegion(myTextureAch);
        TextureRegionDrawable myTexRegionDrawableAch = new TextureRegionDrawable(myTextureRegionAch);
        ImageButton imageButton = new ImageButton(myTexRegionDrawableAch);
        imageButton.setPosition(xPosition, yPosition);
        return imageButton;

    }


}
