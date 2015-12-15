package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.znop.twobuttongrowing.AssetMgr;
import com.znop.twobuttongrowing.TwoButtonGrowing;

import java.io.File;

/**
 * Created by user on 12-12-2015.
 */
public class SplashScreen extends PonzScreen{

    boolean isLoadInit = false;
    boolean isLoaded = false;

    float loadWaitTime = 0.5f;

    Texture splashTexture;

    public SplashScreen(TwoButtonGrowing game) {
        super(game);

        splashTexture = new Texture(Gdx.files.internal(
                        "ponzres" + File.separator +
                        "images" + File.separator +
                        "SplashScreen.png"));

        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void updateScreen(float fixedStep) {
        if(!isLoadInit) {
            isLoadInit = true;
            // Load All
            AssetMgr.Inst().Init();
            TwoButtonGrowing.DEBUG = true;
        }
    }

    @Override
    public void renderScreen(float delta) {
        if(isLoadInit)
        {
            loadWaitTime -= delta;
            if(loadWaitTime < 0)
            {
                gameInst.setScreen(new MainMenuScreen(gameInst));
            }
        }

        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        splashTexture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
