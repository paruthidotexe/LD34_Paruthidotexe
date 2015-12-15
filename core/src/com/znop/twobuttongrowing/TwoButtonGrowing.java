package com.znop.twobuttongrowing;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.znop.twobuttongrowing.screen.SplashScreen;

/**
 * Created by @paruthidotexe on 12-12-2015.
 * Note: Use this code as u wish, but dont sell the code.
 *      If any optimizations/ bugs ping me @paruthidotexe
 *      If you become rich by making games, try like me
 *      donating 10% to those people who are in need.
 *      Happy Gaming !!!
 */

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
