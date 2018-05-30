package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.BulletBody;
import com.mygdx.game.controller.entities.EnemyBody;
import com.mygdx.game.controller.entities.GoldBody;
import com.mygdx.game.controller.entities.HeroBody;
import com.mygdx.game.controller.entities.MapBody;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.HeroState;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EnemyModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;

import java.util.ArrayList;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class GameController implements ContactListener {
    // private MapBody mapBody;
    //private EnemyBody enemyBody;
    //private ArrayList<GoldBody> goldBodyArray;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    private static GameController instance;
    private final GameModel gameModel;
    private final World world;
    private float xPosition;
    private final HeroBody heroBody;
    private final EnemyBody enemyBody;
    private final boolean isContacted;
    private int score;


    /**
     * Class constructor
     * Initializes the world and the bodies in it
     */
    GameController() {
        this.gameModel = GameModel.getInstance();
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        ArrayList<GoldBody> goldBodyArray = new ArrayList<GoldBody>();

        world = new World(new Vector2(0, -10), true);
        world.setContactListener(this);

        xPosition = 0;

        for (int i = 0; i < gameModel.getGolds().size(); i++) {
            goldBodyArray.add(new GoldBody(world, gameModel.getGolds().get(i), false));
        }


        enemyBody = new EnemyBody(world, gameModel.getEnemy(), true);
        heroBody = new HeroBody(world, gameModel.getHero(), true);
        MapBody mapBody = new MapBody(world, gameModel.getMap(), false);


        mapBody.createBody(map);
        isContacted = true;
        this.score = 0;

    }

    /**
     * @return an instance of this class
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * @return a new instance of type GameController
     */
    public GameController newGameContoller() {
        return new GameController();
    }


    /**
     * @return the hero body
     */
    public HeroBody getHeroBody() {
        return heroBody;
    }


    /**
     * Calculates the next physics steps
     */
    public void update() {

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);


        world.step(1 / 60f, 6, 2);

        for (Body body : bodies) {
            if (body.getUserData() instanceof GoldModel ||
                    body.getUserData() instanceof BulletModel
                    || body.getUserData() instanceof HeroModel
                    || body.getUserData() instanceof EnemyModel) {
                ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            }
        }

        setState();
    }

    /**
     * @return a new instance of type GameController
     */
    public void newInstance(GameController newInstance) {
        instance = newInstance;
    }


    /**
     * @return the score of the game
     */
    public int getScore() {
        return score;
    }


    /**
     * Makes the hero jump
     */
    public void jump() {
        if (this.isContacted) {
            heroBody.getBody().applyLinearImpulse(new Vector2(0, 3f / PIXEL_TO_METER), heroBody.getBody().getWorldCenter(), true);
        }
    }


    /**
     * Makes the hero run
     */
    public void run() {

        if (heroBody.getBody().getLinearVelocity().x <= 2) {
            heroBody.getBody().applyLinearImpulse(new Vector2(0.4f / PIXEL_TO_METER, 0), heroBody.getBody().getWorldCenter(), true);
        }

    }


    /**
     * Makes the hero shoot
     */
    public void shoot() {
        gameModel.getHero().setPosition(heroBody.getX(), heroBody.getY());
        BulletModel bullet = gameModel.createBullet(gameModel.getHero().getPosition(), 10);
        BulletBody body = new BulletBody(world, bullet, true);
        //body.getBody().applyLinearImpulse(new Vector2(10f / PIXEL_TO_METER, 0), heroBody.getBody().getWorldCenter(), true);
        body.getBody().setLinearVelocity(1000f / PIXEL_TO_METER, 0f);
    }


    /**
     * @return the position of the game camera
     */
    public float getCameraPosition() {
        return xPosition;

    }


    /**
     * @param xPosition new position of the game camera
     */
    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;

    }


    /**
     *Removes body that has been flagged for removal
     */
    public void removeBody() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (body.getUserData() instanceof GoldModel || body.getUserData() instanceof BulletModel) {
                if (((EntityModel) body.getUserData()).isFlaggedForRemoval()) {
                    gameModel.remove((EntityModel) body.getUserData());
                    world.destroyBody(body);
                }
            }
        }
    }


    /**
     * Sets state of the hero according to his physics
     */
    private void setState() {
        if (heroBody.getBody().getLinearVelocity().x == 0) {
            gameModel.setHeroState(HeroState.State.PAUSED);
        } else if (heroBody.getBody().getLinearVelocity().x > 0) {
            gameModel.setHeroState(HeroState.State.RUNNING);
        } else if (heroBody.getBody().getLinearVelocity().y > 0) {
            gameModel.setHeroState(HeroState.State.JUMPING);
        } else if (heroBody.getBody().getPosition().y < 0) {
            gameModel.setHeroState(HeroState.State.DEAD);
        }
    }


    @Override
    public void beginContact(Contact contact) {
        //System.out.println("Contact");
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();


        // this.isContacted = true;




        if (bodyA.getUserData() instanceof EnemyModel && bodyB.getUserData() instanceof HeroModel) {
           if(score >= 5){
               this.score -=5;
           }
        }

        if (bodyA.getUserData() instanceof GoldModel && (bodyB.getUserData() instanceof HeroModel || bodyB.getUserData() instanceof BulletModel)) {
            goldCollides(bodyA);
            this.score += 5;
        }

        if (bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof GoldModel) {
            bulletCollides(bodyA);
            goldCollides(bodyB);
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    /**
     * Sets the gold flagged for removal
     * @param goldBody body of the gold
     */
    private void goldCollides(Body goldBody) {
        ((GoldModel) goldBody.getUserData()).setFlaggedForRemoval(true);
    }

    /**
     * Sets the bullet flagged for removal
     * @param bulletBody body of the gold
     */
    private void bulletCollides(Body bulletBody) {
        ((BulletModel) bulletBody.getUserData()).setFlaggedForRemoval(true);
    }
}

