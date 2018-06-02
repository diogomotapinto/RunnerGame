package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameServices;
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

/**
 * Class that controls the physical aspects of the game
 */
public class GameController implements ContactListener {

    /**
     * Width of the game
     */
    public static final int V_WIDTH = 400;
    /**
     * Height of the game
     */
    public static final int V_HEIGHT = 208;
    /**
     * Score
     */
    private static final int SCORE = 5;
    /**
     * Gravity force
     */
    private static final int GRAVITY = -10;

    /**
     * Velocity Iterations of the game
     */
    private static final int VELOCITY_ITERATIONS = 6;

    /**
     * Position Iterations of the game
     */
    private static final int POSITION_ITERATIONS = 2;

    /*
     *Jump Impulse
     */
    private static final float JUMP_IMPULSE = 3f;

    /*
     *Running Impulse
     */
    private static final float BODY_IMPULSE = 0.4f;

    /*
     *Hero max linear velocity
     */
    private static final int MAX_SPEED = 2;

    /*
     *Bullets distance from the hero in the x-axis
     */
    private static final int BULLET_POSITION = 10;

    /**
     * Bullet Speed
     */
    private static final float BULLET_SPEED = 1000f;

    /**
     * Game time step
     */
    private static final float TIME_STEP = 1 / 60f;

    /**
     * Game model instance
     */
    private final GameModel gameModel;

    /**
     * Hero body instance
     */
    private final HeroBody heroBody;

    /**
     * Enemy body instance
     */
    private final EnemyBody enemyBody;

    /**
     * Boolean to control some aspects of the game
     */
    private final boolean isContacted;

    /**
     * Physics world used in the game
     */
    private final World world;

    /**
     * TileMap used in the game
     */
    private final TiledMap map;
    /**
     * Instance of Game Services class
     */
    private final GameServices gameServices;
    /**
     * X-position in of the hero
     */
    private float xPosition;
    /**
     * Score of the game
     */
    private int score;


    /**
     * Class constructor
     * Initializes the world and the bodies in it
     */
    public GameController(TiledMap map, GameModel gameModel, GameServices gameServices) {
        this.gameModel = gameModel;
        this.map = map;
        this.gameServices = gameServices;
        ArrayList<GoldBody> goldBodyArray = new ArrayList<GoldBody>();

        world = new World(new Vector2(0, GRAVITY), true);
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
     * @return the hero body
     */
    public HeroBody getHeroBody() {
        return heroBody;
    }


    /**
     * Calculates the next steps
     */
    public void update() {

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);


        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

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
            heroBody.getBody().applyLinearImpulse(new Vector2(0, JUMP_IMPULSE / PIXEL_TO_METER), heroBody.getBody().getWorldCenter(), true);
        }
    }


    /**
     * Makes the hero run
     */
    public void run() {
        if (heroBody.getBody().getLinearVelocity().x <= MAX_SPEED) {
            heroBody.getBody().applyLinearImpulse(new Vector2(BODY_IMPULSE / PIXEL_TO_METER, 0), heroBody.getBody().getWorldCenter(), true);
        }
    }


    /**
     * Makes the hero shoot
     */
    public void shoot() {
        gameModel.getHero().setPosition(heroBody.getX(), heroBody.getY());
        BulletModel bullet = gameModel.createBullet(gameModel.getHero().getPosition(), BULLET_POSITION);
        BulletBody body = new BulletBody(world, bullet, true);
        body.getBody().setLinearVelocity(BULLET_SPEED / PIXEL_TO_METER, 0f);
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
     * Removes body that has been flagged for removal
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
        if (heroBody.getBody().getLinearVelocity().x == 0 && heroBody.getBody().getPosition().y > 0) {
            gameModel.setHeroState(HeroState.State.PAUSED);
        } else if (heroBody.getBody().getLinearVelocity().x > 0 && heroBody.getBody().getPosition().y > 0) {
            gameModel.setHeroState(HeroState.State.RUNNING);
        } else if (heroBody.getBody().getLinearVelocity().y > 0 && heroBody.getBody().getPosition().y > 0) {
            gameModel.setHeroState(HeroState.State.JUMPING);
        } else if (heroBody.getBody().getPosition().y < 0) {
            gameModel.setHeroState(HeroState.State.DEAD);
        }
    }


    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof EnemyModel && bodyB.getUserData() instanceof HeroModel) {
            if (score >= SCORE) {
                this.score -= SCORE;
            }

        }

        if (bodyA.getUserData() instanceof GoldModel && (bodyB.getUserData() instanceof HeroModel || bodyB.getUserData() instanceof BulletModel)) {
            goldCollides(bodyA);
            this.score += SCORE;
            gameServices.submitScore(getScore());
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
     *
     * @param goldBody body of the gold
     */
    private void goldCollides(Body goldBody) {
        ((GoldModel) goldBody.getUserData()).setFlaggedForRemoval(true);
    }

    /**
     * Sets the bullet flagged for removal
     *
     * @param bulletBody body of the gold
     */
    private void bulletCollides(Body bulletBody) {
        ((BulletModel) bulletBody.getUserData()).setFlaggedForRemoval(true);
    }
}

