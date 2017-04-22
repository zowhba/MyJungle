package com.myjungle.game.ui.game;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.HomeScreen;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-13.
 */

public class GameResultDialog extends Dialog {
    GameStage GAMESTAGE;
    public GameResultDialog(String title, Skin skin, GameStage gameStage) {
        super(title, skin);
        GAMESTAGE = gameStage;
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);
        this.setVisible(false);
    }

    {

    }

    @Override
    protected void result(Object object) {
        super.result(object);
        if(object=="EXIT"){
            GAMESTAGE.GAMESCREEN.MJ.allMusic.gameMusic.stop();
            GAMESTAGE.GAMESCREEN.MJ.allMusic.homeMusic.setLooping(true);
            GAMESTAGE.GAMESCREEN.MJ.allMusic.playMusic(GAMESTAGE.GAMESCREEN.MJ.allMusic.homeMusic);
            GAMESTAGE.GAMESCREEN.dispose();
            GAMESTAGE.GAMESCREEN.MJ.setScreen(new HomeScreen(GAMESTAGE.GAMESCREEN.MJ,true));
        }
    }
}
