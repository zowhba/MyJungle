package com.myjungle.game.unit;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public abstract class AllUnit extends Circle implements Cloneable{
    public int unitNumber;

    public int imageWidth;

    public int imageHeight;

    public String sort;

    public String type;

    public String attackType;

    public int cellNum;

    public int ObCellNum = -1;

    public int team = -1;

    public boolean die = false;

    public int hp = 1;

    public int id;

    public int obId = -1;

    public boolean stop = false;

    public boolean right = true;

    public Vector2 destination = new Vector2();

    public float attackCooltime;

    public float attackCooltimeMax;

    public int attack;

    public Vector2 center = new Vector2();

    public int speed;

    public int damage;

    public int range;

    public float attackRange;

    public int size = 1;

    public boolean go = false;

    public boolean castleAttack = false;

    public boolean collision = true;

    public boolean outMap = false;

    public boolean isTargeted = true;

    public boolean isNearCastle = false;

    public boolean move = true;

    public boolean isAttack = false;

    public boolean injured = false;

    public Vector2 direction = new Vector2(0,0);

    public float dieState;

    public float dieStateMax;

    public float attackState;

    public float attackStateMax;

    public float aniState;

    public int food;

    public int attackAnimationNum = 0;

    public int purchaseGold;

    public void setLocationX(float x)
    {
        if(x>this.x) {
            right = true;
        }else{
            right = false;
        }
        this.x = x;
        this.center.x = x + radius;
    }
    public void setLocationY(float y)
    {
        this.y = y;
        this.center.y = y + radius;
    }

    public void dieStateUpdate()
    {
        if(dieState<=dieStateMax) {
            dieState ++;
        }
    }

    public void attackCooltimeUpdate(){
        attackCooltime ++;
        if(attackCooltime>attackCooltimeMax)
        {
            attackCooltime = 0;
            attackState = 1;
        }
    }

    public void attackStateUpdate()
    {
        if(attackState>0) {
            attackState ++;
            if(attackState>attackStateMax){
                attackState = 0;
                attack = 1;
            }
        }
    }

    public void pushLocationX(float x)
    {
        this.x = x;
        this.center.x = x + radius;
    }
    public void pushLocationY(float y)
    {
        this.y = y;
        this.center.y = y + radius;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object objReturn;

        try
        {
            objReturn = super.clone();
            ((AllUnit)objReturn).sort = this.sort;
            ((AllUnit)objReturn).type = this.type;
            ((AllUnit)objReturn).attackType = this.attackType;
            ((AllUnit)objReturn).destination = this.destination.cpy();
            ((AllUnit)objReturn).center = this.center.cpy();
            ((AllUnit)objReturn).direction = this.direction.cpy();
            ((AllUnit)objReturn).id = MathUtils.random(0,999999999);
            ((AllUnit)objReturn).aniState = MathUtils.random(0,3);

            return objReturn;
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}