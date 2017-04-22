package com.myjungle.game.ui.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by LeeWoochan on 2017-02-21.
 */

public class GameAttackEffet extends Vector2 {
    public int imageWidth = 64;
    public int imageHeight = 64;
    public int attackAnimationNum = 0;
    public int removeCount = 12;

    public boolean right = true;
    public GameAttackEffet(float x,float y,int attackAnimationNum,boolean right){
        this.x = x;
        this.y = y;
        this.attackAnimationNum = attackAnimationNum;
        this.right = right;
    }
}