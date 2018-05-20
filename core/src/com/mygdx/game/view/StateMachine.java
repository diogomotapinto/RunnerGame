package com.mygdx.game.view;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameServices;
import com.mygdx.game.view.GamePausedScreen;
import com.mygdx.game.view.GameWonScreen;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.view.GameOverScreen;

public class StateMachine {

    private State gameState;
    private static StateMachine instance;

    private StateMachine() {
        this.gameState = State.MAIN_MENU;
    }

    public static StateMachine getInstance() {
        if (instance == null)
            return new StateMachine();

        return instance;
    }

    enum State {MAIN_MENU, LEADER_BOARD_MENU, NETWORK_MENU, OPTIONS_MENU, SKIN_MENU, GAME, GAME_PAUSE, GAME_LOST, GAME_WON, EXIT}

    ;

    public enum Event {
        OPEN_OPTIONS_MENU, OPEN_GAME, OPEN_NETWORKING_MENU, OPEN_EXIT,
        OPEN_MAIN_MENU, OPEN_SKIN_MENU, OPEN_LEADERBOARD_MENU, OPEN_PAUSE_MENU,
        OPEN_LOST_MENU, OPEN_WON_MENU, OPEN_NEW_GAME
    }

    public void advanceState(Event evt) {
        switch (this.gameState) {
            case MAIN_MENU:
                if (evt == Event.OPEN_GAME) {
                    this.gameState = State.GAME;
                } else if (evt == Event.OPEN_OPTIONS_MENU) {
                    this.gameState = State.OPTIONS_MENU;
                } else if (evt == Event.OPEN_LEADERBOARD_MENU) {
                    this.gameState = State.LEADER_BOARD_MENU;
                } else if (evt == Event.OPEN_NETWORKING_MENU) {
                    this.gameState = State.NETWORK_MENU;
                } else if (evt == Event.OPEN_EXIT) {
                    this.gameState = State.EXIT;
                }
                break;
            case OPTIONS_MENU:
                if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                } else if (evt == Event.OPEN_SKIN_MENU) {
                    this.gameState = State.SKIN_MENU;
                }
                break;
            case SKIN_MENU:
                if (evt == Event.OPEN_OPTIONS_MENU) {
                    this.gameState = State.OPTIONS_MENU;
                }
                break;
            case LEADER_BOARD_MENU:
                if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                }
                break;
            case NETWORK_MENU:
                if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                }
                break;
            case GAME:
                if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                } else if (evt == Event.OPEN_PAUSE_MENU) {
                    this.gameState = State.GAME_PAUSE;
                } else if (evt == Event.OPEN_LOST_MENU) {
                    this.gameState = State.GAME_LOST;
                } else if (evt == Event.OPEN_WON_MENU) {
                    this.gameState = State.GAME_WON;
                }
                break;
            case GAME_WON:
                if (evt == Event.OPEN_NEW_GAME) {
                    this.gameState = State.GAME;
                } else if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                }
                break;
            case GAME_LOST:
                if (evt == Event.OPEN_NEW_GAME) {
                    this.gameState = State.GAME;
                } else if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                }
                break;
            case GAME_PAUSE:
                if (evt == Event.OPEN_NEW_GAME) {
                    this.gameState = State.GAME;
                } else if (evt == Event.OPEN_GAME) {
                    this.gameState = State.GAME;
                } else if (evt == Event.OPEN_MAIN_MENU) {
                    this.gameState = State.MAIN_MENU;
                }

                break;
            default:
                break;
        }


    }
}
