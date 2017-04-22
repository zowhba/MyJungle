package com.myjungle.game.ui.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.screen.StageScreen;

/**
 * Created by LeeWoochan on 2017-02-08.
 */

public class StageButtonIcon extends Image {
    StageScreen STAGESCREEN;
    public StageButtonIcon(Texture texture,StageScreen stageScreen) {
        super(texture);
        STAGESCREEN = stageScreen;
        this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                STAGESCREEN.MJ.allEffectSound.playSound(STAGESCREEN.MJ.allEffectSound.foodSound);
                STAGESCREEN.MJ.allMusic.homeMusic.stop();
                STAGESCREEN.MJ.stageInfo.setStage(Integer.parseInt(StageButtonIcon.this.getName()));
                STAGESCREEN.dispose();
                STAGESCREEN.MJ.setScreen(new GameScreen(STAGESCREEN.MJ));
                return true;
            }
        });
    }
}
