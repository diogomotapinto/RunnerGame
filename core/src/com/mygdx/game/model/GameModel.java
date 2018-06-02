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

/**
 * Model representation of the game
 */
public class GameModel {

    /**
     * Initial X position of the hero
     */
    private static final int HERO_XPOSITION = 200;

    /**
     * Initial Y position of the hero
     */
    private static final int HERO_YPOSITION = 25;

    /**
     * Initial X position of the enemy
     */
    private static final int ENEMY_XPOSITION = 300;

    /**
     * Initial Y position of the enemy
     */
    private static final int ENEMY_YPOSITION = 25;

    /**
     * Possible Minimum position in the x-axis for the gold to be generated
     */
    private static final int XLOWER_BOUND = 200;

    /**
     * Possible Maximum position in the x-axis for the gold to be generated
     */
    private static final int XUPPER_BOUND = 3000;

    /**
     * Possible Minimum position in the y-axis for the gold to be generated
     */
    private static final int YLOWER_BOUND = 40;

    /**
     * Possible Maximum position in the y-axis for the gold to be generated
     */
    private static final int YUPPER_BOUND = 100;

    /**
     * The main character of the game
     */
    private final HeroModel hero;

    /**
     * Model of the map
     */
    private final MapModel map;


    /**
     * Model of the enemy
     */
    private final EntityModel enemy;

    /**
     * Array Model of the golds
     */
    private final ArrayList<GoldModel> golds;

    /**
     * Array Model of the bullets
     */
    private final ArrayList<BulletModel> bullets;

    /**
     * Object Pool of bullets
     */
    private final Pool<BulletModel> bulletPool = new Pool<BulletModel>() {
        @Override
        protected BulletModel newObject() {
            return new BulletModel(0, 0);
        }
    };

    /**
     * State instance for the hero model
     */
    private HeroState heroState;





    /**
     * Class constructor
     */
    public GameModel() {
        hero = new HeroModel(HERO_XPOSITION, HERO_YPOSITION);
        enemy = new EnemyModel(ENEMY_XPOSITION, ENEMY_YPOSITION);
        map = new MapModel();
        golds = new ArrayList<GoldModel>();
        bullets = new ArrayList<BulletModel>();
        this.heroState = new HeroState();
        generateGolds();
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
     * Generates the golds with random positions
     */
    private void generateGolds() {
        for (int i = 0; i < 100; i++) {
            golds.add(new GoldModel(Utilities.generateRandomNumber(XLOWER_BOUND, XUPPER_BOUND), Utilities.generateRandomNumber(YLOWER_BOUND, YUPPER_BOUND)));
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
     *
     * @param heroState
     */
    public void setHeroState(HeroState.State heroState) {
        this.heroState.setState(heroState);
    }
}
