package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.znop.twobuttongrowing.AssetMgr;
import com.znop.twobuttongrowing.PonzObj;
import com.znop.twobuttongrowing.TwoButtonGrowing;

/**
 * Created by user on 12-12-2015.
 */
public class MainMenuScreen extends PonzScreen{

    PonzObj playBtn;

    public MainMenuScreen(TwoButtonGrowing game)
    {
        super(game);

        playBtn = new PonzObj();
        playBtn.w = 64;
        playBtn.h = 64;
        playBtn.x = Gdx.graphics.getWidth() / 2 - playBtn.w / 2;
        playBtn.y = Gdx.graphics.getHeight() / 2 - playBtn.h / 2;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void updateScreen(float fixedStep) {

    }

    @Override
    public void renderScreen(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(AssetMgr.Inst().GetTexture("Play"),
                            playBtn.x, playBtn.y, playBtn.w, playBtn.h);
        spriteBatch.end();
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
        if(playBtn.IsInside(screenX, Gdx.graphics.getHeight() - screenY))
        {
            gameInst.setScreen(new InGameScreen(gameInst));
        }
        else
        {
            Gdx.app.log("touchdown", "[" + screenX + ", " + screenY + "][" +
            playBtn.x + ", " + playBtn.y + ", " + (playBtn.x + playBtn.w) + ", " + (playBtn.y + playBtn.h) + "]");
        }
        return true;
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
