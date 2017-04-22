package com.myjungle.game.ui.home;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.HomeScreen;
import com.myjungle.game.user.UserInfo;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-10.
 */

public class HomeUnitUpgradeDialog extends Dialog {
    HomeScreen HOMESCREEN;
    Skin skin;
    public HomeUnitUpgradeDialog(String title, Skin skin, HomeScreen homeScreen) {
        super(title, skin);
        HOMESCREEN = homeScreen;
        this.skin = skin;
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);
    }
    {

    }

    public void notEnoughGold(){
        HOMESCREEN.stage.addActor(new Dialog("알림",skin){
            {
                text("골드 부족");
                button("확인");
                this.getTitleLabel().setAlignment(center);
                this.setScale(1);
                this.setBounds(340,210,600,300);
            }
        });
    }

    @Override
    public void result(Object object) {
        super.result(object);
        HOMESCREEN.MJ.allEffectSound.playSound(HOMESCREEN.MJ.allEffectSound.foodSound);
        if(object=="upgrade"){
            for(int i=0;i<HOMESCREEN.homeUnitTap.preUnitStat.stat.length;i++){
                if(HOMESCREEN.homeUnitTap.upgradeNum == i){
                    if(UserInfo.gold >=HOMESCREEN.homeUnitTap.preViewUnit.food*Math.pow(2,HOMESCREEN.homeUnitTap.preUnitStat.stat[i])){
                        UserInfo.gold -= HOMESCREEN.homeUnitTap.preViewUnit.food * Math.pow(2,HOMESCREEN.homeUnitTap.preUnitStat.stat[i]);
                        HOMESCREEN.homeUnitTap.preUnitStat.stat[i] += 1;
                    } else {
                        notEnoughGold();
                    }
                }
            }
            HOMESCREEN.goldLabel.setText(UserInfo.gold + " G");
            HOMESCREEN.homeUnitTap.updateUnitInfoLabel();
            HOMESCREEN.saveFile();
        } else if(object=="none"){
            //
        }
    }
}
