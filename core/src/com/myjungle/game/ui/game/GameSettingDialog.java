package com.myjungle.game.ui.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.user.UserInfo;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-23.
 */

public class GameSettingDialog extends Dialog {
    GameScreen GAMESCREEN;

    Skin skin;

    Image musicImage;
    Image soundImage;
    Image musicSelectImage = new Image();
    Image soundSelectImage = new Image();

    Table table = new Table();

    public GameSettingDialog(String title, Skin skin, GameScreen gameScreen) {
        super(title, skin);
        GAMESCREEN = gameScreen;

        this.skin = skin;

        musicImage = new Image(GAMESCREEN.musicTexture);
        soundImage = new Image(GAMESCREEN.soundTexture);

        table.add(musicImage).spaceRight(64);
        table.add(soundImage).row();
        table.add(musicSelectImage).spaceRight(64);
        table.add(soundSelectImage);

        this.getContentTable().add(table);
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);

        this.button("나가기","EXIT");

        if(UserInfo.music){
            musicSelectImage.setDrawable(skin.getDrawable("on"));
        } else{
            musicSelectImage.setDrawable(skin.getDrawable("off"));
        }
        if(UserInfo.sound){
            soundSelectImage.setDrawable(skin.getDrawable("on"));
        } else{
            soundSelectImage.setDrawable(skin.getDrawable("off"));
        }

        musicSelectImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            if(UserInfo.music){
                UserInfo.music = false;
                musicSelectImage.setDrawable(GameSettingDialog.this.skin.getDrawable("off"));
                GAMESCREEN.MJ.allMusic.playMusic(GAMESCREEN.MJ.allMusic.gameMusic);
            } else{
                UserInfo.music = true;
                musicSelectImage.setDrawable(GameSettingDialog.this.skin.getDrawable("on"));
                GAMESCREEN.MJ.allMusic.playMusic(GAMESCREEN.MJ.allMusic.gameMusic);
            }
            return true;
            }
        });

        soundSelectImage.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            if(UserInfo.sound){
                UserInfo.sound = false;
                soundSelectImage.setDrawable(GameSettingDialog.this.skin.getDrawable("off"));
            } else{
                UserInfo.sound = true;
                soundSelectImage.setDrawable(GameSettingDialog.this.skin.getDrawable("on"));
            }
            return true;
            }
        });
    }
    {

    }
    @Override
    protected void result(Object object) {
        super.result(object);
        if(object==""){
        }
        GAMESCREEN.pause = false;
        dispose();
    }

    public void dispose(){
    }
}
