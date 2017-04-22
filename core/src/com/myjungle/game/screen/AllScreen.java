package com.myjungle.game.screen;

import com.badlogic.gdx.Screen;
import com.myjungle.game.MyJungle;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public abstract class AllScreen implements Screen {
    public MyJungle MJ;

    public AllScreen(MyJungle mj)
    {
        MJ = mj;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
