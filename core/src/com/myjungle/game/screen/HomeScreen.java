package com.myjungle.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myjungle.game.MyJungle;
import com.myjungle.game.system.MyJungleInfo;
import com.myjungle.game.ui.home.HomeCastleTap;
import com.myjungle.game.ui.home.HomeCastleUpgradeDialog;
import com.myjungle.game.ui.home.HomeSkin;
import com.myjungle.game.ui.home.HomeUnitTap;
import com.myjungle.game.ui.home.HomeUnitUpgradeDialog;
import com.myjungle.game.user.UserInfo;
import com.myjungle.game.user.UserUnitInfo;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class HomeScreen extends AllScreen {
    public OrthographicCamera camera;

    public Stage stage;

    public Texture castleTapTexture = new Texture("homeImage/castleTap.jpg");
    public Texture unitTapTexture = new Texture("homeImage/unitTap.jpg");

    public Texture unitTapButtonTexture = new Texture("homeImage/unitTapButton.png");
    public Texture castleTapButtonTexture = new Texture("homeImage/castleTapButton.png");

    public HomeSkin homeSkin = new HomeSkin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"),this);

    public Image unitTapIcon;
    public Image castleTapIcon;

    public Label goldLabel;

    int tap = 1;

    public HomeUnitTap homeUnitTap;
    public HomeUnitUpgradeDialog homeUnitUpgradeDialog;

    public HomeCastleTap homeCastleTap;
    public HomeCastleUpgradeDialog homeCastleUpgradeDialog;

    public HomeScreen(MyJungle mj,boolean fromGame) {
        super(mj);
        MJ = mj;

        if(!fromGame){
            if(Gdx.files.local("userData.bin").exists()) {
                loadFile();
            }
        }
        saveFile();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyJungleInfo.V_WIDTH, MyJungleInfo.V_HEIGHT);

        //stage
        stage = new Stage(new StretchViewport(1280,720));
        Gdx.input.setInputProcessor(stage);

        unitTapIcon = new Image(unitTapButtonTexture);
        unitTapIcon.setBounds(0,656,128,64);
        unitTapIcon.setVisible(false);

        castleTapIcon = new Image(castleTapButtonTexture);
        castleTapIcon.setBounds(128,656,128,64);
        castleTapIcon.setVisible(true);

        goldLabel = new Label(UserInfo.gold + " G",homeSkin);
        goldLabel.setColor(Color.WHITE);
        goldLabel.setBounds(1024,656,256,64);
        goldLabel.setFontScale(1);
        goldLabel.setAlignment(center);

        homeUnitTap = new HomeUnitTap(this);
        homeUnitUpgradeDialog = new HomeUnitUpgradeDialog("업그레이드",homeSkin, this);

        homeCastleTap = new HomeCastleTap(this);
        homeCastleUpgradeDialog = new HomeCastleUpgradeDialog("업그레이드",homeSkin, this);
        homeCastleTap.setVisible(false);


        stage.addActor(unitTapIcon);
        stage.addActor(castleTapIcon);
        stage.addActor(goldLabel);

        stage.addActor(homeUnitTap);
        stage.addActor(homeUnitTap.unitCheckContainer);

        stage.addActor(homeCastleTap);

        unitTapIcon.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                MJ.allEffectSound.playSound(MJ.allEffectSound.foodSound);
                tap = 1;
                unitTapIcon.setVisible(false);
                castleTapIcon.setVisible(true);
                homeUnitTap.setVisible(true);
                homeCastleTap.setVisible(false);
                return true;
            }
        });

        castleTapIcon.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                MJ.allEffectSound.playSound(MJ.allEffectSound.foodSound);
                tap = 2;
                unitTapIcon.setVisible(true);
                castleTapIcon.setVisible(false);
                homeUnitTap.setVisible(false);
                homeUnitTap.unitCheckContainer.setVisible(false);
                homeCastleTap.setVisible(true);
                return true;
            }
        });
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MJ.batch.setProjectionMatrix(camera.combined);

        MJ.batch.begin();
        if(tap == 1){
            MJ.batch.draw(unitTapTexture, 0, 0);
        } else if(tap == 2){
            MJ.batch.draw(castleTapTexture, 0, 0);
        }

        MJ.batch.end();

        homeUnitTap.render();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }



    public void saveFile(){
        FileHandle file = Gdx.files.local("userData.bin");
        byte[] bytes = new byte[1000];

        //gold
        intToBytes(bytes,10, UserInfo.gold);

        //0~49 settingInfo
        bytes[0] = (byte) UserInfo.clearStage;

        //50~55 unitSetting
        for(int i=0;i<6;i++){
            bytes[50+i] = (byte)MJ.userUnitSettingInfo.unitNumber[i];
        }


        //100~149 castleInfo
        for(int i=0;i<6;i++){
            bytes[100+i] = (byte) UserInfo.userCastleInfo[i];
        }

        //200~999 unitInfo
        for(int i=0;i<MJ.unitMountsInfo.playerUnits;i++){
            changeUnitByte(bytes,200+(i*10), UserInfo.userUnitInfo[i]);
        }

        file.writeBytes(bytes,false);
    }
    public void loadFile(){

        FileHandle file = Gdx.files.local("userData.bin");
        byte[] bytes = file.readBytes();

        UserInfo.gold = byteToInt(bytes,10);

        //0~49 stageInfo
        UserInfo.clearStage = bytes[0];

        //50~55 unitSetting
        for(int i=0;i<6;i++){
            MJ.userUnitSettingInfo.unitNumber[i] = bytes[50+i];
        }


        //100~149 castleInfo
        for(int i=0;i<6;i++){
            UserInfo.userCastleInfo[i] = (int)bytes[100+i];
        }


        //200~999 unitInfo
        for(int i=0;i<MJ.unitMountsInfo.playerUnits;i++){
            changeByteUnit(bytes,200+(i*10), UserInfo.userUnitInfo[i]);
        }
    }

    public void changeUnitByte(byte[] bytes,int num, UserUnitInfo userUnitInfo){
        bytes[num] = (byte) userUnitInfo.use;
        for(int i=1;i<5;i++) {
            bytes[num + i] = (byte) userUnitInfo.stat[i-1];
        }
    }

    public void changeByteUnit(byte[] bytes,int num,UserUnitInfo userUnitInfo){
        userUnitInfo.use = (int)bytes[num];
        for(int i=1;i<5;i++) {
            userUnitInfo.stat[i-1] = (int)bytes[num + i];
        }
    }

    private int byteToInt(byte[] bytes,int num)
    {
        int newValue = 0;
        newValue |= ( ( bytes[ num ] ) << 24 ) & 0xFF000000;
        newValue |= ( ( bytes[ num+1 ] ) << 16 ) & 0xFF0000;
        newValue |= ( ( bytes[ num+2 ] ) << 8 ) & 0xFF00;
        newValue |= ( bytes[ num+3 ] ) & 0xFF;
        return newValue;
    }

    public void intToBytes( byte[] bytes,int num,int i )
    {
        bytes[num] = ( byte ) ( ( i >>> 24 ) & 0xFF );
        bytes[num+1] = ( byte ) ( ( i >>> 16 ) & 0xFF );
        bytes[num+2] = ( byte ) ( ( i >>> 8 ) & 0xFF );
        bytes[num+3] = ( byte ) ( ( i >>> 0 ) & 0xFF );
    }

    public void updateUnitUpgradeDialog(int num){
        homeUnitUpgradeDialog = new HomeUnitUpgradeDialog("업그레이드",homeSkin,this);
        homeUnitUpgradeDialog.text("정말 업그레이드 하시겠습니까?\n" + "필요 골드 " + homeUnitTap.preViewUnit.food*(int)Math.pow(2, UserInfo.userUnitInfo[homeUnitTap.preViewUnit.unitNumber].stat[num]));
        homeUnitUpgradeDialog.button("네","upgrade");
        homeUnitUpgradeDialog.button("아니오","none");
        stage.addActor(homeUnitUpgradeDialog);
    }

    public void updateCastleUpgradeDialog(int num){
        homeCastleUpgradeDialog = new HomeCastleUpgradeDialog("업그레이드",homeSkin,this);
        homeCastleUpgradeDialog.text("정말 업그레이드 하시겠습니까?\n" + "필요 골드 " + UserInfo.userCastleGold[num]*(int)Math.pow(2, UserInfo.userCastleInfo[num]));
        homeCastleUpgradeDialog.button("네","upgrade");
        homeCastleUpgradeDialog.button("아니오","none");

        stage.addActor(homeCastleUpgradeDialog);
    }


    public void dispose() {
        stage.dispose();

        castleTapTexture.dispose();
        unitTapTexture.dispose();

        unitTapButtonTexture.dispose();
        castleTapButtonTexture.dispose();

        homeSkin.dispose();
        homeSkin.allDispose();

        homeUnitTap.dispose();
        homeCastleTap.dispose();
    }
}
