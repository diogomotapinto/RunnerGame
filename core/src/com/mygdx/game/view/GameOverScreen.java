package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameServices;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.GameModel;

class GameOverScreen extends Stage implements Screen {
    GameServices gameServices;
    private final OrthographicCamera overCamera;
    private final RunnerGame game;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle textButtonStyle;
    private final Stage stage;
    private int screenWidth;
    private int screenHeight;

    

    /**
     * Class constructor
     * @param game The game passed as parameter
     * @param viewport the viewport previous screen
     */
    public GameOverScreen(RunnerGame game, Viewport viewport) {
        this.game = game;
        Table table = new Table();
        screenWidth= Gdx.graphics.getWidth();
        screenHeight =Gdx.graphics.getHeight();


        stage = new Stage(new ScreenViewport());
        addRestartBtn();
        addAchievmentBtn();
        addLeaderBtn();
        overCamera = new OrthographicCamera();
        overCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);

    }


    /**
     * Loads the images to be used by the screen
     */
    public void loadMenuAssets() {

        this.game.getAssetManager().load("gameOver.jpg", Texture.class);
        this.game.getAssetManager().load("restartButton.png", Texture.class);
        this.game.getAssetManager().load("achievement.png", Texture.class);
        this.game.getAssetManager().load("leaderboard.png", Texture.class);



        this.game.getAssetManager().finishLoading();

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

        ImageButton buttonRestart = createButton("restartButton.png", 0, screenHeight/2);
        buttonRestart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameServices().submitScore(GameController.getInstance().getScore());
                GameModel.getInstance().newGameModel();
                GameController.getInstance().newGameContoller();
                MainMenuView view = new MainMenuView(game);
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
        ImageButton buttonAch = createButton("achievement.png", screenWidth/2-50, screenHeight/2);
        buttonAch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameServices().submitScore(GameController.getInstance().getScore());
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
        ImageButton buttonAch = createButton("leaderboard.png",screenWidth-100,screenHeight/2);
        buttonAch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getGameServices().submitScore(GameController.getInstance().getScore());
                game.getGameServices().showScores(GameController.getInstance().getScore());

            }
        });

        stage.addActor(buttonAch);
        Gdx.input.setInputProcessor(stage);
    }


    /**
     * Creates an image button
     * @param path of the image
     * @param xPosition x position on the screen
     * @param yPosition y position on the screen
     * @return ImageButton
     */
    private ImageButton createButton(String path, int xPosition, int yPosition) {
        Texture myTextureAch = new Texture(Gdx.files.internal(path));
        TextureRegion myTextureRegionAch = new TextureRegion(myTextureAch);
        TextureRegionDrawable myTexRegionDrawableAch = new TextureRegionDrawable(myTextureRegionAch);
        ImageButton imageButton=  new ImageButton(myTexRegionDrawableAch);
        imageButton.setPosition(xPosition, yPosition);
        return imageButton;

    }


    /**
     * @return the skin
     */
    public Skin getSkin() {
        return skin;
    }
}
