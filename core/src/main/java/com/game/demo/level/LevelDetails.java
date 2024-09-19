package com.game.demo.level;

import com.game.demo.logic.Log;
import com.game.demo.object.obstacles.Obstacle;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Setter
@Getter
public class LevelDetails {
    private String levelName;
    private List<Obstacle> obstacles;

    public static LevelDetails renderCurrentLevel(String levelName) {
        Log.info("Rendering level: " + levelName);
        LevelDetails levelDetails = new LevelDetails();
        levelDetails.levelName = levelName;

        File file = new File("assets/files/data/levels/" + levelName + ".yml");

        try {
            levelDetails.obstacles = Obstacle.getObstacles(file);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
        }

        return levelDetails;
    }
}
