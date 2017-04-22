package com.myjungle.game.ui.home;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myjungle.game.animation.UnitAnimation;
import com.myjungle.game.screen.HomeScreen;
import com.myjungle.game.screen.StageScreen;
import com.myjungle.game.unit.AllUnit;
import com.myjungle.game.user.UserInfo;
import com.myjungle.game.user.UserUnitInfo;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-14.
 */

public class HomeUnitTap extends Table {
    public HomeScreen HOMESCREEN;

    Texture startIconTexture = new Texture("homeImage/start.png");

    Texture unitUseTexture = new Texture("gameImage/unitUse.png");
    Texture unitNotUseTexture = new Texture("gameImage/unitNotUse.png");

    Texture unitUpgradeTexture = new Texture("homeImage/upgrade.png");


    public HomeUnitIcon unitIcon[];

    public Image startImage;

    public Image unitUseImage;
    public Image unitNotUseImage;

    public Label unitTypeInfoLabel;

    public Label unitInfoLabel[] = new Label[4];

    public Label unitNumInfoLabel[] = new Label[4];

    public HomeUnitUpgradeIcon homeUnitUpgradeIcon[] = new HomeUnitUpgradeIcon[4];

    public Table unitTapLeftTable;
    public Table unitTapRightTable;

    public Table unitListTable;
    public Table unitUseTable;
    public Table unitInfoTable;
    public Table unitCheckTable;

    public ScrollPane unitListScrollPane;

    public Container unitUseContainer;
    public Container unitInfoContainer;
    public Container unitCheckContainer;
    public Container unitListContainer;


    public int useNum = 0;

    public int upgradeNum = -1;

    public AllUnit preViewUnit;

    public UserUnitInfo preUnitStat;

    public HomeUnitIcon preViewHomeUnitIcon;

    public Image preViewUnitImage = new Image();

    public UnitAnimation unitAnimation;

    public boolean checkUnit = false;

    public boolean deleteCheck = false;

    public HomeUnitTap(HomeScreen homeScreen){
        HOMESCREEN = homeScreen;

        unitAnimation = new UnitAnimation(this);

        unitIcon = new HomeUnitIcon[HOMESCREEN.MJ.unitMountsInfo.playerUnits];

        for(int i=0;i<unitIcon.length;i++){
            unitIcon[i] = new HomeUnitIcon(HOMESCREEN.homeSkin.unitIconTexture[i],this,HOMESCREEN.MJ.convert.convertPlayerUnitNumToType(i), UserInfo.userUnitInfo[i], UserInfo.playerUnit[i],i);
        }

        preViewUnit = unitIcon[0].unit;

        preUnitStat = unitIcon[0].userUnitInfo;

        preViewHomeUnitIcon = unitIcon[0];


        startImage = new Image(startIconTexture);
        startImage.setBounds(1024,0,256,64);

        unitListTable = new Table();
        unitListTable.setName("unitListTable");
        //unitListTable.setSize(1280,2000);
        //table.setFillParent(true);


        for(int i=0;i<unitIcon.length;i++){
            addListTable(unitIcon[i]);
        }
        unitListTable.add().row();
        unitListTable.add().size(32);
        unitListTable.add().row();


        unitListScrollPane = new ScrollPane(unitListTable);

        unitListContainer = new Container(unitListScrollPane);
        unitListContainer.prefSize(896,285);
        unitListContainer.setBackground(HOMESCREEN.homeSkin.getDrawable("unitListBackground"));

        unitUseTable = new Table();
        unitUseTable.setName("unitUseTable");

        for(int i=0;i<6;i++) {
            unitUseTable.add().size(8);
            unitUseTable.add().size(128);
            unitUseTable.add().size(8);
        }
        for(int i=1;i<=6;i++) {
            unitUseTable.getCells().get(3 * i - 1 - 1).clearActor();
        }

        unitUseContainer = new Container(unitUseTable);



        unitTypeInfoLabel = new Label(preViewUnit.type+"",HOMESCREEN.homeSkin);
        unitTypeInfoLabel.setColor(Color.BLACK);
        unitTypeInfoLabel.setFontScale(1);



        for(int i=0;i<homeUnitUpgradeIcon.length;i++){
            homeUnitUpgradeIcon[i] = new HomeUnitUpgradeIcon(unitUpgradeTexture,this,i);
        }

        unitInfoLabel[0] = new Label("체력",HOMESCREEN.homeSkin);
        unitInfoLabel[1] = new Label("공격력",HOMESCREEN.homeSkin);
        unitInfoLabel[2] = new Label("스피드",HOMESCREEN.homeSkin);
        unitInfoLabel[3] = new Label("공격속도",HOMESCREEN.homeSkin);

        for(int i=0;i<unitInfoLabel.length;i++){
            unitInfoLabel[i].setAlignment(center);
            unitInfoLabel[i].setColor(Color.BLUE);
            unitInfoLabel[i].setFontScale(1);
        }
        unitNumInfoLabel[0] = new Label((int)(preViewUnit.hp*Math.pow(1.1,preUnitStat.stat[0]))+" ",HOMESCREEN.homeSkin);
        unitNumInfoLabel[1] = new Label((int)(preViewUnit.damage*Math.pow(1.1,preUnitStat.stat[1]))+" ",HOMESCREEN.homeSkin);
        unitNumInfoLabel[2] = new Label((int)(preViewUnit.speed*Math.pow(1.1,preUnitStat.stat[2]))+" ",HOMESCREEN.homeSkin);
        unitNumInfoLabel[3] = new Label(Math.round((preViewUnit.attackCooltimeMax/Math.pow(1.05,preUnitStat.stat[3]))*100f)/100f+" ",HOMESCREEN.homeSkin);

        for(int i=0;i<unitNumInfoLabel.length;i++){
            unitNumInfoLabel[i].setAlignment(center);
            unitNumInfoLabel[i].setColor(Color.PINK);
            unitNumInfoLabel[i].setFontScale(1);
        }
        unitInfoTable = new Table();
        unitInfoTable.setName("unitInfoTable");
        unitInfoTable.add().size(128).colspan(2).row();
        unitInfoTable.add(unitTypeInfoLabel).colspan(2).space(4).row();
        unitInfoTable.add().size(30).colspan(2).row();

        for(int i=0;i<unitInfoLabel.length;i++){
            unitInfoTable.add(unitInfoLabel[i]).colspan(2).size(128,32).space(4).row();
            unitInfoTable.add(unitNumInfoLabel[i]).size(128,32).space(4);
            unitInfoTable.add(homeUnitUpgradeIcon[i]).space(4).row();
        }


        unitInfoContainer = new Container(unitInfoTable);
        unitInfoContainer.setBackground(HOMESCREEN.homeSkin.getDrawable("unitInfoBackground"));
        unitUseImage = new Image(unitUseTexture);
        unitNotUseImage = new Image(unitNotUseTexture);


        unitCheckTable = new Table();
        unitCheckTable.add().size(160);
        unitCheckTable.add().row();
        unitCheckTable.add(unitUseImage);

        unitCheckContainer = new Container(unitCheckTable);
        unitCheckContainer.setBackground(HOMESCREEN.homeSkin.getDrawable("unitCheckBackground"));
        unitCheckContainer.setBounds(0,0,160,224);
        unitCheckContainer.setVisible(false);


        unitTapLeftTable = new Table();
        unitTapLeftTable.add(unitListContainer).size(896,300).spaceBottom(32);
        unitTapLeftTable.row();
        unitTapLeftTable.add(unitUseContainer).size(896,200);

        unitTapRightTable = new Table();
        unitTapRightTable.add(unitInfoContainer).size(256,532).row();

        this.bottom();
        this.setSize(1280,720);
        this.add(unitTapLeftTable).spaceRight(32).spaceBottom(32);
        this.add(unitTapRightTable).spaceBottom(32);
        this.add().row();
        this.add(startImage).size(256,64).right().colspan(2).padBottom(16);

        addSavedUseTable();

        unitNotUseImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMESCREEN.MJ.allEffectSound.playSound(HOMESCREEN.MJ.allEffectSound.foodSound);
                preViewHomeUnitIcon.use = false;
                int removeNum = Integer.parseInt(preViewHomeUnitIcon.getName());
                unitUseTable.getCells().get(3*removeNum+1).clearActor();
                HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[removeNum] = -1;
                HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[removeNum] = null;
                HOMESCREEN.MJ.userUnitSettingInfo.unitStat[removeNum] = null;
                useNum--;

                int t = Integer.parseInt(preViewHomeUnitIcon.getName());
                for(int i = Integer.parseInt(preViewHomeUnitIcon.getName())+1;i<6;i++){
                    if(unitUseTable.getCells().get(3*i+1).hasActor()) {
                        Actor actor = unitUseTable.getCells().get(3*i+1).getActor();
                        actor.setName("" + t);
                        HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[t] = HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i];
                        HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i] = -1;
                        HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[t] = HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[i];
                        HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[i] = null;
                        HOMESCREEN.MJ.userUnitSettingInfo.unitStat[t] = HOMESCREEN.MJ.userUnitSettingInfo.unitStat[i];
                        HOMESCREEN.MJ.userUnitSettingInfo.unitStat[i] = null;
                        unitUseTable.getCells().get(3*i+1).clearActor();
                        unitUseTable.getCells().get(3 * (t++) + 1).setActor(actor);
                    }
                }
                refreshTable();
                unitCheckContainer.setVisible(false);
                return true;
            }
        });

        unitUseImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMESCREEN.MJ.allEffectSound.playSound(HOMESCREEN.MJ.allEffectSound.foodSound);
                if(UserInfo.userUnitInfo[preViewUnit.unitNumber].use==1) {
                    if (useNum < 6) {
                        preViewHomeUnitIcon.use = true;
                        useNum++;
                        addUseTable(preViewHomeUnitIcon);
                        refreshTable();
                    } else {
                        /////
                        /////
                        /////
                    }
                } else{
                    HOMESCREEN.stage.addActor(new HomeUnitPurchaseDialog("유닛 구매",HOMESCREEN.homeSkin, HOMESCREEN));
                }
                unitCheckContainer.setVisible(false);
                return true;
            }
        });

        startImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                HOMESCREEN.MJ.allEffectSound.playSound(HOMESCREEN.MJ.allEffectSound.foodSound);
                if(HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[0]!=null){
                    HOMESCREEN.saveFile();
                    HOMESCREEN.dispose();
                    HOMESCREEN.MJ.setScreen(new StageScreen(HOMESCREEN.MJ));
                } else{
                    HOMESCREEN.stage.addActor(new HomeWarningDialog("알림",HOMESCREEN.homeSkin,HOMESCREEN).text("유닛을 먼저 배치하세요!"));
                }
                return true;
            }
        });

        HOMESCREEN.stage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(deleteCheck&&checkUnit==false) {
                    unitCheckContainer.setVisible(false);
                    deleteCheck = false;
                } else if(checkUnit){
                    deleteCheck = true;
                    checkUnit = false;
                }
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
        });
    }

    public void refreshTable(){
        unitListTable.clear();
        for(int i=0;i<unitIcon.length;i++){
            if(!unitIcon[i].use) addListTable(unitIcon[i]);
        }
        unitListTable.add().row();
        unitListTable.add().size(32);
        unitListTable.add().row();
    }

    public void addListTable(HomeUnitIcon icon){
        if(unitListTable.getCells().size==0) {
            unitListTable.add().row();
            unitListTable.add().size(32);
            unitListTable.add().row();
        }
        unitListTable.add().size(32);
        unitListTable.add(icon).size(128);
        unitListTable.add().size(32);
        if(unitListTable.getCells().size%15==0) {
            unitListTable.add().row();
            unitListTable.add().size(32);
            unitListTable.add().row();
        }
    }

    public void addUseTable(HomeUnitIcon homeUnitIcon){
        for(int i=0;i<6;i++) {
            if(!unitUseTable.getCells().get(3*i+1).hasActor()){
                homeUnitIcon.setName("" + i);
                unitUseTable.getCells().get(3 * i + 1).setActor(homeUnitIcon);
                HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i] = homeUnitIcon.unitNumber;
                HOMESCREEN.MJ.userUnitSettingInfo.unitInfo[i] = homeUnitIcon.unit;
                HOMESCREEN.MJ.userUnitSettingInfo.unitStat[i] = homeUnitIcon.userUnitInfo;
                break;
            }
        }
    }

    public void updateUnitInfoLabel(){
        unitTypeInfoLabel.setText(preViewUnit.type+"");
        unitNumInfoLabel[0].setText((int)(preViewUnit.hp*Math.pow(1.1,preUnitStat.stat[0]))+" ");
        unitNumInfoLabel[1].setText((int)(preViewUnit.damage*Math.pow(1.1,preUnitStat.stat[1]))+" ");
        unitNumInfoLabel[2].setText((int)(preViewUnit.speed*Math.pow(1.1,preUnitStat.stat[2]))+" ");
        unitNumInfoLabel[3].setText(Math.round((preViewUnit.attackCooltimeMax/Math.pow(1.05,preUnitStat.stat[3]))*100f)/100f+" ");
    }

    public void addSavedUseTable(){
        for(int i=0;i<6;i++) {
            if(HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i] != -1){
                unitIcon[HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i]].use = true;
                addUseTable(unitIcon[HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i]]);
                HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[useNum] = HOMESCREEN.MJ.userUnitSettingInfo.unitNumber[i];
                useNum++;
            }
        }
        refreshTable();
    }

    public void render(){
        TextureRegion Frame = unitAnimation.updateUnitFrame(preViewUnit);
        preViewUnitImage.remove();
        preViewUnitImage = new Image(Frame);
        unitInfoTable.getCells().get(0).clearActor();
        unitInfoTable.getCells().get(0).setActor(preViewUnitImage);
        unitAnimation.updateStatetime();

        if(unitListScrollPane.isPanning()) unitCheckContainer.setVisible(false);
    }

    public void dispose(){
        startIconTexture.dispose();
        unitUseTexture.dispose();
        unitNotUseTexture.dispose();
        unitUpgradeTexture.dispose();

        unitTapLeftTable.clear();
        unitTapRightTable.clear();

        unitListTable.clear();
        unitUseTable.clear();
        unitInfoTable.clear();
        unitCheckTable.clear();

        unitListScrollPane.clear();

        unitUseContainer.clear();
        unitInfoContainer.clear();
        unitCheckContainer.clear();
        unitListContainer.clear();

        unitAnimation.dispose();
    }
}
