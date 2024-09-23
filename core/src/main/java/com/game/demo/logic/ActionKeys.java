/* 23/09/2024 - Gust
 * Last updated: 23/09/2024 - Gust
 */

package com.game.demo.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.demo.Core;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Setter
@Getter
public class ActionKeys {
    private Core core; //why?

    private int moveNorth;
    private int moveSouth;
    private int moveEast;
    private int moveWest;
    private int interact;
    private int toggleMenu;

    /**
     * Listener for user input every frame the application is rendered.
     */
    public void checkInput() {
        for (int key : List.of(moveNorth, moveSouth, moveEast, moveWest)) {
            if(Gdx.input.isKeyPressed(key)){
                processInput(key);
            }
        }

        for (int key : List.of(interact, toggleMenu)) {
            if (Gdx.input.isKeyJustPressed(key)) {
                processInput(key);
            }
        }
    }

    /**
     * Processes the detected user input.
     * @param keycode integer value of the input to be processed. Refer to {@link com.badlogic.gdx.Input.Keys} for the defined codes
     */
    private void processInput(int keycode) {
        if (keycode == moveNorth) {
            core.player.getCharacter().move(core.level.levelDetails.getAllObstacles(), 0, 1);
            core.player.getFov().setNorth();
        }
        if (keycode == moveSouth) {
            core.player.getCharacter().move(core.level.levelDetails.getAllObstacles(),0, -1);
            core.player.getFov().setSouth();
        }
        if (keycode == moveEast) {
            core.player.getCharacter().move(core.level.levelDetails.getAllObstacles(), 1, 0);
            core.player.getFov().setEast();
        }
        if (keycode == moveWest) {
            core.player.getCharacter().move(core.level.levelDetails.getAllObstacles(), -1, 0);
            core.player.getFov().setWest();
        }
        if (keycode == interact) {
            core.player.interact();
        }
        if (keycode == toggleMenu) {}
    }

    /**
     * Attempts to initialize action keys with custom saved data. If unable, sets the action keys to their default value.
     * @return instantiated ActionKeys object
     */
    public static ActionKeys getKeys() {
        Log.info("Setting up action keys...");

        ActionKeys keys;
        try {
            ActionKeysData data = YamlReader.mapper.readValue(new File("assets/data/action-keys.yml"), ActionKeysData.class);

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

    /**
     * Sets action keys to their default value.
     * @return instantiated ActionKeys object
     */
    private static ActionKeys getDefaultKeys() {
        Log.info("Setting all keys to default");
        ActionKeys keys = new ActionKeys();
        keys.setMoveNorth(Input.Keys.W); //51
        keys.setMoveSouth(Input.Keys.S); //47
        keys.setMoveEast(Input.Keys.D); //32
        keys.setMoveWest(Input.Keys.A); //29
        keys.setInteract(Input.Keys.E); //33
        keys.setToggleMenu(Input.Keys.ESCAPE); //111

        return keys;
    }

    /**
     * Custom class used along with YamlReader for the parsing of data from a YAML file.
     */
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
