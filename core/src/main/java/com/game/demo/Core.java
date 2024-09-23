/* 23/09/2024 - Gust
 * Class that contains the core codebase of the core.
 * Only has data that is unique throughout the whole application at the same time.
 * Last updated: 23/09/2024 - Gust
 */

package com.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.demo.logic.ActionKeys;
import com.game.demo.logic.Log;
import com.game.demo.logic.YamlReader;
import com.game.demo.level.Level;
import com.game.demo.object.Player;
import com.game.demo.screen.MainMenu;

/**
 * Core code of the application. Has data that is unique throughout the whole application at once.
 */
public class Core extends Game {
    public static final String TITLE = "Alpha";
    public static final String VERSION = "v0.1";

    public Batch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public FitViewport fitViewport;
    public Player player;
    public ActionKeys actionKeys;

    public Level level;

    /**
     * Called at application startup. Should only be instantiated once.
     */
    @Override
    public void create() {
        Log.build();
        Log.info(TITLE + " " + VERSION);
        Log.info("Initializing Game...");

        //tools
        YamlReader.build();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        //camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 320, 320);
        this.fitViewport = new FitViewport(320, 320, this.camera);

        //player
        this.player = new Player(this);
        this.actionKeys = ActionKeys.getKeys();
        this.actionKeys.setCore(this);

        Log.info("Game Started");

        setScreen(new MainMenu(this));
    }
}
