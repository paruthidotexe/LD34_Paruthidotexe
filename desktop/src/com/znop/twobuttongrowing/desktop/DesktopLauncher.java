package com.znop.twobuttongrowing.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.znop.twobuttongrowing.GameConst;
import com.znop.twobuttongrowing.TwoButtonGrowing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConst.APP_WIDTH;
		config.height = GameConst.APP_HEIGHT;
        config.title = GameConst.APP_TITLE;
		new LwjglApplication(new TwoButtonGrowing(), config);
	}
}
