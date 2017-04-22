package com.myjungle.game.unit.enemyUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-28.
 */

public class EnemyBulldozerInfo extends AllUnit {
    public EnemyBulldozerInfo(float x, float y, int team){
        this.unitNumber = 5;
        this.imageWidth = 512;
        this.imageHeight = 512;
        this.type = "bulldozer";
        this.sort = "ground";
        this.attackType = "push";
        this.team = team;
        this.radius = 100;
        this.center.x = x;
        this.center.y = y;
        this.x = this.center.x - this.radius;
        this.y = this.center.y - this.radius;
        this.destination.x = this.center.x;
        this.destination.y = this.center.y;
        this.attack = 0;
        this.size = 1;
        this.aniState = MathUtils.random(0,3);
        this.speed = 150;
        this.attackRange = 1.5f;
        this.castleAttack = true;
        this.hp = 20000;
        this.damage = 80;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax = 5;
        this.attackState = 1;
        this.attackStateMax = 3;
        this.dieStateMax = 50;
        this.food = 10000;
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
        this.setLocationX(this.x-(float)(this.radius+((this.y-1.5*128)/2) + (float)Math.random()));
    }
}
