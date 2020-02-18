package com.jcarlosalarconp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.Window;
import java.security.Key;

import sun.misc.ObjectInputFilter;

public class CenecsAdventure extends ApplicationAdapter {
	private TiledMap map;
	private AssetManager manager;
	private int tileWidth, tileHeight,
			mapWidthInTiles, mapHeightInTiles,
			mapWidthInPixels, mapHeightInPixels;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	private TiledMapTileLayer terrainLayer;
	private int[] decorationLayersIndices;
	private SpriteBatch batch;
	private Character character;
	@Override
	public void create () {
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("mapas/tmx/cuevaPrincipal.tmx", TiledMap.class);
		manager.finishLoading();
		map = manager.get("mapas/tmx/cuevaPrincipal.tmx", TiledMap.class);
		MapProperties properties = map.getProperties();
		tileWidth         = properties.get("tilewidth", Integer.class);
		tileHeight        = properties.get("tileheight", Integer.class);
		mapWidthInTiles   = properties.get("width", Integer.class);
		mapHeightInTiles  = properties.get("height", Integer.class);
		mapWidthInPixels  = mapWidthInTiles  * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;
		camera = new OrthographicCamera(320.f, 240.f);
		camera.position.x = mapWidthInPixels /2f;
		camera.position.y = mapHeightInPixels /2f;
		renderer = new OrthogonalTiledMapRenderer(map);
		MapLayers mapLayers = map.getLayers();
		terrainLayer = (TiledMapTileLayer) mapLayers.get("Suelo");
		decorationLayersIndices = new int[] {
				mapLayers.getIndex("Decoracion"),
				mapLayers.getIndex("Laterales"),
				mapLayers.getIndex("CapaArriba"),
		};
		renderer = new OrthogonalTiledMapRenderer(map);
		character=new Character(mapWidthInPixels,mapHeightInPixels);
		batch = new SpriteBatch();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputListener(character));
		Gdx.input.setInputProcessor(multiplexer);
		MapObjects mapObjects = map.getLayers().get("Colisiones").getObjects();
		for(MapObject objeto : mapObjects) {
			Rectangle rect = ((RectangleMapObject) objeto).getRectangle();

		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.0f, .0f, .0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.getBatch().begin();
		renderer.renderTileLayer(terrainLayer);
		renderer.getBatch().end();
		batch.begin();
		character.render(batch);
		batch.end();
		renderer.render(decorationLayersIndices);
	}

	@Override
	public void dispose () {
		manager.dispose();
	}
}