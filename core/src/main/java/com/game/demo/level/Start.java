package com.game.demo.level;

import com.badlogic.gdx.Screen;
import com.game.demo.Demo;

public class Start implements Screen {
    private Demo game;

    public Start(Demo game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.updateStart();
        game.font.draw(game.batch, "Start Screen", 8, 160-16);
        game.updateEnd();
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
}
