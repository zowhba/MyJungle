package com.myjungle.game.unit.playerUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-20.
 */

public class PlayerMonkeyInfo extends AllUnit{
    public PlayerMonkeyInfo(float x, float y, int team){
        this.unitNumber = 4;
        this.imageWidth = 128;
        this.imageHeight = 128;
        this.type = "monkey";
        this.sort = "ground";
        this.attackType = "long";
        this.team = team;
        this.radius = 32;
        this.center.x = x;
        this.center.y = y;
        this.x = this.center.x - this.radius;
        this.y = this.center.y - this.radius;
        this.destination.x = this.center.x;
        this.destination.y = this.center.y;
        this.attack = 0;
        this.size = 2;
        this.aniState = MathUtils.random(0,3);
        this.speed = 100;
        this.attackRange = 3;
        this.castleAttack = false;
        this.hp = 150;
        this.damage = 15;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax=40;
        this.attackState = 1;
        this.attackStateMax = 20;
        this.dieStateMax = 50;
        this.food = 150;
        this.purchaseGold = 20000;
        this.attackAnimationNum = 1;
        if(team == 0) {
            this.range = 3;
            this.right = false;
            this.destination.x = 0;
            this.destination.y = this.center.y;
        }else {
            this.range = 3;
            this.right = true;
            this.destination.x = 8000;
            this.destination.y = this.center.y;
        }
    }
}
