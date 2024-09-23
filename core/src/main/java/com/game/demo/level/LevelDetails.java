/* 23/09/2024 - Gust
 * Class that contains all the details of a given level. This class should handle all updates for the details of a level
 * as needed.
 * Last updated: 23/09/2024 - Gust
 */

//TODO: (BUG) at least renderCurrentLevel() runs multiple times (check logs), may also be running more methods

package com.game.demo.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.demo.Core;
import com.game.demo.logic.Log;
import com.game.demo.object.obstacles.Door;
import com.game.demo.object.obstacles.Obstacle;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class LevelDetails {
    private Core core;

    private String levelName;
    private List<Obstacle> obstacles;
    private List<Door> doors;
    //list<interactable>

    public LevelDetails(Core core) {
        this.core = core;
        renderCurrentLevel("main-menu");
    }

// -------------------------------------------------------------------------------------------------------------- //
// === LEVEL RENDERING (ONCE) === //
/*
 * Determines all that happens a singular time during level rendering, usually when loading up the core or changing levels
 */

    /**
     * Instantiates and stores data of a new level.
     * Should happen every time a new level is loaded.
     *
     * @param levelName name of the level to be rendered
     */
    public void renderCurrentLevel(String levelName) {
        clearLevelInfo();

        Log.info("Loading level: " + levelName);
        this.levelName = levelName;

        File file = new File("assets/data/levels/" + levelName +"/" + levelName + ".yml");
        try {
            this.obstacles = Obstacle.getObstacles(file);
            this.doors = Door.getDoors(file);
        } catch (Exception e) {
            Log.warning("Data File not located for current level");
        }

        checkData();
    }

    private void clearLevelInfo() {
        Log.info("Clearing data from previous level...");

        //clear obstacles data
        if (obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                obstacle.getTexture().dispose();
            }
            this.obstacles.clear();
        }

        Log.info("Data cleared");
    }

    /**
     * Checks if data for all fields exist. If not, logs what data has not been found.
     * Called after level has been instantiated
     */
    private void checkData() {
        List<String> dataMissing = new ArrayList<>();

        if (this.levelName == null) {
            dataMissing.add("levelName");
        }
        if (this.obstacles == null) {
            dataMissing.add("obstacles");
        }
        if (this.doors == null) {
            dataMissing.add("doors");
        }
        if (!dataMissing.isEmpty()) {
            Log.warning("Data not found for objects: " + String.join(", ", dataMissing));
        } else {
            Log.info("Data loaded successfully");
        }
    }

    public List<Obstacle> getAllObstacles() {
        for (Door door : this.getDoors()) {
            obstacles.add(new Obstacle(door.getBounds(), door.getTexture()));
        }

        return obstacles;
    }

// -------------------------------------------------------------------------------------------------------------- //
// === LEVEL RENDERING (EVERY FRAME) === //
/*
 * Determines all actions and information required in order to update the level data every core frame.
 */

    public void drawAll() {
        updateCamera();

        core.batch.begin();
        drawLevel();
        core.player.drawPlayer(core.batch);
        core.player.drawPlayerFov(core.batch);
        drawGameDetails();
        core.batch.end();

        core.actionKeys.checkInput();
    }

    private void updateCamera() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        core.camera.update();
        core.batch.setProjectionMatrix(core.camera.combined);
    }

    /**
     * Draws all textures within the level. Not related to hitboxes or collision, for that, refer to {@link com.game.demo.object.Character}
     */
    private void drawLevel() {
        for (Obstacle obstacle : obstacles) {
            core.batch.draw(obstacle.getTexture(), obstacle.getBounds().x, obstacle.getBounds().y, obstacle.getBounds().width, obstacle.getBounds().height);
        }
        for (Door door : doors) {
            core.batch.draw(door.getTexture(), door.getBounds().x, door.getBounds().y, door.getBounds().width, door.getBounds().height);
        }
    }

    private void drawGameDetails() {
        core.font.draw(core.batch,
            "CURRENT LEVEL: " + levelName +
                "\nPLAYER" +
                "\nx: " + core.player.getCharacter().getX() +
                "\ny: " + core.player.getCharacter().getY() +
                "\nxSpeed: " + core.player.getCharacter().getXSpeed() +
                "\nySpeed: " + core.player.getCharacter().getYSpeed() +
                "\n" +
                "\nSCREEN" +
                "\nwidth: " + Gdx.graphics.getWidth() +
                "\nheight: " + Gdx.graphics.getHeight() +
                "\n",
            4, 320-4
        );
    }

// -------------------------------------------------------------------------------------------------------------- //
// === UPDATING DATA === //

    public void changeLevels(String levelName, Vector2 destination, String facing) {
        renderCurrentLevel(levelName);
        core.player.setPosition(destination, facing);
    }

}
