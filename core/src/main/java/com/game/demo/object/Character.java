package com.game.demo.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Character {
    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;
    private int speed;

    public Character() {}
    public Character(float x, float y, float width, float height, Texture texture){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.speed = 120;
    }
    public static Character newPlayerCharacter() {
        return new Character(32, 32, 32, 32, new Texture(Gdx.files.internal("files/images/hitbox-player.png")));
    }

    public void move(){

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Setter
    @Getter
    private static class PlayerYaml {
        private String x;
        private String y;
        private String width;
        private String height;
        private String texture;
    }
}
