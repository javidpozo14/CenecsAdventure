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
 * Character.java manage all from the main character that we control in the game
 * This class extends from 'Actor' (LibGDX class)
 */
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
    private Stone stone;

    //Constructor with player's location at the map (x, y) and the player's size (characterWidth, characterHeight)
    public Character(int x, int y,float characterWidth,float characterHeight) {
        this.x = x;
        this.y = y;
        this.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10); //rescale the size of the player depending of the map
        collisions = new Collisions();
        map = new Map();
        try {
            collisions.checkCollision(map.getMap(), this, stone);
        }catch(Exception e){
            System.out.println("Not Stone found");
        }
        mapCollision = collisions.getMapCollisions(); //Get the map collisions
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
        texture = new Texture(Gdx.files.internal("gfx/character.png"));
        characterDirection = "";
        characterHitBox = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        tmp = TextureRegion.split(texture, texture.getWidth() / 17, texture.getHeight() / 8); //Set the size to scale the atlas of the character
        regions = new TextureRegion[4];
        //Split the atlas
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
    //Check the character direction and collision to change the sprite and move it at the map
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
    //Get the movement of the player to do the motion animation
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
    //When the player stop moving, the sprite return to the default sprite depending of the direction
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
    //Getters to use variables in others classes of the project
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
