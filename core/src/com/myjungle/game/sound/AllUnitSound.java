package com.myjungle.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.myjungle.game.MyJungle;
import com.myjungle.game.user.UserInfo;

/**
 * Created by LeeWoochan on 2017-02-22.
 */

public class AllUnitSound {
    MyJungle MJ;

    public UnitSound playerUnitSound[];
    public UnitSound enemyUnitSound[];

    public Sound playerBulletSound[];
    public Sound enemyBulletSound[];

    public AllUnitSound(MyJungle mj){
        MJ = mj;

        playerUnitSound = new UnitSound[MJ.unitMountsInfo.playerUnits];
        enemyUnitSound = new UnitSound[MJ.unitMountsInfo.enemyUnits];
        playerBulletSound = new Sound[MJ.unitMountsInfo.playerBullets];
        enemyBulletSound = new Sound[MJ.unitMountsInfo.enemyBullets];
        playerUnitSound[0] = new UnitSound(this,"dog");
        playerUnitSound[1] = new UnitSound(this,"cat");
        playerUnitSound[2] = new UnitSound(this,"alpaca");
        playerUnitSound[3] = new UnitSound(this,"snake");
        playerUnitSound[4] = new UnitSound(this,"monkey");
        playerUnitSound[5] = new UnitSound(this,"bigdog");

        enemyUnitSound[0] = new UnitSound(this,"axeman");
        enemyUnitSound[1] = new UnitSound(this,"hunter");
        enemyUnitSound[2] = new UnitSound(this,"sawman");
        enemyUnitSound[3] = new UnitSound(this,"gunman");
        enemyUnitSound[4] = new UnitSound(this,"clubman");
        enemyUnitSound[5] = new UnitSound(this,"bulldozer");

        playerBulletSound[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/stone.wav"));
        playerBulletSound[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/spit.wav"));
        enemyBulletSound[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/arrow.wav"));
        enemyBulletSound[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/unitSound/bullet.wav"));
    }

    public void playSound(Sound sound){
        if(UserInfo.sound){
            sound.play();
        }
    }
    public void dispose(){
        for(int i=0;i<playerUnitSound.length;i++){
            playerUnitSound[i].dispose();
        }
        for(int i=0;i<enemyUnitSound.length;i++){
            enemyUnitSound[i].dispose();
        }
        for(int i=0;i<playerBulletSound.length;i++){
            playerBulletSound[i].dispose();
        }
        for(int i=0;i<enemyBulletSound.length;i++){
            enemyBulletSound[i].dispose();
        }
    }
}
