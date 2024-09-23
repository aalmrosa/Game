/* 23/09/2024 - Gust
 * Class that contains code universal to all characters within the core. If it is player specific code,
 * refer to Player.java.
 * Last updated: 23/09/2024 - Gust
 */

package com.game.demo.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.game.demo.object.obstacles.Obstacle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Character {
    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;

    public Character(float x, float y, float width, float height, Texture texture){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.speed = 120;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

// -------------------------------------------------------------------------------------------------------------- //
// === CHARACTER MOVEMENT === //
/*
 * May create new file logic.Collision.java to store collision related code
 */

    //movement specific fields
    private float speed;
    private float xSpeed;
    private float ySpeed;

    /**
     * Moves the character that calls this method towards the given x and y values if no collision with an obstacle
     * within the level happens. If collision is detected, stops the character exactly at obstacle bounds.
     * @param obstacles all obstacles a character can collide with within the level
     * @param x position the character wants to move towards
     * @param y position the character wants to move towards
     */
    public void move(List<Obstacle> obstacles, float x, float y){
        this.xSpeed = x * speed * Gdx.graphics.getDeltaTime();
        this.ySpeed = y * speed * Gdx.graphics.getDeltaTime();

        this.x = this.x + xSpeed;
        this.y = this.y + ySpeed;

        //check if colliding
        for (Obstacle obstacle : obstacles) {
            if (checkCollision(obstacle)) {
                checkCollisionDirection(obstacle);
                return;
            }
        }
    }

    /**
     * Collision is only detected if all the following statements are true:
     * <ol>
     *     <li>the right side of the character is to the right of left side of the obstacle and</li>
     *     <li>the left side of the character is to the left than the right side of the obstacle</li>
     * </ol>
     * (results in horizontal overlap of boundaries)
     * <ol>
     *     <li>the top side of the character is above of the bottom side of the obstacle and</li>
     *     <li>the bottom side of the character is below the top side of the obstacle</li>
     * </ol>
     * (results in vertical overlap of boundaries) <br>
     * In an n-dimensional universe, if two entities overlap in all existent axis, then both entities are colliding.
     * @param obstacle entity that is being checked for collision
     * @return {@code true} if there was a collision detected, {@code false} otherwise
     */
    private boolean checkCollision(Obstacle obstacle) {
        return
            //horizontal collision
            this.x + this.width > obstacle.getBounds().x &&
            this.x < obstacle.getBounds().x + obstacle.getBounds().width &&
            //vertical collision
            this.y + this.height > obstacle.getBounds().y &&
            this.y < obstacle.getBounds().y + obstacle.getBounds().height;
    }

    public void checkCollisionDirection(Obstacle obstacle) {
        //horizontal collision
        if (this.xSpeed > 0) { //going right
            this.x = obstacle.getBounds().x - this.width;
            return;
        }
        if (this.xSpeed < 0){ //going left
            this.x = obstacle.getBounds().x + obstacle.getBounds().width;
            return;
        }

        //vertical collision
        if (this.ySpeed > 0) { //going up
            this.y = obstacle.getBounds().y - this.height;
            return;
        }
        if (this.ySpeed < 0) { //going down
            this.y = obstacle.getBounds().y + obstacle.getBounds().height;
        }
    }

// -------------------------------------------------------------------------------------------------------------- //

}
