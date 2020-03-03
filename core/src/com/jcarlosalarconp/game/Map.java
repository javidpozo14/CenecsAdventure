package com.jcarlosalarconp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * @author Juan Carlos
 * Map.java Manage all from the map (Size, Layers, camera, render, asset)
 * The game only have one map
 */

public class Map {
    private TiledMap map;
    private AssetManager manager;
    private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer terrainLayer;
    private TiledMapTileLayer terrainLayer2;
    private TiledMapTileLayer terrainLayer3;
    private int[] decorationLayers;
    private float w,h;
    public Map() {
        w = Gdx.graphics.getWidth(); //Width of the screen
        h = Gdx.graphics.getHeight(); //Height of the screen
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("mapas/tmx/cuevaPrincipal.tmx", TiledMap.class);
        manager.finishLoading();
        map = manager.get("mapas/tmx/cuevaPrincipal.tmx", TiledMap.class); //Set the tmx map from directory
        MapProperties properties = map.getProperties();
        tileWidth = properties.get("tilewidth", Integer.class); //Get the Tile Width
        tileHeight = properties.get("tileheight", Integer.class); //Get the Tile Height
        mapWidthInTiles = properties.get("width", Integer.class); //Get the Map Width (In Pixels)
        mapHeightInTiles = properties.get("height", Integer.class); //Get the Map Height (In Pixels)
        mapWidthInPixels = mapWidthInTiles * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera(mapWidthInPixels,mapHeightInPixels);
        camera.position.x = mapWidthInPixels/2;
        camera.position.y = mapHeightInPixels/2;
        w = w/mapWidthInPixels;
        h = h/mapHeightInPixels;
        MapLayers mapLayers = map.getLayers(); //Get the Map Layers from the .tmx map
        terrainLayer = (TiledMapTileLayer) mapLayers.get("Suelo");
        terrainLayer2 = (TiledMapTileLayer) mapLayers.get("Decoracion");
        terrainLayer3 = (TiledMapTileLayer) mapLayers.get("Laterales");
        //decorationLayers is the top layer to put the character behind this layer
        decorationLayers = new int[]{
                mapLayers.getIndex("CapaArriba")
        };
    }
    public void renderLayers() {
        renderer.setView(camera);
        renderer.getBatch().begin();
        renderer.renderTileLayer(terrainLayer);
        renderer.renderTileLayer(terrainLayer2);
        renderer.renderTileLayer(terrainLayer3);
        renderer.getBatch().end();
    }
    public void renderObjects() {
        renderer.render(decorationLayers);
    }

    public void dispose() {
        manager.dispose();
    }
    //Getters to use variables in others classes of the project
    public OrthographicCamera getCamera() {
        return camera;
    }

    public int getMapWidthInPixels() {
        return mapWidthInPixels;
    }

    public int getMapHeightInPixels() {
        return mapHeightInPixels;
    }


    public TiledMap getMap() {
        return map;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }
}
