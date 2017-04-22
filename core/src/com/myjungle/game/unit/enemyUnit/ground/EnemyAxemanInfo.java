package com.myjungle.game.unit.enemyUnit.ground;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-19.
 */

public class EnemyAxemanInfo extends AllUnit {
    public EnemyAxemanInfo(float x, float y, int team){
        this.unitNumber = 0;
        this.imageWidth = 128;
        this.imageHeight = 128;
        this.type = "axeman";
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
        this.size = 2;
        this.aniState = MathUtils.random(0,3);
        this.speed = 50;
        this.attackRange = 0.5f;
        this.castleAttack = true;
        this.hp = 100;
        this.damage = 20;
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax = 60;
        this.attackState = 1;
        this.attackStateMax = 25;
        this.dieStateMax = 50;
        this.food = 20;
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
