package com.myjungle.game.ui.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by LeeWoochan on 2017-02-20.
 */

public class GameFoodIcon extends Vector2 {
    public String type = "";
    public int imageWidth = 32;
    public int imageHeight = 32;
    public int removeCount = 0;
    public boolean click = false;
    public GameFoodIcon(float x,float y,String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
