package com.myjungle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myjungle.game.castle.CastleInfo;
import com.myjungle.game.screen.MainScreen;
import com.myjungle.game.sound.AllEffectSound;
import com.myjungle.game.sound.AllMusic;
import com.myjungle.game.sound.AllUnitSound;
import com.myjungle.game.stage.StageInfo;
import com.myjungle.game.system.Convert;
import com.myjungle.game.system.UnitMountsInfo;
import com.myjungle.game.user.UserInfo;
import com.myjungle.game.user.UserUnitSettingInfo;

public class MyJungle extends Game {
	public UnitMountsInfo unitMountsInfo = new UnitMountsInfo();
	public Convert convert = new Convert(this);

	public SpriteBatch batch;
	public CastleInfo castleInfo = new CastleInfo();
	public UserInfo userInfo;
	public UserUnitSettingInfo userUnitSettingInfo = new UserUnitSettingInfo();
	public StageInfo stageInfo = new StageInfo();

	public AllUnitSound allUnitSound;
	public AllEffectSound allEffectSound;
	public AllMusic allMusic;

	@Override
	public void create() {
		batch = new SpriteBatch();
		userInfo = new UserInfo(this);
		allUnitSound = new AllUnitSound(this);
		allEffectSound = new AllEffectSound(this);
		allMusic = new AllMusic(this);


		this.setScreen(new MainScreen(this));
	}

	public void render(){
		super.render();
	}

	public void dispose() {
		batch.dispose();

		allUnitSound.dispose();
		allEffectSound.dispose();
		allMusic.dispose();
	}

	public void reset(){
		userUnitSettingInfo.reset();
		stageInfo.reset();
	}
}

