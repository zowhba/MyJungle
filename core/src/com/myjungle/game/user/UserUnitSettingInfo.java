package com.myjungle.game.user;

import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-07.
 */

public class UserUnitSettingInfo {
    public int unitNumber[];
    public AllUnit unitInfo[];
    public UserUnitInfo unitStat[];
    public UserUnitSettingInfo(){
        unitNumber = new int[]{-1,-1,-1,-1,-1,-1};
        unitInfo = new AllUnit[6];
        unitStat = new UserUnitInfo[6];
    }

    public void reset(){
        unitInfo = null;
        unitInfo = new AllUnit[6];
        unitStat = null;
        unitStat = new UserUnitInfo[6];
    }
}
