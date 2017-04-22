package com.myjungle.game.ui.home;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-21.
 */

public class HomeCastleUpgradeIcon extends Image {
    HomeCastleTap HOMECASTLETAP;
    int num;
    public HomeCastleUpgradeIcon(Texture texture, HomeCastleTap homeCastleTap, int i){
        super(texture);
        HOMECASTLETAP = homeCastleTap;
        this.num = i;
        this.setBounds(340,210,600,300);
        this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMECASTLETAP.HOMESCREEN.MJ.allEffectSound.playSound(HOMECASTLETAP.HOMESCREEN.MJ.allEffectSound.foodSound);
                if(UserInfo.userCastleInfo[HomeCastleUpgradeIcon.this.num]>9){
                    HOMECASTLETAP.HOMESCREEN.stage.addActor(new HomeWarningDialog("알림",HOMECASTLETAP.HOMESCREEN.homeSkin,HOMECASTLETAP.HOMESCREEN).text("강화 최대치 입니다!"));
                } else{
                    HOMECASTLETAP.upgradeNum = HomeCastleUpgradeIcon.this.num;
                    HOMECASTLETAP.HOMESCREEN.updateCastleUpgradeDialog(HomeCastleUpgradeIcon.this.num);
                }
                return true;
            }
        });
    }
}
