package com.mygdx.game.controller;

import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.HeroState;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest extends GameTest {


    GameController gameController;

    @Test
    public void getScore() {
        this.gameController = GameController.getInstance();
        assertEquals(gameController.getScore(), 0);

        this.gameController.run();
        while (true) {
            if (gameController.getHeroBody().getY() < 0) {
                break;
            }
        }
        assertEquals(gameController.getScore(), 0);
        assertEquals(GameModel.getInstance().getHeroState().getState(), HeroState.State.DEAD);
    }

    @Test
    public void jump() {
        //     this.gameController = new GameController();

    }

    @Test
    public void run() {
//        this.gameController = new GameController();

    }

    @Test
    public void shoot() {
        //    this.gameController = new GameController();

    }

    @Test
    public void removeBody() {
        //    this.gameController = new GameController();

    }
}