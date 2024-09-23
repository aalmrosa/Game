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
public class Door {
    private Rectangle bounds;
    private Texture texture;
    private int id;
    private String destinationLevel;
    private Vector2 destinationPos;
    private String facing;

    public Door (Rectangle bounds, Texture texture, int id, String destinationLevel, String destinationPos, String facing) {
        this.bounds = bounds;
        this.texture = texture;
        this.id = id;
        this.destinationLevel = destinationLevel;
        this.destinationPos = new Vector2(
            Integer.parseInt(destinationPos.split(" ")[0]),
            Integer.parseInt(destinationPos.split(" ")[1])
        );
        this.facing = facing;
    }

// -------------------------------------------------------------------------------------------------------------- //
// === PARSING YAML === //
/*
     * Code for parsing YAML data into Obstacle.java objects
     */

    public static List<Door> getDoors (File file) throws IOException {
        Log.info("Loading doors...");

        List<Door> doors = new ArrayList<>();
        DoorDataRoot root = YamlReader.mapper.readValue(file, DoorDataRoot.class);
        root.getDoors().forEach(door -> {
            doors.add(
                new Door(
                    new Rectangle(
                        Integer.parseInt(door.position.split(" ")[0]),
                        Integer.parseInt(door.position.split(" ")[1]),
                        Integer.parseInt(door.size.split(" ")[0]),
                        Integer.parseInt(door.size.split(" ")[1])
                    ),
                    new Texture(Gdx.files.internal("images/" + door.texture + ".png")),
                    Integer.parseInt(door.id),
                    door.destinationLevel,
                    door.destinationPos,
                    door.facing
                )
            );
        });

        return doors;
    }

    @Setter
    @Getter
    private static class DoorDataRoot {
        @JsonProperty("doors")
        private List<DoorData> doors;
    }

    @Setter
    @Getter
    private static class DoorData {
        private String position;
        private String size;
        private String texture;
        private String id;
        private String destinationLevel;
        private String destinationPos;
        private String facing;
    }

// -------------------------------------------------------------------------------------------------------------- //

}
