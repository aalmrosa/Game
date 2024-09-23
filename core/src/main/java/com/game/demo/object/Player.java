package com.game.demo.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.demo.Core;
import com.game.demo.logic.Log;
import com.game.demo.object.obstacles.Door;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private final Core core;

    private final Character character;
    private Fov fov;

    public Player(Core core) {
        this.core = core;
        Log.info("Creating new player character");
        this.character = new Character(32, 32, 32, 32, new Texture(Gdx.files.internal("images/hitbox-player.png")));
        this.fov = new Fov(this, new Rectangle(64, 32, 32 ,32));
    }

    public void interact() {
        for (Door door : core.level.levelDetails.getDoors()) {
            if(fov.bounds.overlaps(door.getBounds())) {
                core.level.levelDetails.changeLevels(door.getDestinationLevel(), door.getDestinationPos(), door.getFacing());
                return;
            }
        }
    }

    public void setPosition(Vector2 position, String facing) {
        character.setX(position.x);
        character.setY(position.y);

        switch (facing) {
            case "north":
                fov.setNorth();
                break;
            case "south":
                fov.setSouth();
                break;
            case "east":
                fov.setEast();
                break;
            case "west":
                fov.setWest();
        }
    }

    public void drawPlayer(Batch batch) {
        batch.draw(character.getTexture(), character.getX(), character.getY(), character.getWidth(), character.getHeight());
    }
    public void drawPlayerFov(Batch batch) {
        batch.draw(fov.getTexture(), fov.getBounds().x, fov.getBounds().y, fov.getBounds().width, fov.getBounds().height);
    }

    @Setter
    @Getter
    public static class Fov {
        private Player player;
        private Rectangle bounds;
        private Texture texture;

        public Fov(Player player, Rectangle bounds) {
            this.player = player;
            this.bounds = bounds;
            this.texture = new Texture(Gdx.files.internal("images/hitbox-npc.png"));
        }

        public void setNorth() {
            bounds = new Rectangle(player.character.getX(), player.character.getY() + player.character.getHeight(), bounds.width, bounds.height);
        }
        public void setSouth() {
            bounds = new Rectangle(player.character.getX(), player.character.getY() - player.character.getHeight(), bounds.width, bounds.height);
        }
        public void setEast() {
            bounds = new Rectangle(player.character.getX() + player.character.getWidth(), player.character.getY(), bounds.width, bounds.height);
        }
        public void setWest() {
            bounds = new Rectangle(player.character.getX() - player.character.getWidth(), player.character.getY(), bounds.width, bounds.height);
        }
    }
}
