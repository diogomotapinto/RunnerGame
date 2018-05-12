package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.model.entities.MapModel;
import com.mygdx.game.model.entities.TileModel;
import com.mygdx.game.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public class GameModel {
    /**
     * Singleton instance of game model
     */
    private static GameModel instance;

    /**
     * The main character of the game
     */
    private HeroModel hero;

    private MapModel map;

    private static int DISTANCE = 50;

    public MapModel getMap() {
        return map;
    }

    public ArrayList<GoldModel> golds;

    public ArrayList<TileModel> tiles;

    public ArrayList<GoldModel> getGolds() {
        return golds;
    }

    public ArrayList<TileModel> getTiles() {
        return tiles;
    }

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
    public GameModel(){
        hero = new HeroModel(200,25);
        map = new MapModel();
        golds = new ArrayList<GoldModel>();
        tiles = new ArrayList<TileModel>();

        for(int i = 0; i < 100 ; i++){
           // golds.add(new GoldModel(Utilities.generateRandomNumber(200, 34000), Utilities.generateRandomNumber(40,100)));
        }

            tiles.add(new TileModel(200, 20));

            for (int i = 0; i < 100; i++){
                tiles.add(new TileModel(i*DISTANCE, 20));
            }




    }


    /**
     * Returns main character of the game
     * @return main character of the game
     */
    public HeroModel getHero() {
        return hero;
    }

    public void remove(EntityModel model){
        if(model instanceof GoldModel){
            golds.remove(model);
        }
    }

}
