package com.myjungle.game.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.system.MyJungleInfo;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-08.
 */

public class GameStage extends Stage {
    public GameScreen GAMESCREEN;

    public Texture settingTexture = new Texture("gameImage/setting.png");

    public Texture giveUpTexture = new Texture("gameImage/giveUp.png");

    public Texture menuBackgroundTexTure = new Texture("gameImage/menuBackground.jpg");

    public Texture playerHpBarTexture = new Texture("gameImage/playerHpBar.png");

    public Texture enemyHpBarTexture = new Texture("gameImage/enemyHpBar.png");

    public Texture hpBarTexture = new Texture("gameImage/hpBar.png");

    public Texture foodTexture = new Texture("gameImage/apple.png");

    public Texture bucketTexture = new Texture("gameImage/bucket.png");

    public Texture waterBarTexture = new Texture("gameImage/waterBar.png");


    public BitmapFont font = new BitmapFont(Gdx.files.internal("flat-earth/skin/wargame.fnt"));

    public Table foodTable = new Table();

    public Table mainTable = new Table();

    public Table buttonTable = new Table();

    public GameUnitIcon[] unitButton;

    public Image settingButton = new Image(settingTexture);

    public Image giveUpButton = new Image(giveUpTexture);

    public Image menuBackgroundImage = new Image(menuBackgroundTexTure);

    public Image foodImage = new Image(foodTexture);

    public Label foodLabel;

    public Image bucketImage = new Image(bucketTexture);

    public Image waterBarImage = new Image(waterBarTexture);

    public GameSkin gameSkin;

    public GameStage(Viewport viewport,GameScreen gameScreen){
        super(viewport);
        GAMESCREEN = gameScreen;

        gameSkin = new GameSkin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"),this);

        this.addActor(menuBackgroundImage);

        foodLabel = new Label("",new Label.LabelStyle(font, Color.BLUE));

        unitButton = new GameUnitIcon[6];
        for(int i=0;i<6;i++){
            if(gameScreen.MJ.userUnitSettingInfo.unitInfo[i]!=null) {
                unitButton[i] = new GameUnitIcon(gameSkin, GAMESCREEN.MJ.userUnitSettingInfo.unitInfo[i].unitNumber+"Style", this);
            } else{
                unitButton[i] = new GameUnitIcon(gameSkin,"noneStyle", this);
            }
            unitButton[i].setName(i+"");
        }

        mainTable.bottom();
        mainTable.setFillParent(true);
        mainTable.pad(32);


        buttonTable.add(settingButton).row();
        buttonTable.add(giveUpButton);
        mainTable.add(buttonTable).size(MyJungleInfo.roomSize);

        for(int i=0;i<6;i++){
            mainTable.add(unitButton[i]).size(MyJungleInfo.roomSize).spaceRight(MyJungleInfo.roomSize/4);
        }

        foodTable.add(foodImage).size(32);
        foodTable.add(foodLabel).size(100,64).row();
        foodTable.add(bucketImage).colspan(2).row();

        foodTable.add(waterBarImage).colspan(2);

        mainTable.add(foodTable);

        this.addActor(mainTable);

        bucketImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GAMESCREEN.gamefoodIcons.add(new GameFoodIcon(MathUtils.random(MyJungleInfo.roomSize*3,MyJungleInfo.roomSize*10),MathUtils.random(MyJungleInfo.roomSize*4,MyJungleInfo.roomSize*5),"bucket"));
                GAMESCREEN.waterGauge += 100;
                if(GAMESCREEN.waterGauge>1000){
                    GAMESCREEN.waterGauge = 1000;
                }
                GAMESCREEN.MJ.allEffectSound.playSound(GAMESCREEN.MJ.allEffectSound.pourSound);
                return true;
            }
        });

        settingButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GameStage.this.addActor(new GameSettingDialog("설정",gameSkin,GAMESCREEN));
                GAMESCREEN.pause = true;
                return true;
            }
        });

        giveUpButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GameStage.this.addActor(new GameGiveUpDialog("항복",gameSkin,GAMESCREEN));
                GAMESCREEN.pause = true;
                return true;
            }
        });
    }

    public void render(){
        foodLabel.setText(""+(int)GAMESCREEN.playerFood);

        this.getBatch().begin();
        if(GAMESCREEN.playerCastleHp>0){
            this.getBatch().draw(playerHpBarTexture, 128, 656, 0, 0, (int) (512 * ((float) GAMESCREEN.playerCastleHp / (GAMESCREEN.MJ.castleInfo.castleHp * (int) Math.pow(1.1, UserInfo.userCastleInfo[1])))), 32);
        }
        else this.getBatch().draw(playerHpBarTexture, 128, 656, 0, 0,0,0);
        if(GAMESCREEN.enemyCastleHp>0){
            this.getBatch().draw(enemyHpBarTexture, 1152 - 512 * ((float) GAMESCREEN.enemyCastleHp / (GAMESCREEN.MJ.castleInfo.castleHp * (int) Math.pow(1.1, GAMESCREEN.MJ.stageInfo.castleLevel))), 656, (int) (512 - 512 * ((float) GAMESCREEN.enemyCastleHp / (GAMESCREEN.MJ.castleInfo.castleHp * (int) Math.pow(1.1, GAMESCREEN.MJ.stageInfo.castleLevel)))), 0, (int) (512 * ((float) GAMESCREEN.enemyCastleHp / (GAMESCREEN.MJ.castleInfo.castleHp * (int) Math.pow(1.1, GAMESCREEN.MJ.stageInfo.castleLevel)))), 32);
        }
        else this.getBatch().draw(enemyHpBarTexture, 1152 - 512 * ((float) GAMESCREEN.enemyCastleHp / (GAMESCREEN.MJ.castleInfo.castleHp * (int) Math.pow(1.1, GAMESCREEN.MJ.stageInfo.castleLevel))), 656, 0, 0);
        this.getBatch().draw(hpBarTexture, 128, 656);
        this.getBatch().end();
    }

    public void allDispose(){
        settingTexture.dispose();
        giveUpTexture.dispose();
        menuBackgroundTexTure.dispose();
        playerHpBarTexture.dispose();
        enemyHpBarTexture.dispose();
        hpBarTexture.dispose();
        foodTexture.dispose();
        bucketTexture.dispose();
        font.dispose();

        gameSkin.dispose();
        gameSkin.allDispose();
    }
}
