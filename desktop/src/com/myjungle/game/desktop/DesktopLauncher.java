package com.myjungle.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.myjungle.game.MyJungle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new MyJungle(), "MyJungle", 1280, 720);
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new MyJungle(), config);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
