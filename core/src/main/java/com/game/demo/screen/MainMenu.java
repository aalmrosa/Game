package com.game.demo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.game.demo.Demo;
import com.game.demo.level.Start;

public class MainMenu implements Screen {
    private final Demo game;

    public MainMenu(Demo game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.logger.info("Setting Main-Menu Screen");
    }

    @Override
    public void render(float delta) {
        game.updateStart();
        game.font.draw(game.batch, "Press [SPACE] to start", 8, 160-16);
        game.updateEnd();

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
        game.logger.info("Starting new game");
        game.setScreen(new Start(game));
        dispose();
    }
}
