package com.game.demo.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.demo.Demo;

import java.util.List;

public class ActionKeys {
    private final Demo game;

    public int moveNorth;
    public int moveSouth;
    public int moveEast;
    public int moveWest;
    public int interact;
    public int toggleMenu;

    public ActionKeys(Demo game) {
        this.game = game;
        setKeys();
    }

    public void checkInput(){
        for(int key : List.of(moveNorth, moveSouth, moveEast, moveWest)){
            if(Gdx.input.isKeyPressed(key)){
                processInput(key);
                return;
            }
        }

        for(int key : List.of(interact, toggleMenu)){
            if(Gdx.input.isKeyJustPressed(key)){
                processInput(key);
                return;
            }
        }
    }

    private void processInput(int key){
        if(key == moveNorth){
            game.player.move().north();
        }
        if(key == moveSouth){
            game.player.move().south();
        }
        if(key == moveEast){
            game.player.move().east();
        }
        if(key == moveWest){
            game.player.move().west();
        }
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
