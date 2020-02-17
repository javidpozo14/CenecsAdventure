package com.jcarlosalarconp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CenecsAdventure extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Character character;

	@Override
	public void create () {
		batch = new SpriteBatch();
		character=new Character(100,100);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputListener(character));
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		character.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
