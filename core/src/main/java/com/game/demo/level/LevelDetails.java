package com.game.demo.level;

import com.game.demo.logic.Log;
import com.game.demo.object.obstacles.Obstacle;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class LevelDetails {
    private String levelName;
    private List<Obstacle> obstacles;

    public static LevelDetails renderCurrentLevel(String levelName) {
        Log.info("Getting Level details...");
        LevelDetails currentLevel = new LevelDetails();
        currentLevel.levelName = levelName;

        File file = new File("assets/files/data/levels/" + levelName + ".yml");

        try {
            currentLevel.obstacles = Obstacle.getObstacles(file);
        } catch (Exception e) {
            Log.warning("Data File not located for current level");
        }

        LevelDetails.checkData(currentLevel);

        return currentLevel;
    }

    private static void checkData(LevelDetails levelDetails) {
        List<String> dataMissing = new ArrayList<>();

        if (levelDetails.levelName == null) {
            dataMissing.add("levelName");
        }
        if (levelDetails.obstacles == null) {
            dataMissing.add("obstacles");
        }
        if (!dataMissing.isEmpty()) {
            Log.warning("Data not found for objects: " + String.join(", ", dataMissing));
        } else {
            Log.info("Data loaded successfully");
        }
    }
}
