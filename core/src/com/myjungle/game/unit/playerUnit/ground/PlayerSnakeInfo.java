package com.myjungle.game.unit.playerUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-24.
 */

public class PlayerSnakeInfo extends AllUnit{
    public PlayerSnakeInfo(float x, float y, int team){
        this.unitNumber = 3;
        this.imageWidth = 256;
        this.imageHeight = 256;
        this.type = "snake";
        this.sort = "ground";
        this.attackType = "short";
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
        this.attackRange = 1f;
        this.castleAttack = true;
        this.hp = 150;
        this.damage = 40;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax = 60;
        this.attackState = 1;
        this.attackStateMax = 20;
        this.dieStateMax = 50;
        this.food = 100;
        this.purchaseGold = 8000;
        this.attackAnimationNum = 1;
        if(team == 0) {
            this.range = 1;
            this.right = false;
            this.destination.x = 0;
            this.destination.y = this.center.y;
        }else {
            this.range = 1;
            this.right = true;
            this.destination.x = 8000;
            this.destination.y = this.center.y;
        }
    }
}
