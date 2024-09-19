package com.game.demo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.demo.Core;
import com.game.demo.level.Level;

public class MainMenu implements Screen {
    private final Core game;

    public MainMenu(Core game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Press [SPACE] to start", 8, 160-16);
        game.batch.end();

        game.actionKeys.checkInput();

        if(Gdx.input.isKeyJustPressed(game.actionKeys.interact)){
            newGame();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    private void newGame(){
        game.setScreen(new Level(game));
        game.level = new Level(game);
        dispose();
    }
}
