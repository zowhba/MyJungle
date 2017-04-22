package com.myjungle.game.user;

import com.myjungle.game.MyJungle;
import com.myjungle.game.unit.AllUnit;
import com.myjungle.game.unit.playerUnit.ground.PlayerAlpacaInfo;
import com.myjungle.game.unit.playerUnit.ground.PlayerBigdogInfo;
import com.myjungle.game.unit.playerUnit.ground.PlayerCatInfo;
import com.myjungle.game.unit.playerUnit.ground.PlayerDogInfo;
import com.myjungle.game.unit.playerUnit.ground.PlayerMonkeyInfo;
import com.myjungle.game.unit.playerUnit.ground.PlayerSnakeInfo;

/**
 * Created by LeeWoochan on 2017-02-11.
 */

public class UserInfo {
    public MyJungle MJ;

    public static  boolean music = true;
    public static  boolean sound = true;

    public static int clearStage;

    public static int gold;

    public static int userCastleGold[] = new int[6];

    public static int userCastleInfo[] = new int[6];

    public static AllUnit playerUnit[];

    public static UserUnitInfo userUnitInfo[];

    public UserInfo(MyJungle myJungle){
        MJ = myJungle;
        playerUnit = new AllUnit[MJ.unitMountsInfo.playerUnits];
        userUnitInfo = new UserUnitInfo[MJ.unitMountsInfo.playerUnits];

        userCastleGold[0] = 500;//cannonlevel
        userCastleGold[1] = 100;//hp
        userCastleGold[2] = 300;//damage
        userCastleGold[3] = 400;//cooltime
        userCastleGold[4] = 600;//foodlevel
        userCastleGold[5] = 700;//foodcooltime

        userCastleInfo[0] = 0;
        userCastleInfo[1] = 0;
        userCastleInfo[2] = 0;
        userCastleInfo[3] = 0;
        userCastleInfo[4] = 0;
        userCastleInfo[5] = 0;

        playerUnit[0] = new PlayerDogInfo(0,0,1);
        playerUnit[1] = new PlayerCatInfo(0,0,1);
        playerUnit[2] = new PlayerAlpacaInfo(0,0,1);
        playerUnit[3] = new PlayerSnakeInfo(0,0,1);
        playerUnit[4] = new PlayerMonkeyInfo(0,0,1);
        playerUnit[5] = new PlayerBigdogInfo(0,0,1);

        for(int i=0;i<userUnitInfo.length;i++){
            userUnitInfo[i] = new UserUnitInfo(MJ.convert.convertPlayerUnitNumToType(i));
        }
    }
}
