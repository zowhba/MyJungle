package com.myjungle.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myjungle.game.MyJungle;
import com.myjungle.game.ui.stage.StageButtonIcon;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class StageScreen extends AllScreen {

    Stage stage;

    StageButtonIcon[] button = new StageButtonIcon[20];

    Texture stageBackgroundTexture = new Texture("stageImage/background.jpg");

    Texture backTexture = new Texture("stageImage/back.png");

    Texture newStageTexture = new Texture("stageImage/newStage.png");
    Texture reStageTexture = new Texture("stageImage/reStage.png");
    Texture bossStageTexture = new Texture("stageImage/bossStage.png");

    Image backImage;

    public StageScreen(MyJungle mj) {
        super(mj);
        MJ = mj;
        stage = new Stage(new StretchViewport(1280,720));
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        //Table table = new Table();
        //table.setFillParent(true);
        //stage.addActor(table);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<10;j++) {
                //table.add(button[i*10+j]).size(100,50);
                //table.add().size(6);
                if(UserInfo.clearStage == i * 10 + j && UserInfo.clearStage == 19){
                    button[i * 10 + j] = new StageButtonIcon(bossStageTexture, this);
                    button[i * 10 + j].setName("" + (i * 10 + j + 1));
                } else if (UserInfo.clearStage == i * 10 + j) {
                    button[i * 10 + j] = new StageButtonIcon(newStageTexture, this);
                    button[i * 10 + j].setName("" + (i * 10 + j + 1));
                } else if (UserInfo.clearStage > i * 10 + j) {
                    button[i * 10 + j] = new StageButtonIcon(reStageTexture, this);
                    button[i * 10 + j].setName("" + (i * 10 + j + 1));
                } else {
                    button[i * 10 + j] = new StageButtonIcon(newStageTexture, this);
                    button[i * 10 + j].setName("" + (i * 10 + j + 1));
                    button[i * 10 + j].setVisible(false);
                }
                stage.addActor(button[i * 10 + j]);
            }
            //table.row();
            //table.add().size(30);
            //table.row();
        }
        button[0].setPosition(200,600);
        button[1].setPosition(400,560);
        button[2].setPosition(130,450);
        button[3].setPosition(450,430);
        button[4].setPosition(20,400);
        button[5].setPosition(190,370);
        button[6].setPosition(420,300);
        button[7].setPosition(280,230);
        button[8].setPosition(120,180);
        button[9].setPosition(230,100);
        button[10].setPosition(400,120);
        button[11].setPosition(630,50);
        button[12].setPosition(820,180);
        button[13].setPosition(950,200);
        button[14].setPosition(1020,330);
        button[15].setPosition(1000,420);
        button[16].setPosition(820,500);
        button[17].setPosition(950,580);
        button[18].setPosition(800,620);
        button[19].setPosition(700,650);

        backImage = new Image(backTexture);
        stage.addActor(backImage);

        backImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                MJ.allEffectSound.playSound(MJ.allEffectSound.foodSound);
                dispose();
                MJ.setScreen(new HomeScreen(MJ,true));
                return true;
            }
        });
    }

    public void render (float delta) {
        Gdx.gl.glClearColor(1f, 1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MJ.batch.begin();
        MJ.batch.draw(stageBackgroundTexture, 0, 0);
        MJ.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose () {
        stageBackgroundTexture.dispose();
        backTexture.dispose();
        newStageTexture.dispose();
        reStageTexture.dispose();
        bossStageTexture.dispose();

        stage.dispose();
    }
}
