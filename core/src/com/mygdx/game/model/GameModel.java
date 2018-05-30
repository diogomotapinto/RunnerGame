package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EnemyModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.model.entities.MapModel;
import com.mygdx.game.utils.Utilities;

import java.util.ArrayList;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public class GameModel {
    /**
     * Singleton instance of game model
     */
    private static GameModel instance;
    private HeroState heroState;

    /**
     * The main character of the game
     */
    private final HeroModel hero;

    private final MapModel map;

    private final EntityModel enemy;
    private final ArrayList<GoldModel> golds;
    private final ArrayList<BulletModel> bullets;
    private final Pool<BulletModel> bulletPool = new Pool<BulletModel>() {
        @Override
        protected BulletModel newObject() {
            return new BulletModel(0, 0);
        }
    };

    /**
     * Class constructor
     */
    private GameModel() {
        hero = new HeroModel(200, 25);
        enemy = new EnemyModel(300, 25);
        map = new MapModel();
        golds = new ArrayList<GoldModel>();
        bullets = new ArrayList<BulletModel>();
        this.heroState = new HeroState();
        generateGolds();
        //golds.add(new GoldModel(200,50));


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
     * @return the mapModel
     */
    public MapModel getMap() {
        return map;
    }

    /**
     * @return the goldModel array
     */
    public ArrayList<GoldModel> getGolds() {
        return golds;
    }


    /**
     * @return a new instance of the Game Model
     */
    public static void newGameModel() {
        GameModel.instance = new GameModel();
    }


    /**
     * Generates the golds with random positions
     */
    private void generateGolds() {
        for (int i = 0; i < 100; i++) {
            golds.add(new GoldModel(Utilities.generateRandomNumber(200, 3000), Utilities.generateRandomNumber(40, 100)));
        }
    }

    /**
     * Returns main character of the game
     *
     * @return main character of the game
     */
    public HeroModel getHero() {
        return hero;
    }

    /**
     * @return enemy character of the game
     */
    public EntityModel getEnemy() {
        return enemy;
    }

    /**
     * @return the bulletModel array
     */
    public ArrayList<BulletModel> getBullets() {
        return bullets;
    }

    public BulletModel createBullet(Vector2 vec, int x) {
        BulletModel bullet = bulletPool.obtain();

        bullet.setFlaggedForRemoval(false);
        bullet.setPosition(PIXEL_TO_METER * vec.x + x, PIXEL_TO_METER * vec.y);
        bullets.add(bullet);
        return bullet;
    }


    /**
     * @param model
     */
    public void remove(EntityModel model) {
        if (model instanceof BulletModel) {
            bullets.remove(model);
            bulletPool.free((BulletModel) model);
        }

        if (model instanceof GoldModel) {
            golds.remove(model);
        }
    }


    /**
     * @return the hero state
     */
    public HeroState getHeroState() {
        return heroState;
    }


    /**
     * Sets the hero state
     * @param heroState
     */
    public void setHeroState(HeroState.State heroState) {
        this.heroState.setState(heroState);
    }
}
