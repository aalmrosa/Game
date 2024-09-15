package com.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.demo.logic.ActionKeys;
import com.game.demo.logic.Log;
import com.game.demo.object.Character;
import com.game.demo.screen.MainMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Demo extends Game {
    public OrthographicCamera camera;
    public Batch batch;
    public BitmapFont font;
    public Character player;
    public ActionKeys actionKeys;

    public boolean paused;

    @Override
    public void create() {
        Log.buildLogger();
        Log.info("Initializing Game Demo");

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 160, 160);
        this.player = new Character(
            new Vector2(16, 16),
            new Texture(Gdx.files.internal("hitbox-player.png"))
        );
        this.actionKeys = new ActionKeys(this);
        setScreen(new MainMenu(this));
    }

    public void updateStart(){
        ScreenUtils.clear(0, 0, 0.2f, 1);
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
    }

    public void updateStart(Character player){
        updateStart();
        this.batch.draw(player.getCharacterTexture(), player.getCharacterPosition().x, player.getCharacterPosition().y);
    }

    public void updateEnd(){
        this.batch.end();
        //check for input
        actionKeys.checkInput();
    }

    public void pause(){

    }

    public void resume(){

    }
}
