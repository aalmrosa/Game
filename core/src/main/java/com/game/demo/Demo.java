package com.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Log logger;

    @Override
    public void create() {
        this.logger = new Log();
        logger.info("Initializing DEMO");
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 160, 160);
        this.player = new Character();
        this.actionKeys = new ActionKeys(this);
        logger.info("Setting Main-Menu Screen");
        setScreen(new MainMenu(this));
    }

    public void updateStart(){
        ScreenUtils.clear(0, 0, 0.2f, 1);
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
    }

    public void updateEnd(){
        this.batch.end();
        //check for input
    }
}
