package com.myjungle.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.myjungle.game.MyJungle;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-24.
 */

public class AllMusic {
    MyJungle MJ;
    public Music mainMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/main.mp3"));
    public Music homeMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/home.mp3"));
    public Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/game.mp3"));

    public AllMusic(MyJungle mj){
        MJ = mj;
    }

    public void playMusic(Music music){
        if(UserInfo.music){
            music.play();
        } else{
            music.stop();
        }
    }

    public void dispose(){
        mainMusic.dispose();
        homeMusic.dispose();
        gameMusic.dispose();
    }
}
