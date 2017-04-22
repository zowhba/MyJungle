package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LeeWoochan on 2017-02-21.
 */

public class CreateAttackAnimation {
    public Texture sheet;

    public Animation<TextureRegion> right;
    public Animation<TextureRegion> left;

    public CreateAttackAnimation(Texture sheet){
        this.sheet = sheet;
        right = makeAnimation(sheet,"right");
        left = makeAnimation(sheet,"left");
    }

    public Animation<TextureRegion> makeAnimation(Texture sheet,String motion)
    {
        TextureRegion[][] region = TextureRegion.split(sheet, sheet.getWidth() / 3, sheet.getHeight());
        TextureRegion[][] region2 = TextureRegion.split(sheet, sheet.getWidth() / 3, sheet.getHeight());
        TextureRegion[] frame = new TextureRegion[2*3];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                frame[index] = region[i][j];
                frame[3+index] = region2[i][j];
                frame[3+index].flip(true,false);
                index++;
            }
            index+=3;
        }
        if(motion == "right"){
            return new Animation<TextureRegion>(0.1f, frame[2],frame[1],frame[0]);
        } else{
            return new Animation<TextureRegion>(0.1f, frame[5],frame[4],frame[3]);
        }
    }

    public void dispose(){
        sheet.dispose();
    }
}
