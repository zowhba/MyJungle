package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LeeWoochan on 2017-02-01.
 */

public class CreateChangeTexture {
    public Texture sheet;

    public TextureRegion strong;
    public TextureRegion weak;
    public TextureRegion die;

    public CreateChangeTexture(Texture sheet){
        this.sheet = sheet;
        strong = makeTexture(sheet,"strong");
        weak = makeTexture(sheet,"week");
        die = makeTexture(sheet,"die");
    }

    public TextureRegion makeTexture(Texture sheet, String motion)
    {
        TextureRegion[][] region = TextureRegion.split(sheet, sheet.getWidth(), (sheet.getHeight())/3);
        TextureRegion[] frame = new TextureRegion[3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                frame[index++] = region[i][j];
            }
        }
        if(motion == "strong") return frame[0];
        else if(motion == "week") return frame[1];
        else if(motion == "die") return frame[2];
        else return frame[0];
    }

    public void dispose(){
        sheet.dispose();
    }
}
