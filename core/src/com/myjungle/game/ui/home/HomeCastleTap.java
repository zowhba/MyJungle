package com.myjungle.game.ui.home;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myjungle.game.screen.HomeScreen;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-14.
 */

public class HomeCastleTap extends Table{
    HomeScreen HOMESCREEN;

    public Table castleTapLeftTable;

    public Texture castleTexture = new Texture("homeImage/castle.png");
    public Texture castleUpgradeTexture = new Texture("homeImage/upgrade.png");

    public Image castleImage;

    public Label castleInfoLabel[] = new Label[6];

    public Label castleNumInfoLabel[] = new Label[6];

    public HomeCastleUpgradeIcon homeCastleUpgradeIcon[] = new HomeCastleUpgradeIcon[6];

    public int upgradeNum = -1;

    public Table castleTapRightTable;

    public Texture skillTexture = new Texture("homeImage/skill.png");

    public Image skillImage = new Image(skillTexture);
    
    public HomeCastleTap(HomeScreen homeScreen){
        HOMESCREEN = homeScreen;

        this.setSize(1280,720);

        castleImage = new Image(castleTexture);


        castleInfoLabel[0] = new Label("사정거리",HOMESCREEN.homeSkin);

        castleInfoLabel[1] = new Label("체력",HOMESCREEN.homeSkin);

        castleInfoLabel[2] = new Label("공격력",HOMESCREEN.homeSkin);

        castleInfoLabel[3] = new Label("공격속도",HOMESCREEN.homeSkin);

        castleInfoLabel[4] = new Label("식량",HOMESCREEN.homeSkin);

        castleInfoLabel[5] = new Label("생산속도",HOMESCREEN.homeSkin);

        for(int i=0;i<castleInfoLabel.length;i++){
            castleInfoLabel[i].setColor(Color.BLUE);
            castleInfoLabel[i].setFontScale(1);
        }

        castleNumInfoLabel[0] = new Label("레벨 " + UserInfo.userCastleInfo[0],HOMESCREEN.homeSkin);

        castleNumInfoLabel[1] = new Label((int)(HOMESCREEN.MJ.castleInfo.castleHp * Math.pow(1.1, UserInfo.userCastleInfo[1])) + "",HOMESCREEN.homeSkin);

        castleNumInfoLabel[2] = new Label((int)(HOMESCREEN.MJ.castleInfo.castleDamage * Math.pow(1.1, UserInfo.userCastleInfo[2])) + "",HOMESCREEN.homeSkin);

        castleNumInfoLabel[3] = new Label(Math.round((HOMESCREEN.MJ.castleInfo.castleCooltime / Math.pow(1.05, UserInfo.userCastleInfo[3]))*100f)/100f + "",HOMESCREEN.homeSkin);

        castleNumInfoLabel[4] = new Label("레벨 " + UserInfo.userCastleInfo[4],HOMESCREEN.homeSkin);

        castleNumInfoLabel[5] = new Label(Math.round((200 / Math.pow(1.1, UserInfo.userCastleInfo[5]))*100f)/100f+" ",HOMESCREEN.homeSkin);

        for(int i=0;i<castleNumInfoLabel.length;i++){
            castleNumInfoLabel[i].setColor(Color.RED);
            castleNumInfoLabel[i].setFontScale(1);
        }

        for(int i=0;i<homeCastleUpgradeIcon.length;i++){
            homeCastleUpgradeIcon[i] = new HomeCastleUpgradeIcon(castleUpgradeTexture,this,i);
        }

        castleTapLeftTable = new Table();
        castleTapLeftTable.add(castleImage).space(32).colspan(3).row();
        for(int i=0;i<4;i++){
            castleTapLeftTable.add(castleInfoLabel[i]).size(250,50).space(32);
            castleTapLeftTable.add(castleNumInfoLabel[i]).size(150,50).space(32);
            castleTapLeftTable.add(homeCastleUpgradeIcon[i]).size(100,50).space(32).row();
        }

        castleTapRightTable = new Table();

        castleTapRightTable.add(skillImage).space(32).colspan(3).row();
        for(int i=4;i<6;i++){
            castleTapRightTable.add(castleInfoLabel[i]).size(250,50).space(32);
            castleTapRightTable.add(castleNumInfoLabel[i]).size(150,50).space(32);
            castleTapRightTable.add(homeCastleUpgradeIcon[i]).size(100,50).space(32).row();
        }
        castleTapRightTable.add().size(50).space(32).row();
        castleTapRightTable.add().size(50).space(32).row();

        this.add(castleTapLeftTable).space(128);
        this.add(castleTapRightTable);
    }

    public void updateCastleInfoLabel(){
        castleNumInfoLabel[0].setText("레벨 " + UserInfo.userCastleInfo[0]);
        castleNumInfoLabel[1].setText((int)(HOMESCREEN.MJ.castleInfo.castleHp * Math.pow(1.1, UserInfo.userCastleInfo[1]))+" ");
        castleNumInfoLabel[2].setText((int)(HOMESCREEN.MJ.castleInfo.castleDamage * Math.pow(1.1, UserInfo.userCastleInfo[2]))+" ");
        castleNumInfoLabel[3].setText(Math.round((HOMESCREEN.MJ.castleInfo.castleCooltime / Math.pow(1.05, UserInfo.userCastleInfo[3]))*100f)/100f+" ");
        castleNumInfoLabel[4].setText("레벨 " + UserInfo.userCastleInfo[4]);
        castleNumInfoLabel[5].setText(Math.round((200 / Math.pow(1.1, UserInfo.userCastleInfo[5]))*100f)/100f+" ");
    }
    public void dispose(){
        castleTexture.dispose();
        castleUpgradeTexture.dispose();
        skillTexture.dispose();

        for(int i=0;i<homeCastleUpgradeIcon.length;i++){
            homeCastleUpgradeIcon[i].clear();
        }
        castleTapRightTable.clear();
    }
}
