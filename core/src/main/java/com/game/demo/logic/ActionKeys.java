package com.game.demo.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Setter
@Getter
public class ActionKeys {
    private int moveNorth;
    private int moveSouth;
    private int moveEast;
    private int moveWest;
    private int interact;
    private int toggleMenu;

    //processing user input
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

    //initializing action keys
    public static ActionKeys getKeys() {
        Log.info("Setting up action keys...");

        ActionKeys keys;
        try {
            ActionKeysData data = YamlReader.mapper.readValue(new File("assets/files/data/action-keys.yml"), ActionKeysData.class);

            keys = new ActionKeys();
            keys.moveNorth = Input.Keys.valueOf(data.moveNorth);
            keys.moveSouth = Input.Keys.valueOf(data.moveSouth);
            keys.moveEast = Input.Keys.valueOf(data.moveEast);
            keys.moveWest = Input.Keys.valueOf(data.moveWest);
            keys.interact = Input.Keys.valueOf(data.interact);
            keys.toggleMenu = Input.Keys.valueOf(data.toggleMenu);
            Log.info("Keys set to custom configuration");
        } catch (Exception e) {
            Log.warning("Could not locate action-keys.yml data file");

            keys = ActionKeys.getDefaultKeys();
        }

        return keys;
    }

    private static ActionKeys getDefaultKeys() {
        Log.info("Setting all keys to default");
        ActionKeys keys = new ActionKeys();
        keys.setMoveNorth(Input.Keys.W);
        keys.setMoveSouth(Input.Keys.S);
        keys.setMoveEast(Input.Keys.D);
        keys.setMoveWest(Input.Keys.A);
        keys.setInteract(Input.Keys.E);
        keys.setToggleMenu(Input.Keys.ESCAPE);

        return keys;
    }

    @Setter
    @Getter
    private static class ActionKeysData {
        private String moveNorth;
        private String moveSouth;
        private String moveEast;
        private String moveWest;
        private String interact;
        private String toggleMenu;
    }
}
