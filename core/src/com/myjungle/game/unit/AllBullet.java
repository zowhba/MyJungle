package com.myjungle.game.unit;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public abstract class AllBullet extends AllUnit {
    public float bulletHeight;
    public float rotate;

    @Override
    public void setLocationX(float x)
    {
        this.x = x;
        this.center.x = x + radius;
    }
}