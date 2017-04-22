package com.myjungle.game.user;

/**
 * Created by LeeWoochan on 2017-02-07.
 */

public class UserUnitInfo {
    public String type = "none";

    public int stat[] = new int[4];

    public int use = 0;

    public UserUnitInfo(String type){
        this.type = type;
        stat[0] = 0;//hp
        stat[1] = 0;//damage
        stat[2] = 0;//speed
        stat[3] = 0;//cooltime
    }
}
