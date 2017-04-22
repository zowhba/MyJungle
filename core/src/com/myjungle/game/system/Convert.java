package com.myjungle.game.system;

import com.myjungle.game.MyJungle;

/**
 * Created by LeeWoochan on 2017-02-19.
 */

public class Convert {
    MyJungle MJ;

    public String playerUnitType[];
    public String enemyUnitType[];

    public Convert(MyJungle mj){
        MJ = mj;
        playerUnitType = new String[MJ.unitMountsInfo.playerUnits];
        enemyUnitType = new String[MJ.unitMountsInfo.enemyUnits];
        playerUnitType[0] = "dog";
        playerUnitType[1] = "cat";
        playerUnitType[2] = "alpaca";
        playerUnitType[3] = "snake";
        playerUnitType[4] = "monkey";
        playerUnitType[5] = "bigdog";

        enemyUnitType[0] = "axeman";
        enemyUnitType[1] = "hunter";
        enemyUnitType[2] = "sawman";
        enemyUnitType[3] = "gunman";
        enemyUnitType[4] = "clubman";
        enemyUnitType[5] = "bulldozer";
    }

    public String convertPlayerUnitNumToType(int num){
        return playerUnitType[num];
    }
    public int convertPlayerUnitTypeToNum(String type){
        for(int i=0;i<playerUnitType.length;i++){
            if(playerUnitType[i] == type){
                return i;
            }
        }
        return -1;
    }

    public String convertEnemyUnitNumToType(int num){
        return enemyUnitType[num];
    }

    public int convertEnemyUnitTypeToNum(String type){
        for(int i=0;i<enemyUnitType.length;i++){
            if(enemyUnitType[i] == type){
                return i;
            }
        }
        return -1;
    }
}
