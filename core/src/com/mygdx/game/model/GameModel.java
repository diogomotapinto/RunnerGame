package com.mygdx.game.model;

import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.model.entities.MapModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public class GameModel {
    /**
     * Singleton instance of game model
     */
    private static GameModel instance;

    /**
     * The main character of the game
     */
    private final HeroModel hero;

    private final MapModel map;

    /**
     * Returns a singleton instance of the game model;
     *
     * @return the singleton instance
     */
   // public static GameModel getInstance() {
    //    if (instance == null)
   //         instance = new GameModel();

    //    return instance;
    //}

    /**
     * Class constructor
     */
    public GameModel(){
        hero = new HeroModel(GameController.V_WIDTH / PIXEL_TO_METER,GameController.V_HEIGHT/ PIXEL_TO_METER);
        map = new MapModel();
    }

    /**
     * Returns main character of the game
     * @return main character of the game
     */
    public HeroModel getHero() {
        return hero;
    }

}
