package com.jcarlosalarconp.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputListener implements InputProcessor {
    private Character character;

    public InputListener(Character character){
        super();
        this.character=character;
    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                character.animations('w');
                break;
            case Input.Keys.A:
                character.animations('a');
                break;
            case Input.Keys.S:
                character.animations('s');
                break;
            case Input.Keys.D:
                character.animations('d');
                break;
        }
        return false;
    }

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

    @Override
    public boolean keyTyped(char keycode) {
        switch (keycode){
            case 'w':
                character.moveCharacter('w');
                break;
            case 's':
                character.moveCharacter('s');
                break;
            case 'a':
                character.moveCharacter('a');
                break;
            case 'd':
                character.moveCharacter('d');
                break;
        }        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
