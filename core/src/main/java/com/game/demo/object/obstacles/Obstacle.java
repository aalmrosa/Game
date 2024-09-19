package com.game.demo.object.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.demo.logic.Log;
import com.game.demo.logic.YamlReader;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Obstacle {

    private Vector2 position;

    private Rectangle bounds;

    private Texture texture;

    //constructors
    public Obstacle() {}
    public Obstacle(String position, String size, String texture) {
        Log.info(position);
        this.position = new Vector2(Integer.parseInt(position.split(" ")[0]), Integer.parseInt(position.split(" ")[1]));
        this.bounds = new Rectangle(this.position.x, this.position.y, Integer.parseInt(size.split(" ")[0]), Integer.parseInt(size.split(" ")[1]));
        this.texture = new Texture(Gdx.files.internal("files/images/" + texture + ".png"));
    }

    //methods
    public static List<Obstacle> getObstacles(File file) throws Exception {
        Log.info("Getting obstacles");

        List<Obstacle> obstacles = new ArrayList<>();
        ObstacleDataRoot root = YamlReader.mapper.readValue(file, ObstacleDataRoot.class);
        root.getObstacles().forEach(obstacle -> {
            obstacles.add(new Obstacle(obstacle.getPosition(), obstacle.getSize(), obstacle.getTexture()));
        });

        return obstacles;
    }

    public static void drawObstacles(Batch batch, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            batch.draw(obstacle.texture, obstacle.bounds.x, obstacle.bounds.y, obstacle.bounds.width, obstacle.bounds.height);
        }
    }

    //nested classes
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
}
