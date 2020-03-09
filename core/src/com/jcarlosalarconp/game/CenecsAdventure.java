package com.jcarlosalarconp.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
	private TextureAtlas textureAtlas;
	private Stage stageButtons;
	private ImageButton rightButton;
	private ImageButton leftButton;
	private ImageButton upButton;
	private ImageButton downButton;
	private Music music;
	private Stone stone;
	private Boolean pressed;
	private char direction;
	@Override
	public void create () {
		//Creating map, character, collision and stage
		map = new Map();
		character = new Character(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/4, map.getMapWidthInPixels()/2.5f,map.getMapHeightInPixels()/1.5f);
		//stone = new Stone(280,290, map.getMapWidthInPixels()/7f,map.getMapHeightInPixels()/3f);
		collisions=new Collisions();
		collisions.checkCollision(map.getMap(),character);	//Cheking collisions from map and character
		batch = new SpriteBatch();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new Listener(character));
		Gdx.input.setInputProcessor(multiplexer);	//Set the input processor
		stage=new Stage();
		stage.setDebugAll(true);
		stage.addActor(character); //Set the character in the stage
		//stage.addActor(stone); //Set the character in the stage
		//Iterate collisions of the map and character
		for(int b=0;b<collisions.getActor().length-1;b++){
			stage.addActor(collisions.getActor()[b]);
		}
		stageButtons = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stageButtons);
		textureAtlas = new TextureAtlas("buttons/botone.pack");
		Skin skin = new Skin();
		skin.addRegions(textureAtlas);
		ImageButton.ImageButtonStyle rightStyle = new ImageButton.ImageButtonStyle();
		rightStyle.up = skin.getDrawable("right_green");
		rightButton = new ImageButton(rightStyle);
		rightButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(true);
				character.stopCharacter('d');
				return;
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(false);
				direction=moveCharacter('d');
				character.doAnimations('d');
				return true;
			}
		});

        ImageButton.ImageButtonStyle leftStyle = new ImageButton.ImageButtonStyle();
        leftStyle.up = skin.getDrawable("left_green");
        leftButton = new ImageButton(leftStyle);
        leftButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(true);
				character.stopCharacter('a');
				return;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(false);
				direction=moveCharacter('a');
                character.doAnimations('a');
                return true;
            }
        });

        ImageButton.ImageButtonStyle upStyle = new ImageButton.ImageButtonStyle();
        upStyle.up = skin.getDrawable("up_green");
        upButton = new ImageButton(upStyle);
        upButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(true);
				character.stopCharacter('w');
                return;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(false);
				direction=moveCharacter('w');
                character.doAnimations('w');
                return true;
            }
        });

        ImageButton.ImageButtonStyle downStyle = new ImageButton.ImageButtonStyle();
        downStyle.up = skin.getDrawable("down_green");
        downButton = new ImageButton(downStyle);
        downButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(true);
				character.stopCharacter('s');
				return;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setDirection(false);
				direction=moveCharacter('s');
                character.doAnimations('s');
				return true;
            }
        });

		Table table = new Table();
		table.bottom();
		table.setFillParent(true);
        table.add(leftButton).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
        table.add(upButton).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
        table.add(downButton).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
        table.add(rightButton).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);

        stageButtons.addActor(table);

		music = Gdx.audio.newMusic(Gdx.files.internal("music/track.mp3"));
		music.setLooping(true);
		music.setVolume(8.5f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //Background color to Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.getCamera().update();
		map.renderLayers();
		batch.begin();
		character.render(batch);
		//stone.render(batch);
		batch.end();
		map.renderObjects();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		stageButtons.act();
		stageButtons.draw();
		if(Gdx.input.isButtonPressed(0)&&!pressed){
			character.moveCharacter(direction);
		}
	}

	@Override
	public void dispose () {
		map.dispose();
	}

	public char moveCharacter(char direction) {
		switch (direction) {
			case 'w':
				this.direction = 'w';
				break;
			case 'd':
				this.direction = 'd';
				break;
			case 's':
				this.direction = 's';
				break;
			case 'a':
				this.direction = 'a';
				break;
		}
		return direction;
	}
	public void setDirection(Boolean pressed){
		this.pressed = pressed;
	}

}