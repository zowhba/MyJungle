package com.myjungle.game.ui.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by LeeWoochan on 2017-02-23.
 */

public class GameSkin extends Skin {
    GameStage GAMESTAGE;

    Texture onTexture = new Texture("gameImage/on.png");
    Texture offTexture = new Texture("gameImage/off.png");


    Texture unitIconTexture[];

    Texture noneTexture = new Texture("unitIcons/none.png");
    Texture textureCheck = new Texture("unitIcons/check.png");

    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    public GameSkin(FileHandle skinFile, GameStage gameStage){
        super(skinFile);
        GAMESTAGE = gameStage;

        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        this.add("white", new Texture(pixmap));
        this.add("Check",textureCheck);

        this.add("on",onTexture);
        this.add("off",offTexture);


        unitIconTexture = new Texture[GAMESTAGE.GAMESCREEN.MJ.unitMountsInfo.playerUnits];

        for(int i=0;i<unitIconTexture.length;i++){
            unitIconTexture[i] = new Texture("unitIcons/"+i+".png");
            this.add(""+i,unitIconTexture[i]);
            createImageButtonStyle(""+i);
        }
        this.add("none",noneTexture);
        createImageButtonStyle("none");
    }

    public void createImageButtonStyle(String type){
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = this.newDrawable(type);
        imageButtonStyle.checked = this.newDrawable("Check");
        this.add(type + "Style", imageButtonStyle);
    }

    public void allDispose(){
        onTexture.dispose();
        offTexture.dispose();

        for(int i=0;i<unitIconTexture.length;i++){
            unitIconTexture[i].dispose();
        }

        noneTexture.dispose();
        textureCheck.dispose();

        pixmap.dispose();
    }
}
