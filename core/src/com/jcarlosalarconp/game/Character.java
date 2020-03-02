package com.jcarlosalarconp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor {
    public float x, y;
    private Animation animation;
    private float time;
    private Rectangle characterHitBox;
    private TextureRegion[] regions;
    private Texture texture;
    private TextureRegion textureRegion;
    private TextureRegion[][] tmp;
    private Rectangle[] mapCollision;
    private Sprite sprite;
    private String characterDirection;
    private Collisions collisions;
    private float characterWidth, characterHeight;
    private Boolean isCollision;
    private Map map;
    public Character(int x, int y,float characterWidth,float characterHeight) {
        this.x = x;
        this.y = y;
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
        collisions = new Collisions();
        map = new Map();
        collisions.checkCollision(map.getMap(),this);
        mapCollision = collisions.getMapCollisions();
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
        texture = new Texture(Gdx.files.internal("gfx/character.png"));
        characterDirection = "";
        characterHitBox = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        tmp = TextureRegion.split(texture, texture.getWidth() / 17, texture.getHeight() / 8);
        regions = new TextureRegion[4];
        for (int b = 0; b < regions.length; b++) {
            regions[b] = tmp[0][0];
            animation = new Animation((float) 0.2, regions);
            time = 0f;
        }
    }

    public void render(final SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime();
        textureRegion = (TextureRegion) animation.getKeyFrame(time, true);
        sprite=new Sprite(textureRegion);
        setBounds(x,y,characterWidth-1,characterHeight-10);
        batch.draw(sprite, x, y, characterWidth, characterHeight);
    }

    public void moveCharacter(char direction) {
        switch (direction) {
            case 'w':
                for(int b=0; b<mapCollision.length; b++){
                    if(mapCollision[b].overlaps(characterHitBox.set(x,y+9, characterWidth, characterHeight))){
                        isCollision = true;
                        break;
                    }else{
                        isCollision = false;
                    }
                }
                if(isCollision == false){
                    y = y+9;
                }
                break;
            case 's':
                for(int b=0; b<mapCollision.length; b++){
                    if(mapCollision[b].overlaps(characterHitBox.set(x,y-9, characterWidth, characterHeight))){
                        isCollision = true;
                        break;
                    }else{
                        isCollision = false;
                    }
                }
                if(isCollision == false){
                    y = y-9;
                }
                break;
            case 'd':
                for(int b=0; b<mapCollision.length; b++){
                    if(mapCollision[b].overlaps(characterHitBox.set(x+9, y, characterWidth, characterHeight))){
                        isCollision = true;
                        break;
                    }else{
                        isCollision = false;
                    }
                }
                if(isCollision == false){
                    x = x+9;
                }
                break;
            case 'a':
                for(int b=0; b<mapCollision.length; b++){
                    if(mapCollision[b].overlaps(characterHitBox.set(x-9, y, characterWidth, characterHeight))){
                        isCollision = true;
                        break;
                    }else{
                        isCollision = false;
                    }
                }
                if(isCollision == false){
                    x = x-9;
                }
                break;
        }
    }

    public void doAnimations(char direction) {
        switch (direction) {
            case 'd':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[1][b];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                }
                break;
            case 's':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[0][b];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                }
                break;
            case 'a':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[3][b];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                }
                break;
            case 'w':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[2][b];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                }
                break;
        }
    }

    public void stopCharacter(char direction) {
        switch (direction) {
            case 'd':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[1][0];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                    characterDirection = "Right";
                }
                break;
            case 's':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[0][0];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                    characterDirection = "Down";
                }
                break;
            case 'a':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[3][0];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                    characterDirection = "Left";
                }
                break;
            case 'w':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[2][0];
                    animation = new Animation((float) 0.2, regions);
                    time = 0f;
                    characterDirection = "Up";
                }
                break;
        }

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public float getCharacterWidth() {
        return characterWidth;
    }

    public float getCharacterHeight() {
        return characterHeight;
    }

    public Rectangle getcharacterHitBox() {
        return characterHitBox;
    }
}
