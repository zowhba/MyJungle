package com.myjungle.game.unit.playerUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-01-31.
 */
public class PlayerCatInfo extends AllUnit {
    public PlayerCatInfo(float x, float y, int team){
        this.unitNumber = 1;
        this.imageWidth = 64;
        this.imageHeight = 64;
        this.type = "cat";
        this.sort = "ground";
        this.attackType = "short";
        this.team = team;
        this.radius = 16;
        this.center.x = x;
        this.center.y = y;
        this.x = this.center.x - this.radius;
        this.y = this.center.y - this.radius;
        this.destination.x = this.center.x;
        this.destination.y = this.center.y;
        this.attack = 0;
        this.size = 1;
        this.aniState = MathUtils.random(0,3);
        this.speed = 50;
        this.attackRange = 0.4f;
        this.castleAttack = true;
        this.hp = 100;
        this.damage = 10;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax = 30;
        this.attackState = 1;
        this.attackStateMax = 25;
        this.dieStateMax = 50;
        this.food = 50;
        this.attackAnimationNum = 2;
        this.purchaseGold = 300;
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