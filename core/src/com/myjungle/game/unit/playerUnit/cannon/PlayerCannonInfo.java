package com.myjungle.game.unit.playerUnit.cannon;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.unit.AllUnit;

/**
 * Created by LeeWoochan on 2017-02-16.
 */

public class PlayerCannonInfo extends AllUnit {
    public PlayerCannonInfo(float x, float y,int level, int damage, int cooltime, int damageLevel, int cooltimeLevel, int team){
        this.imageWidth = 128;
        this.imageHeight = 128;
        this.type = "cannon";
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
        this.size = 1;
        this.aniState = MathUtils.random(0,3);
        this.speed = 0;
        this.attackRange = 5+level;
        this.castleAttack = false;
        this.collision = false;
        this.outMap = true;
        this.stop = true;
        this.isTargeted = false;
        this.move = false;
        this.hp = 99999;
        this.damage = (int)(damage*Math.pow(1.2,damageLevel));
        this.id = MathUtils.random(0,999999999);
        this.attackCooltime = 0;
        this.attackCooltimeMax=(float)(cooltime/Math.pow(1.05,cooltimeLevel));
        this.attackState = 1;
        this.attackStateMax = (float)(cooltime/Math.pow(1.05,cooltimeLevel)*0.5);
        this.dieStateMax = 50;
        this.food = 50;
        this.attackAnimationNum = 1;
        if(team == 0) {
            this.range = 5+level;
            this.right = false;
            this.destination.x = 0;
            this.destination.y = this.center.y;
        }else {
            this.range = 5+level;
            this.right = true;
            this.destination.x = 8000;
            this.destination.y = this.center.y;
        }
    }
}
