package com.myjungle.game.ui.home;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.HomeScreen;
import com.myjungle.game.user.UserInfo;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-25.
 */

public class HomeUnitPurchaseDialog extends Dialog {
    HomeScreen HOMESCREEN;
    Skin skin;
    public HomeUnitPurchaseDialog(String title, Skin skin, HomeScreen homeScreen) {
        super(title, skin);
        HOMESCREEN = homeScreen;
        this.skin = skin;
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);
        this.text("유닛을 구매 하시겠습니까?\n필요 골드 " + HOMESCREEN.homeUnitTap.preViewUnit.purchaseGold);
        this.button("네","YES");
        this.button("아니오","NO");
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
        if(object=="YES"){
            if(UserInfo.gold >=HOMESCREEN.homeUnitTap.preViewUnit.purchaseGold){
                UserInfo.gold -=HOMESCREEN.homeUnitTap.preViewUnit.purchaseGold;
                UserInfo.userUnitInfo[HOMESCREEN.homeUnitTap.preViewUnit.unitNumber].use = 1;
                if (HOMESCREEN.homeUnitTap.useNum < 6) {
                    HOMESCREEN.homeUnitTap.preViewHomeUnitIcon.use = true;
                    HOMESCREEN.homeUnitTap.useNum++;
                    HOMESCREEN.homeUnitTap.addUseTable(HOMESCREEN.homeUnitTap.preViewHomeUnitIcon);
                    HOMESCREEN.homeUnitTap.refreshTable();
                } else {
                    /////
                    /////
                    /////
                }
            } else{
                notEnoughGold();
            }
            HOMESCREEN.goldLabel.setText(UserInfo.gold + " G");
            HOMESCREEN.homeUnitTap.updateUnitInfoLabel();
            HOMESCREEN.saveFile();
        } else if(object=="none"){
            //
        }
    }
}
