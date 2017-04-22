package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LeeWoochan on 2017-02-01.
 */

public class CreateUnitAnimation {
    public Texture sheet;

    public Animation<TextureRegion> stop;
    public Animation<TextureRegion> run;
    public Animation<TextureRegion> attack;
    public Animation<TextureRegion> die;
    public Animation<TextureRegion> stopB;
    public Animation<TextureRegion> runB;
    public Animation<TextureRegion> attackB;
    public Animation<TextureRegion> dieB;

    public CreateUnitAnimation(Texture sheet){
        this.sheet = sheet;
        stop = makeAnimation(sheet,"stop");
        run = makeAnimation(sheet,"run");
        attack = makeAnimation(sheet,"attack");
        die = makeAnimation(sheet,"die");
        stopB = makeAnimation(sheet,"stopB");
        runB = makeAnimation(sheet,"runB");
        attackB = makeAnimation(sheet,"attackB");
        dieB = makeAnimation(sheet,"dieB");
    }

    public Animation<TextureRegion> makeAnimation(Texture sheet, String motion)
    {
        TextureRegion[][] region = TextureRegion.split(sheet, sheet.getWidth() / 4, sheet.getHeight() / 4);
        TextureRegion[][] region2 = TextureRegion.split(sheet, sheet.getWidth() / 4, sheet.getHeight() / 4);
        TextureRegion[] frame = new TextureRegion[8*4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                frame[index] = region[i][j];
                frame[4+index] = region2[i][j];
                frame[4+index].flip(true,false);
                index++;
            }
            index+=4;
        }
        if(motion == "stop") return new Animation<TextureRegion>(0.2f, frame[0],frame[1],frame[2],frame[3]);
        else if(motion == "stopB") return new Animation<TextureRegion>(0.2f, frame[4],frame[5],frame[6],frame[7]);
        else if(motion == "run") return new Animation<TextureRegion>(0.2f, frame[8],frame[9],frame[10],frame[11]);
        else if(motion == "runB") return new Animation<TextureRegion>(0.2f, frame[12],frame[13],frame[14],frame[15]);
        else if(motion == "attack") return new Animation<TextureRegion>(0.25f, frame[16],frame[17],frame[18],frame[19]);
        else if(motion == "attackB") return new Animation<TextureRegion>(0.25f, frame[20],frame[21],frame[22],frame[23]);
        else if(motion == "die") return new Animation<TextureRegion>(0.1f, frame[24],frame[25],frame[26],frame[27],frame[27],frame[27],frame[27],frame[27],frame[27]);
        else if(motion == "dieB") return new Animation<TextureRegion>(0.1f, frame[28],frame[29],frame[30],frame[31],frame[31],frame[31],frame[31],frame[31],frame[31],frame[31]);
        else return new Animation<TextureRegion>(0.2f, frame[0],frame[1],frame[2],frame[3]);
    }

    public void dispose(){
        sheet.dispose();
    }
}
