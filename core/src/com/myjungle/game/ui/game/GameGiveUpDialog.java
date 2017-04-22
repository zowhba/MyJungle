package com.myjungle.game.ui.game;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.screen.HomeScreen;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-19.
 */

public class GameGiveUpDialog extends Dialog {
    GameScreen GAMESCREEN;
    public GameGiveUpDialog(String title, Skin skin, GameScreen gameScreen) {
        super(title, skin);
        GAMESCREEN = gameScreen;
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);
        this.text("항복 하시겠습니까?");
        this.button("네","YES");
        this.button("아니오","NO");
    }
    {

    }

    @Override
    protected void result(Object object) {
        super.result(object);
        if(object=="YES"){
            GAMESCREEN.MJ.allMusic.gameMusic.stop();
            GAMESCREEN.MJ.allMusic.homeMusic.setLooping(true);
            GAMESCREEN.MJ.allMusic.playMusic(GAMESCREEN.MJ.allMusic.homeMusic);
            GAMESCREEN.dispose();
            GAMESCREEN.MJ.setScreen(new HomeScreen(GAMESCREEN.MJ,true));
        }
        GAMESCREEN.pause = false;
    }
}
