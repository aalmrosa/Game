package com.game.demo.object.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.demo.logic.Log;
import com.game.demo.logic.YamlReader;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Obstacle {
    private Rectangle bounds;

    private Texture texture;

    //constructors
    public Obstacle() {}
    public Obstacle(Rectangle bounds, Texture texture){
        this.bounds = bounds;
        this.texture = texture;
    }

// -------------------------------------------------------------------------------------------------------------- //
// === PARSING YAML === //
/*
 * Code for parsing YAML data into Obstacle.java objects
 */

    public static List<Obstacle> getObstacles(File file) throws IOException {
        Log.info("Loading obstacles...");

        List<Obstacle> obstacles = new ArrayList<>();
        ObstacleDataRoot root = YamlReader.mapper.readValue(file, ObstacleDataRoot.class);
        root.getObstacles().forEach(obstacle -> {
            obstacles.add(
                new Obstacle(
                    new Rectangle(
                        Integer.parseInt(obstacle.position.split(" ")[0]),
                        Integer.parseInt(obstacle.position.split(" ")[1]),
                        Integer.parseInt(obstacle.size.split(" ")[0]),
                        Integer.parseInt(obstacle.size.split(" ")[1])
                    ),
                    new Texture(Gdx.files.internal("images/" + obstacle.texture + ".png")))
            );
        });

        return obstacles;
    }

    @Setter
    @Getter
    private static class ObstacleDataRoot {
        @JsonProperty("obstacles")
        private List<ObstacleData> obstacles;
    }

    @Setter
    @Getter
    private static class ObstacleData {
        private String position;
        private String size;
        private String texture;
    }

// -------------------------------------------------------------------------------------------------------------- //

}
