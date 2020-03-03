package com.jcarlosalarconp.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Juan Carlos
 * InputListener.java manage the input of the player tu control the character
 * Use the functions of Character.java (doAnimations(), moveCharacter(), stopCharacter())
 */

public class InputListener implements InputProcessor {
    private Character character;
    private Collisions collisions;
    private Actor[] actor;
    private Rectangle[] rectangles;
    private Boolean isCollision;

    //Contructor only need the character
    public InputListener(Character character){
        super();
        this.character=character;
    }

    //When the key is down, the character do the animation depending of the direction
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.D:
                character.doAnimations('d');
                break;
            case Input.Keys.S:
                character.doAnimations('s');
                break;
            case Input.Keys.A:
                character.doAnimations('a');
                break;
            case Input.Keys.W:
                character.doAnimations('w');
                break;
        }
        return false;
    }

    //When quit the key down, the character stop with the default sprite of the direction
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.D:
                character.stopCharacter('d');
                break;
            case Input.Keys.S:
                character.stopCharacter('s');
                break;
            case Input.Keys.A:
                character.stopCharacter('a');
                break;
            case Input.Keys.W:
                character.stopCharacter('w');
                break;
        }
        return false;
    }

    //When the key is typed, the character moves in to the right direction
    @Override
    public boolean keyTyped(char key) {
        String characterDirection=String.valueOf(key);
        switch (characterDirection.toLowerCase()){
            case "w":
                character.moveCharacter('w');
                break;
            case "s":
                character.moveCharacter('s');
                break;
            case "a":
                character.moveCharacter('a');
                break;
            case "d":
                character.moveCharacter('d');
                break;

        }        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX+" y:"+screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
