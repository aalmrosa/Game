package com.game.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.demo.logic.ActionKeys;
import com.game.demo.logic.Log;
import com.game.demo.logic.YamlReader;
import com.game.demo.object.Character;
import com.game.demo.level.Level;
import com.game.demo.screen.MainMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Demo extends Game {
    public OrthographicCamera camera;
    public Batch batch;
    public BitmapFont font;
    public Character player;
    public ActionKeys actionKeys;

    public Level level;

    @Override
    public void create() {
        Log.build();
        Log.info("Initializing Game Demo");

        YamlReader.configure();

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 320, 320);
        this.player = Character.newPlayerCharacter();
        this.actionKeys = new ActionKeys();
        setScreen(new MainMenu(this));
    }
}
