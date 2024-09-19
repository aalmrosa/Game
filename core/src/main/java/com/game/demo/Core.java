package com.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.demo.logic.ActionKeys;
import com.game.demo.logic.Log;
import com.game.demo.logic.YamlReader;
import com.game.demo.object.Character;
import com.game.demo.level.Level;
import com.game.demo.screen.MainMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    public Batch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public FitViewport viewport;
    public Character player;
    public ActionKeys actionKeys;

    public Level level;

    @Override
    public void create() {
        Log.build();
        Log.info("Initializing Game...");

        //tools
        YamlReader.build();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        //camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 320, 320);
        this.viewport = new FitViewport(320, 320, this.camera);

        //player
        this.player = Character.newPlayerCharacter();
        this.actionKeys = ActionKeys.getKeys();

        Log.info("Game Started");

        setScreen(new MainMenu(this));
    }
}
