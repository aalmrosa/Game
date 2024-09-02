package com.game.demo.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Character {
    private Rectangle characterBounds;
    private Vector2 characterPosition;
    private Texture characterTexture;
    private int characterSpeed;

    public Character(){
        this.characterBounds = new Rectangle().setWidth(16).setHeight(16);
        this.characterTexture = new Texture(Gdx.files.internal("hitbox-npc.png"));
        this.characterPosition = new Vector2(0, 0);
        this.characterSpeed = 80;
    }
    public Character(Vector2 characterPosition, Texture characterTexture){
        this.characterBounds = new Rectangle().setWidth(16).setHeight(16);
        this.characterPosition = characterPosition;
        this.characterTexture = characterTexture;
        this.characterSpeed = 80;
    }

    public Direction move(){
        return new Direction(this);
    }
    public static class Direction{
        private final Character character;

        public Direction(Character character){
            this.character = character;
        }

        public void north(){
            character.setCharacterPosition(new Vector2(character.getCharacterPosition().x, character.getCharacterPosition().y + (character.getCharacterSpeed() * Gdx.graphics.getDeltaTime())));
        }
        public void south(){
            character.setCharacterPosition(new Vector2(character.getCharacterPosition().x, character.getCharacterPosition().y - (character.getCharacterSpeed() * Gdx.graphics.getDeltaTime())));
        }
        public void east(){
            character.setCharacterPosition(new Vector2(character.getCharacterPosition().x + (character.getCharacterSpeed() * Gdx.graphics.getDeltaTime()), character.getCharacterPosition().y));
        }
        public void west(){
            character.setCharacterPosition(new Vector2(character.getCharacterPosition().x - (character.getCharacterSpeed() * Gdx.graphics.getDeltaTime()), character.getCharacterPosition().y));
        }
    }
}
