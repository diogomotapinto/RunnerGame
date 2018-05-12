package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
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
import com.mygdx.game.controller.entities.TileBody;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.model.entities.MapModel;
import com.mygdx.game.utils.Utilities;
import com.mygdx.game.view.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class GameController implements ContactListener {
    private static GameController instance;
    private float xPosition;
    private final World world;
    private HeroBody heroBody;
    private MapBody mapBody;
    private EnemyBody enemyBody;
    private ArrayList<GoldBody> goldBodyArray;
    private ArrayList<TileBody> tileBodyArray;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    private boolean isContacted ;





    private GameController() {

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        goldBodyArray = new ArrayList<GoldBody>();
        tileBodyArray = new ArrayList<TileBody>();
        world = new World(new Vector2(0, -10), true);

        xPosition = 0;

        for (int i = 0; i < GameModel.getInstance().getGolds().size(); i++){
          goldBodyArray.add(new GoldBody(world, GameModel.getInstance().getGolds().get(i), false)) ;
        }



        for (int i = 0; i < GameModel.getInstance().getTiles().size(); i++){
            tileBodyArray.add(new TileBody(world, GameModel.getInstance().getTiles().get(i), false));
        }

        enemyBody = new EnemyBody(world,GameModel.getInstance().getEnemy(),true);
        heroBody = new HeroBody(world,GameModel.getInstance().getHero(), true );
        mapBody = new MapBody(world,GameModel.getInstance().getMap(),false);


        mapBody.createBody(map);
        isContacted = true;
        world.setContactListener(this);

    }

    public HeroBody getHeroBody() {
        return heroBody;
    }

    public void update(float delta){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);



        world.step(1/60f,6,2);
        for (Body b : bodies){

        }

    }

    public World getWorld() {
        return world;
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }


    public void jump(float delta){
        if(this.isContacted) {
            heroBody.getBody().applyLinearImpulse(new Vector2(0, 3f / PIXEL_TO_METER), heroBody.getBody().getWorldCenter(), true);
        }
    }

    public void run(float delta){

       if(heroBody.getBody().getLinearVelocity().x <= 2){
           heroBody.getBody().applyLinearImpulse(new Vector2(0.4f / PIXEL_TO_METER, 0), heroBody.getBody().getWorldCenter(), true);
       }

    }

    public void shoot(){
        GameModel.getInstance().getHero().setPosition(heroBody.getX()*100,heroBody.getY()*100);
        System.out.println(heroBody.getX());
        BulletModel bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getHero());
        BulletBody body = new BulletBody(world, bullet,true);
        body.getBody().applyLinearImpulse(new Vector2(10f / PIXEL_TO_METER, 0), heroBody.getBody().getWorldCenter(), true);
    }

    public ArrayList<TileBody> getTileBodyArray() {
        return tileBodyArray;
    }

    public float getCameraPosition() {
        return xPosition;

    }






    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;

    }

    public void removeBody() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if( body.getUserData() instanceof  GoldModel) {
                if (((EntityModel) body.getUserData()).isFlaggedForRemoval()) {
                    GameModel.getInstance().remove((EntityModel) body.getUserData());
                    world.destroyBody(body);
                }
            }
        }



    }



    @Override
    public void beginContact(Contact contact) {

        System.out.println("Contact");
        Body bodyA = contact.getFixtureA().getBody();

        if(bodyA.getUserData() instanceof MapModel){
            this.isContacted = true;
        }

        if (bodyA.getUserData() instanceof GoldModel) {
            heroCollidesGold(bodyA);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();

        if(bodyA.getUserData() instanceof MapModel){
            this.isContacted = false;
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    private void heroCollidesGold(Body goldBody){
        ((GoldModel)goldBody.getUserData()).setFlaggedForRemoval(true);
    }
}

