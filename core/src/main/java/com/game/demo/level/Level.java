/**
 * Class created with the intent to process all different levels as parameters,
 * avoiding the need to create multiple screens individualized for each level
 * of the core.
 * Screens may only be needed in the case where they display unique functionality,
 * such as menu screen or a mini-core.
 */

package com.game.demo.level;

import com.badlogic.gdx.Screen;
import com.game.demo.Core;

public class Level implements Screen {
    public LevelDetails levelDetails;

    public Level(Core core){
        levelDetails = new LevelDetails(core);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        levelDetails.drawAll();
    }

    @Override
    public void resize(int width, int height) {
        levelDetails.getCore().fitViewport.update(width, height);
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
