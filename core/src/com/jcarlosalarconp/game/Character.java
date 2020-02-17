package com.jcarlosalarconp.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.plaf.synth.Region;

public class Character extends Actor {
    public int x, y;
    private Animation animation;
    private float tiempo;
    private TextureRegion[] regions;
    private Texture texture;
    private TextureRegion textureRegion;
    private TextureRegion[][] tmp;
    private String jugadorVista;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        jugadorVista = "";
        texture = new Texture(Gdx.files.internal("gfx/character.png"));
        tmp = TextureRegion.split(texture, texture.getWidth() / 17, texture.getHeight() / 8);
        regions = new TextureRegion[4];
        for (int b = 0; b < regions.length; b++) {
            regions[b] = tmp[0][0];
            animation = new Animation((float) 0.2, regions);
            tiempo = 0f;
        }

    }

    public void render(final SpriteBatch batch) {
        tiempo += Gdx.graphics.getDeltaTime();
        textureRegion = (TextureRegion) animation.getKeyFrame(tiempo, true);
        batch.draw(textureRegion, x, y);
    }

    public void moveCharacter(char letra) {
        switch (letra) {
            case 'w':
                y = y + 3;
                break;
            case 's':
                y = y - 3;
                break;
            case 'd':
                x = x + 3;
                break;
            case 'a':
                x = x - 3;
                break;
        }
    }

    public void animations(char letra) {
        switch (letra) {
            case 'w':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[2][b];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case 'a':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[3][b];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case 's':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[0][b];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case 'd':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[1][b];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
        }


    }

    public void stopCharacter(char letra) {
        switch (letra) {
            case 'w':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[2][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                    jugadorVista = "Arriba";
                }
                break;
            case 'a':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[3][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                    jugadorVista = "Izquierda";
                }
                break;
            case 's':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[0][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                    jugadorVista = "Abajo";
                }
                break;
            case 'd':
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[1][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                    jugadorVista = "Derecha";
                }
                break;


        }

    }

    public void stopCharacter() {
        texture = new Texture(Gdx.files.internal("gfx/character.png"));
        tmp = TextureRegion.split(texture, texture.getWidth() / 17, texture.getHeight() / 8);
        regions = new TextureRegion[4];
        switch (jugadorVista) {
            case "Up":
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[2][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case "Left":
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[3][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case "Down":
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[0][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
            case "Right":
                for (int b = 0; b < regions.length; b++) {
                    regions[b] = tmp[1][0];
                    animation = new Animation((float) 0.2, regions);
                    tiempo = 0f;
                }
                break;
        }
    }
}

