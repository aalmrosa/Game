package com.game.demo.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.List;

public class ActionKeys {

    public int moveNorth;
    public int moveSouth;
    public int moveEast;
    public int moveWest;
    public int interact;
    public int toggleMenu;

    public ActionKeys() {
        setKeys();
    }

    public void checkInput(){
        for(int key : List.of(moveNorth, moveSouth, moveEast, moveWest)){
            if(Gdx.input.isKeyPressed(key)){
                processInput(key);
            }
        }

        for(int key : List.of(interact, toggleMenu)){
            if(Gdx.input.isKeyJustPressed(key)){
                processInput(key);
            }
        }
    }

    private void processInput(int key){
        if(key == moveNorth){}
        if(key == moveSouth){}
        if(key == moveEast){}
        if(key == moveWest){}
        if(key == interact){}
        if(key == toggleMenu){}
    }

    private void setKeys() {
        moveNorth = Input.Keys.W;
        moveSouth = Input.Keys.S;
        moveEast = Input.Keys.D;
        moveWest = Input.Keys.A;
        interact = Input.Keys.SPACE;
        toggleMenu = Input.Keys.ESCAPE;
    }

}
