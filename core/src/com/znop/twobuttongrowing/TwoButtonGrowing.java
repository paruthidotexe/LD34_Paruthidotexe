package com.znop.twobuttongrowing;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.znop.twobuttongrowing.screen.MainMenuScreen;
import com.znop.twobuttongrowing.screen.SplashScreen;

public class TwoButtonGrowing extends Game {

	public static boolean DEBUG = false;

	@Override
	public void create () {

		// Application
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		int androidVersion = Gdx.app.getVersion();

		Gdx.app.debug("Znop", "Android Version : " + androidVersion);

		// Files
		String FileDetails = "File Details: \n";
		boolean isExtAvailable = Gdx.files.isExternalStorageAvailable();
		boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();

		String extRoot = Gdx.files.getExternalStoragePath();
		String locRoot = Gdx.files.getLocalStoragePath();

		FileDetails += "\n isExternalStorageAvailable : " + isExtAvailable;
		FileDetails += "\n isLocalStorageAvailable : " + isLocAvailable;
		FileDetails += "\n getExternalStoragePath : " + extRoot;
		FileDetails += "\n getLocalStoragePath : " + locRoot;
		Gdx.app.debug("Znop", FileDetails);

		long javaHeap = Gdx.app.getJavaHeap();
		long nativeHeap = Gdx.app.getNativeHeap();

		Gdx.app.debug("Znop", "[javaHeap: " + javaHeap + "] [nativeHeap: " + nativeHeap);

		setScreen(new SplashScreen(this));
	}

/*
    @Override
    public void render() {
        super.render(); //important!
    }
*/

	@Override
	public void dispose() {
		super.dispose();
		if (getScreen() != null) {
			getScreen().dispose();
		}
		AssetMgr.Inst().dispose();
	}

}
