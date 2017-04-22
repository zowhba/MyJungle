package com.myjungle.game.ui.home;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.HomeScreen;

/**
 * Created by LeeWoochan on 2017-02-23.
 */

public class HomeSkin extends Skin {
    HomeScreen HOMESCREEN;

    public Texture unitIconTexture[];

    Texture unitCheckTexture = new Texture("gameImage/unitCheck.png");
    Texture unitListBacgroundTexture = new Texture("homeImage/unitListScrollPane.png");
    Texture unitInfoContainerTexture = new Texture("homeImage/unitInfoContainer.png");

    public HomeSkin(FileHandle skinFile,HomeScreen homeScreen){
        super(skinFile);
        HOMESCREEN = homeScreen;
        unitIconTexture = new Texture[HOMESCREEN.MJ.unitMountsInfo.playerUnits];

        for(int i=0;i<unitIconTexture.length;i++){
            unitIconTexture[i] = new Texture("unitIcons/"+i+".png");
        }

        this.add("unitInfoBackground", unitInfoContainerTexture);
        this.add("unitCheckBackground", unitCheckTexture);
        this.add("unitListBackground", unitListBacgroundTexture);
    }

    public void allDispose(){
        unitCheckTexture.dispose();
        unitListBacgroundTexture.dispose();
        unitInfoContainerTexture.dispose();
        for(int i=0;i<unitIconTexture.length;i++){
            unitIconTexture[i].dispose();
        }
    }
}
