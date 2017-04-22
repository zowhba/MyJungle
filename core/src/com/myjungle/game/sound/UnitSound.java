package com.myjungle.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-22.
 */

public class UnitSound {
    AllUnitSound ALLUNITSOUND;
    public Sound spawnSound;
    public Sound attackSound;
    public Sound dieSound;
    public UnitSound(AllUnitSound allUnitSound,String type){
        ALLUNITSOUND = allUnitSound;
        this.spawnSound = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/" + type + "1.wav"));
        this.attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/" + type + "2.wav"));
        this.dieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/" + type + "3.wav"));
    }
    public void playSound(Sound sound){
        if(UserInfo.sound){
            sound.play();
        }
    }
    public void dispose(){
        spawnSound.dispose();
        attackSound.dispose();
        dieSound.dispose();
    }
}
