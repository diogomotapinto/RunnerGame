package com.mygdx.game.model;

import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.entities.HeroModel;

public class GameModel {
    /**
     * Singleton instance of game model
     */
    private static GameModel instance;

    /**
     * The main character of the game
     */
    private HeroModel hero;

    /**
     * Returns a singleton instance of the game model;
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();

        return instance;
    }

    /**
     * Class constructor
     */
    private GameModel() {
        hero = new HeroModel(GameController.V_WIDTH, GameController.V_HEIGHT);
    }

    /**
     * Returns main character of the game
     *
     * @return main character of the game
     */
    public HeroModel getHero() {
        return hero;
    }

}
