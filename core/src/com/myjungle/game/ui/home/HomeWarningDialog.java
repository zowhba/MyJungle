package com.myjungle.game.ui.home;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.myjungle.game.screen.HomeScreen;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by LeeWoochan on 2017-02-25.
 */

public class HomeWarningDialog extends Dialog{
    HomeScreen HOMESCREEN;
    Skin skin;
    public HomeWarningDialog(String title, Skin skin, HomeScreen homeScreen) {
        super(title, skin);
        HOMESCREEN = homeScreen;
        this.skin = skin;
        this.getTitleLabel().setAlignment(center);
        this.setScale(1);
        this.setBounds(340,210,600,300);
        this.button("확인","OK");
    }
    {

    }
}
