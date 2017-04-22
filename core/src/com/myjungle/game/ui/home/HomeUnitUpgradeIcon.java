package com.myjungle.game.ui.home;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-22.
 */

public class HomeUnitUpgradeIcon extends Image {
    HomeUnitTap HOMEUNITTAP;
    int num;
    public HomeUnitUpgradeIcon(Texture texture, HomeUnitTap homeUnitTap, int i){
        super(texture);
        HOMEUNITTAP = homeUnitTap;
        this.num = i;
        this.setBounds(340,210,600,300);
        this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMEUNITTAP.HOMESCREEN.MJ.allEffectSound.playSound(HOMEUNITTAP.HOMESCREEN.MJ.allEffectSound.foodSound);
                if(UserInfo.userUnitInfo[HOMEUNITTAP.preViewUnit.unitNumber].stat[HomeUnitUpgradeIcon.this.num]>9){
                    HOMEUNITTAP.HOMESCREEN.stage.addActor(new HomeWarningDialog("알림",HOMEUNITTAP.HOMESCREEN.homeSkin,HOMEUNITTAP.HOMESCREEN).text("강화 최대치 입니다!"));
                }else {
                    HOMEUNITTAP.upgradeNum = HomeUnitUpgradeIcon.this.num;
                    HOMEUNITTAP.HOMESCREEN.updateUnitUpgradeDialog(HomeUnitUpgradeIcon.this.num);
                }
                return true;
            }
        });
    }
}
