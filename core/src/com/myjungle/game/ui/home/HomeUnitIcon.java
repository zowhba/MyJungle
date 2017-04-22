package com.myjungle.game.ui.home;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myjungle.game.unit.AllUnit;
import com.myjungle.game.user.UserUnitInfo;

/**
 * Created by LeeWoochan on 2017-02-05.
 */

public class HomeUnitIcon extends Image {
    HomeUnitTap HOMEUNITTAP;
    public int unitNumber;
    public boolean use = false;
    public String type;
    public AllUnit unit;
    public UserUnitInfo userUnitInfo;

    public HomeUnitIcon(Texture texture, HomeUnitTap homeUnitTap, String type, UserUnitInfo userUnitInfo, AllUnit unit, final int unitNumber){
        super(texture);
        HOMEUNITTAP = homeUnitTap;
        this.type = type;
        this.userUnitInfo = userUnitInfo;
        this.unit = unit;
        this.unitNumber = unitNumber;
        this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMEUNITTAP.checkUnit = true;
                HOMEUNITTAP.preViewUnit = null;
                HOMEUNITTAP.preViewUnit = HomeUnitIcon.this.unit;
                HOMEUNITTAP.preUnitStat = HomeUnitIcon.this.userUnitInfo;
                HOMEUNITTAP.updateUnitInfoLabel();
                HOMEUNITTAP.preViewHomeUnitIcon = HomeUnitIcon.this;
                HOMEUNITTAP.unitCheckContainer.setVisible(true);
                if(HomeUnitIcon.this.getParent().getName()=="unitListTable"){
                    HOMEUNITTAP.unitListScrollPane.setScrollY(HOMEUNITTAP.unitListScrollPane.getMaxY() - HomeUnitIcon.this.getY() + 100);
                    HOMEUNITTAP.unitCheckContainer.setPosition(HomeUnitIcon.this.getX() + 30, HomeUnitIcon.this.getY() + HOMEUNITTAP.unitListScrollPane.getScrollY() - HOMEUNITTAP.unitListScrollPane.getMaxY() + 270);
                }
                else{
                    HOMEUNITTAP.unitCheckContainer.setPosition(HomeUnitIcon.this.getX() + 48, 65);
                }
                if(HomeUnitIcon.this.use){
                    HOMEUNITTAP.unitCheckTable.getCells().get(2).clearActor();
                    HOMEUNITTAP.unitCheckTable.getCells().get(2).setActor(HOMEUNITTAP.unitNotUseImage);
                } else{
                    HOMEUNITTAP.unitCheckTable.getCells().get(2).clearActor();
                    HOMEUNITTAP.unitCheckTable.getCells().get(2).setActor(HOMEUNITTAP.unitUseImage);
                }
                HOMEUNITTAP.HOMESCREEN.MJ.allUnitSound.playerUnitSound[HomeUnitIcon.this.unitNumber].playSound(HOMEUNITTAP.HOMESCREEN.MJ.allUnitSound.playerUnitSound[HomeUnitIcon.this.unitNumber].spawnSound);
                return true;
            }
        });
    }
}
