package com.myjungle.game.ui.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by LeeWoochan on 2017-02-08.
 */

public class GameUnitIcon extends ImageButton {
    public String type;
    GameStage GAMESTAGE;

    public GameUnitIcon(Skin skin,String styleName, GameStage gameStage){
        super(skin,styleName);
        GAMESTAGE = gameStage;
        this.addListener(new ChangeListener(){
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                GAMESTAGE.GAMESCREEN.MJ.allEffectSound.playSound(GAMESTAGE.GAMESCREEN.MJ.allEffectSound.foodSound);
                if(GAMESTAGE.unitButton[Integer.parseInt(GameUnitIcon.this.getName())].isChecked()) {
                    for (int i = 0; i < 6; i++) {
                        if(GAMESTAGE.GAMESCREEN.MJ.userUnitSettingInfo.unitInfo[Integer.parseInt(actor.getName())] == null){
                            GAMESTAGE.unitButton[Integer.parseInt(actor.getName())].setChecked(false);
                        }
                        if (i != Integer.parseInt(actor.getName())) {
                            GAMESTAGE.unitButton[i].setChecked(false);
                        }
                    }
                }
            }
        });
    }
}
