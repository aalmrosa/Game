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
    private Texture characterTexture;
    private Vector2 characterPosition;
    private int characterSpeed;

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
