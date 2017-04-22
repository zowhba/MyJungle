package com.myjungle.game.unit.playerUnit.bullet;

import com.badlogic.gdx.math.MathUtils;
import com.myjungle.game.system.MyJungleUtil;
import com.myjungle.game.unit.AllBullet;

/**
 * Created by LeeWoochan on 2017-02-27.
 */

public class PlayerSpitInfo extends AllBullet {
    public PlayerSpitInfo(float x1, float y1, float x2, float y2, int obId, int damage, int team){
        this.unitNumber = 1;
        this.imageWidth = 16;
        this.imageHeight = 16;
        this.type = "spit";
        this.sort = "bullet";
        this.attackType = "collision";
        this.team = team;
        this.radius = 8;
        this.center.x = x1;
        this.center.y = y1;
        this.x = this.center.x - this.radius;
        this.y = this.center.y - this.radius;
        this.direction.x = (x2-x1)/(MyJungleUtil.Abnum(x2-x1)+ MyJungleUtil.Abnum(y2-y1));
        this.direction.y = (y2-y1)/(MyJungleUtil.Abnum(x2-x1)+MyJungleUtil.Abnum(y2-y1));
        this.attack = 1;
        this.size = 1;
        this.aniState = MathUtils.random(0,3);
        this.speed = 800;
        this.attackRange = 1;
        this.castleAttack = false;
        this.hp = 1000;
        this.damage = damage;
        this.id = MathUtils.random(0,999999999);
        this.bulletHeight = 50;
        this.obId = obId;
        this.attackAnimationNum = 1;
        if(x1<x2){
            this.right = true;
        } else{
            this.right = false;
        }
        this.rotate = (float)(90*2*Math.atan(this.direction.y/this.direction.x)/3.14);
    }
}
