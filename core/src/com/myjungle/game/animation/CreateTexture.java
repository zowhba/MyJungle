package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LeeWoochan on 2017-02-01.
 */

public class CreateTexture {
    public Texture sheet;

    public TextureRegion right;
    public TextureRegion left;

    public CreateTexture(Texture sheet){
        this.sheet = sheet;
        right = makeTexture(sheet,"right");
        left = makeTexture(sheet,"left");
    }

    public TextureRegion makeTexture(Texture sheet, String motion)
    {
        TextureRegion[][] region = TextureRegion.split(sheet, sheet.getWidth() / 2, sheet.getHeight());
        TextureRegion[] frame = new TextureRegion[2];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                frame[index++] = region[i][j];
            }
        }
        if(motion == "right") return frame[0];
        else if(motion == "left") return frame[1];
        else return frame[0];
    }

    public void dispose(){
        sheet.dispose();
    }
}
