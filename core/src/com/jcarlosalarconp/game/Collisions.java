package com.jcarlosalarconp.game;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Collisions {
    private Actor[] actor;
    private Rectangle[] mapCollisions;
    private Rectangle characterHitBox;
    private Map map;
    public void checkCollision(TiledMap map, Character character) {
        characterHitBox=new Rectangle();
        map = new TiledMap();
        characterHitBox.set(character.getX(),character.getY(),character.getCharacterWidth(),character.getCharacterHeight());
        MapObjects mons = map.getLayers().get("Colisiones").getObjects();
        actor=new Actor[mons.getCount()];
        mapCollisions=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            mapCollisions[i]=rect1;
            mapCollisions[i].set(rect1.x*rect1.getWidth(),rect1.y*rect1.getHeight(),rect1.width*rect1.getWidth(),rect1.height*rect1.getHeight());
            actor[i]=new Actor();
            actor[i].setBounds(rect1.x,rect1.y,rect1.width,rect1.height);
        }
    }

    public Actor[] getActor() {
        return actor;
    }

    public Rectangle[] getMapCollisions() {
        return mapCollisions;
    }
}
