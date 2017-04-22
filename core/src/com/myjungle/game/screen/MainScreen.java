package com.myjungle.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.myjungle.game.MyJungle;
import com.myjungle.game.system.MyJungleInfo;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class MainScreen extends AllScreen {
    BitmapFont font;
    Texture background;

    OrthographicCamera camera;

    public MainScreen(MyJungle mj) {
        super(mj);
        MJ = mj;
        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyJungleInfo.V_WIDTH, MyJungleInfo.V_HEIGHT);

        background = new Texture("background.jpg");

        MJ.allMusic.mainMusic.setLooping(true);
        MJ.allMusic.playMusic(MJ.allMusic.mainMusic);
    }

    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MJ.batch.setProjectionMatrix(camera.combined);

        MJ.batch.begin();
        MJ.batch.draw(background, 0, 0);
        MJ.batch.end();

        if (Gdx.input.isTouched()) {
            dispose();
            MJ.setScreen(new HomeScreen(MJ,false));
        }
    }

    @Override
    public void dispose() {
        background.dispose();

        MJ.allMusic.playMusic(MJ.allMusic.homeMusic);
        MJ.allMusic.mainMusic.stop();
        MJ.allMusic.homeMusic.setLooping(true);
    }
}

