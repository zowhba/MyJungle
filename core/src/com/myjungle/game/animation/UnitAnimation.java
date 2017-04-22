package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.ui.home.HomeUnitTap;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class UnitAnimation {
    GameScreen GAMESCREEN;
    HomeUnitTap HOMEUNITTAP;

    public CreateChangeTexture playerCastle;
    public CreateChangeTexture enemyCastle;

    public Texture playerUnitTexture[];
    public CreateUnitAnimation playerUnitAnimation[];

    public Texture enemyUnitTexture[];
    public CreateUnitAnimation enemyUnitAnimation[];

    public Texture playerBulletTexture[];
    public CreateTexture playerBulletCT[];

    public Texture enemyBulletTexture[];
    public CreateTexture enemyBulletCT[];

    float stateTime;

    public UnitAnimation(GameScreen gameScreen)
    {
        GAMESCREEN = gameScreen;

        playerUnitTexture = new Texture[GAMESCREEN.MJ.unitMountsInfo.playerUnits];
        playerUnitAnimation = new CreateUnitAnimation[GAMESCREEN.MJ.unitMountsInfo.playerUnits];

        enemyUnitTexture = new Texture[GAMESCREEN.MJ.unitMountsInfo.enemyUnits];
        enemyUnitAnimation = new CreateUnitAnimation[GAMESCREEN.MJ.unitMountsInfo.enemyUnits];

        playerBulletTexture = new Texture[GAMESCREEN.MJ.unitMountsInfo.playerBullets];
        playerBulletCT = new CreateTexture[GAMESCREEN.MJ.unitMountsInfo.playerBullets];

        enemyBulletTexture = new Texture[GAMESCREEN.MJ.unitMountsInfo.enemyBullets];
        enemyBulletCT = new CreateTexture[GAMESCREEN.MJ.unitMountsInfo.enemyBullets];

        playerCastle = new CreateChangeTexture(new Texture("castles/playerCastle.png"));
        enemyCastle = new CreateChangeTexture(new Texture("castles/enemyCastle.png"));

        for(int i = 0;i<GAMESCREEN.MJ.unitMountsInfo.playerUnits;i++) {
            playerUnitTexture[i] = new Texture("units/playerUnit/" + i + ".png");
            playerUnitAnimation[i] = new CreateUnitAnimation(playerUnitTexture[i]);
        }

        for(int i = 0;i<GAMESCREEN.MJ.unitMountsInfo.enemyUnits;i++) {
            enemyUnitTexture[i] = new Texture("units/enemyUnit/" + i +".png");
            enemyUnitAnimation[i] = new CreateUnitAnimation(enemyUnitTexture[i]);
        }

        for(int i = 0;i<GAMESCREEN.MJ.unitMountsInfo.playerBullets;i++) {
            playerBulletTexture[i] = new Texture("units/playerBullet/" + i + ".png");
            playerBulletCT[i] = new CreateTexture(playerBulletTexture[i]);
        }

        for(int i = 0;i<GAMESCREEN.MJ.unitMountsInfo.enemyBullets;i++) {
            enemyBulletTexture[i] = new Texture("units/enemyBullet/" + i +".png");
            enemyBulletCT[i] = new CreateTexture(enemyBulletTexture[i]);
        }
    }

    public UnitAnimation(HomeUnitTap homeUnitTap)
    {
        HOMEUNITTAP = homeUnitTap;

        playerUnitTexture = new Texture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerUnits];
        playerUnitAnimation = new CreateUnitAnimation[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerUnits];

        enemyUnitTexture = new Texture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyUnits];
        enemyUnitAnimation = new CreateUnitAnimation[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyUnits];

        playerBulletTexture = new Texture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerBullets];
        playerBulletCT = new CreateTexture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerBullets];

        enemyBulletTexture = new Texture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyBullets];
        enemyBulletCT = new CreateTexture[HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyBullets];

        playerCastle = new CreateChangeTexture(new Texture("castles/playerCastle.png"));
        enemyCastle = new CreateChangeTexture(new Texture("castles/enemyCastle.png"));

        for(int i = 0;i<HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerUnits;i++) {
            playerUnitTexture[i] = new Texture("units/playerUnit/" + i + ".png");
            playerUnitAnimation[i] = new CreateUnitAnimation(playerUnitTexture[i]);
        }

        for(int i = 0;i<HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyUnits;i++) {
            enemyUnitTexture[i] = new Texture("units/enemyUnit/" + i +".png");
            enemyUnitAnimation[i] = new CreateUnitAnimation(enemyUnitTexture[i]);
        }

        for(int i = 0;i<HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.playerBullets;i++) {
            playerBulletTexture[i] = new Texture("units/playerBullet/" + i + ".png");
            playerBulletCT[i] = new CreateTexture(playerBulletTexture[i]);
        }

        for(int i = 0;i<HOMEUNITTAP.HOMESCREEN.MJ.unitMountsInfo.enemyBullets;i++) {
            enemyBulletTexture[i] = new Texture("units/enemyBullet/" + i +".png");
            enemyBulletCT[i] = new CreateTexture(enemyBulletTexture[i]);
        }
    }

    public TextureRegion updateUnitFrame(AllUnit units)
    {
        if(units.team ==1){
            return createAnimationFrame(units,playerUnitAnimation[units.unitNumber]);
        } else if(units.team == 0){
            return createAnimationFrame(units,enemyUnitAnimation[units.unitNumber]);
        }
        return null;
    }

    public TextureRegion updateBulletFrame(AllUnit units){
        if(units.team ==1){
            return createTextureFrame(units,playerBulletCT[units.unitNumber]);
        } else if(units.team == 0){
            return createTextureFrame(units,enemyBulletCT[units.unitNumber]);
        }
        return null;
    }


    public TextureRegion createAnimationFrame(AllUnit units,CreateUnitAnimation CUA){
        if(units.right == true) {
            if (units.die == true) {
                return CUA.die.getKeyFrame(units.dieState / 60f, true);
            } else if (units.isAttack) {
                return CUA.attack.getKeyFrame(units.attackState / units.attackStateMax, true);
            } else if (units.stop == true) {
                return CUA.stop.getKeyFrame(units.aniState / 8f + stateTime, true);
            } else {
                return CUA.run.getKeyFrame(units.aniState / 8f + stateTime*units.speed/50/units.size, true);
            }
        } else {
            if (units.die == true) {
                return CUA.dieB.getKeyFrame(units.dieState / 60f, true);
            } else if (units.isAttack) {
                return CUA.attackB.getKeyFrame(units.attackState / units.attackStateMax, true);
            } else if (units.stop == true) {
                return CUA.stopB.getKeyFrame(units.aniState / 8f + stateTime, true);
            } else {
                return CUA.runB.getKeyFrame(units.aniState / 8f + stateTime*units.speed/50/units.size, true);
            }
        }
    }
    public TextureRegion createTextureFrame(AllUnit units,CreateTexture CT){
        if(units.right == true) {
            return CT.right;
        } else {
            return CT.left;
        }
    }
    public TextureRegion createChangeTextureFrame(int hp, CreateChangeTexture CCT){
        if (hp>1500) {
            return CCT.strong;
        } else if(hp<=1500&&hp>0) {
            return CCT.weak;
        } else {
            return CCT.die;
        }
    }

    public void updateStatetime()
    {
        stateTime += 0.017;
    }
    public void dispose()
    {
        playerCastle.dispose();
        enemyCastle.dispose();
        for(int i=0;i<playerUnitAnimation.length;i++){
            playerUnitTexture[i].dispose();
            playerUnitAnimation[i].dispose();
        }
        for(int i=0;i<enemyUnitAnimation.length;i++){
            enemyUnitTexture[i].dispose();
            enemyUnitAnimation[i].dispose();
        }
        for(int i=0;i<playerBulletTexture.length;i++){
            playerBulletTexture[i].dispose();
            playerBulletCT[i].dispose();
        }
        for(int i=0;i<enemyBulletTexture.length;i++){
            enemyBulletTexture[i].dispose();
            enemyBulletCT[i].dispose();
        }
    }
}
