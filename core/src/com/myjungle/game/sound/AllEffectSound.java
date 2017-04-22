package com.myjungle.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.myjungle.game.MyJungle;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-26.
 */

public class AllEffectSound {
    MyJungle MJ;
    public Sound pourSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effectSound/pour.wav"));
    public Sound foodSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effectSound/food.wav"));
    public Sound playerCastleExplosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effectSound/playerCastleExplosion.wav"));
    public Sound enemyCastleExplosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effectSound/enemyCastleExplosion.wav"));

    public AllEffectSound(MyJungle mj){
        MJ = mj;
    }

    public void playSound(Sound sound){
        if(UserInfo.sound){
            sound.play();
        } else{
            sound.stop();
        }
    }

    public void dispose(){
        pourSound.dispose();
        foodSound.dispose();
        playerCastleExplosionSound.dispose();
        enemyCastleExplosionSound.dispose();
    }
}
