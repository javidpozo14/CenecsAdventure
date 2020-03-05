package com.jcarlosalarconp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Juan Carlos
 * stone.java manage all from the main stone that we control in the game
 * This class extends from 'Actor' (LibGDX class)
 */
public class Stone extends Actor {
    public float x, y;
    private Rectangle stoneHitBox;
    private Sprite sprite;
    private Collisions collisions;
    private float stoneWidth, stoneHeight;
    private Boolean isCollision;
    private Map map;
    private Character character;

    //Constructor with player's location at the map (x, y) and the player's size (stoneWidth, stoneHeight)
    public Stone(int x, int y, float stoneWidth, float stoneHeight) {
        this.x = x;
        this.y = y;
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10); //rescale the size of the player depending of the map
        collisions = new Collisions();
        collisions.checkCollision(map.getMap(), character, this);
        this.stoneWidth = stoneWidth;
        this.stoneHeight = stoneHeight;
        stoneHitBox = new Rectangle(x,y,Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

    }

    public void render(final SpriteBatch batch) {
        sprite=new Sprite(new Texture("gfx/stone.png"));
        setBounds(x,y,stoneWidth-1,stoneHeight-10);
        batch.draw(sprite, x, y, stoneWidth, stoneHeight);
    }

    //Getters to use variables in others classes of the project
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public float getStoneWidth() {
        return stoneWidth;
    }

    public float getStoneHeight() {
        return stoneHeight;
    }

    public Rectangle getStoneHitBox() {
        return stoneHitBox;
    }
}
