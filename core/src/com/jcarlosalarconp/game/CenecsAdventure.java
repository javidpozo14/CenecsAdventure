package com.jcarlosalarconp.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.DisplayMode;
import java.awt.Point;

/**
 * @author Juan Carlos
 * CenecsAdventure.java is the Main class (The name of the game)
 * This class create, inicialize, render and dispose every necessary to run the game
 * (Sprite, character, map, stage and collisions)
 */

public class CenecsAdventure extends ApplicationAdapter {
	private SpriteBatch batch;
	private Character character;
	private Map map;
	private Stage stage;
	private Collisions collisions;

	@Override
	public void create () {
		//Creating map, character, collision and stage
		map = new Map();
		character = new Character(150,100, map.getMapWidthInPixels()/7f,map.getMapHeightInPixels()/3f);
		collisions=new Collisions();
		collisions.checkCollision(map.getMap(),character);	//Cheking collisions from map and character
		batch = new SpriteBatch();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputListener(character));
		Gdx.input.setInputProcessor(multiplexer);	//Set the input processor
		stage=new Stage();
		stage.setDebugAll(true);
		stage.addActor(character); //Set the character in the stage
		//Iterate collisions of the map and character
		for(int b=0;b<collisions.getActor().length-1;b++){
			stage.addActor(collisions.getActor()[b]);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //Background color to Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.getCamera().update();
		map.renderLayers();
		batch.begin();
		character.render(batch);
		batch.end();
		map.renderObjects();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose () {
		map.dispose();
	}
}
