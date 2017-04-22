package com.myjungle.game.unit.enemyUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-01.
 */

public class EnemyHunterInfo extends AllUnit {
    public EnemyHunterInfo(float x, float y, int team){
        this.unitNumber = 1;
        this.imageWidth = 128;
        this.imageHeight = 128;
        this.type = "hunter";
        this.sort = "ground";
        this.attackType = "long";
        this.team = team;
        this.radius = 16;
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
        this.hp = 100;
        this.damage = 10;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax=60;
        this.attackState = 1;
        this.attackStateMax = 30;
        this.dieStateMax = 50;
        this.food = 30;
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
        this.setLocationX(this.x-(float)(this.radius+((this.y-1.5*128)/2) + (float)Math.random()));
    }
}