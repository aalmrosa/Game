/**
 * Class created with the intent to process all different levels as parameters,
 * avoiding the need to create multiple screens individualized for each level
 * of the game.
 * Screens may only be needed in the case where they display unique functionality,
 * such as menu screen or a mini-game.
 */

package com.game.demo.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.demo.Core;
import com.game.demo.logic.Log;
import com.game.demo.object.obstacles.Obstacle;

public class Level implements Screen {
    private Core game;

    private String currentLevel;

    public LevelDetails levelDetails;

    private Stage background;
    private Stage foreground;

    public Level(Core game){
        this.game = game;
        currentLevel = "main-menu";

        Log.info("Loading level: " + currentLevel);

        levelDetails = LevelDetails.renderCurrentLevel(currentLevel);
    }

    @Override
    public void show() {
        background = new Stage(game.extendViewport);
        foreground = new Stage(game.fitViewport);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        background.getViewport().apply();

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        Obstacle.drawObstacles(game.batch, levelDetails.getObstacles());
        game.batch.draw(game.player.getTexture(), game.player.getX(), game.player.getY(), game.player.getWidth(), game.player.getHeight());
        game.font.draw(game.batch,
            "PLAYER" +
                "\nx: " + game.player.getX() +
                "\ny: " + game.player.getY() +
                "\nxSpeed: " + game.player.getXSpeed() +
                "\nySpeed: " + game.player.getYSpeed() +
                "\n" +
                "\nSCREEN" +
                "\nwidth: " + Gdx.graphics.getWidth() +
                "\nheight: " + Gdx.graphics.getHeight() +
                "\n",
            4, 320-4
        );
        game.batch.end();

        foreground.getViewport().apply();
        foreground.act();
        foreground.draw();

        game.actionKeys.checkInput();
    }

    @Override
    public void resize(int width, int height) {
        game.fitViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
