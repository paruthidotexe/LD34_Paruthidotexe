package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.znop.twobuttongrowing.AssetMgr;
import com.znop.twobuttongrowing.GameMgr;
import com.znop.twobuttongrowing.PonzObj;
import com.znop.twobuttongrowing.TwoButtonGrowing;

/**
 * Created by user on 12-12-2015.
 */
public class MainMenuScreen extends PonzScreen{

    PonzObj playBtn;

    PonzObj Bg_0;
    PonzObj Bg_1;
    PonzObj Bg_2;

    private BitmapFont HeadingFont;
    BitmapFont authorText;

    public MainMenuScreen(TwoButtonGrowing game)
    {
        super(game);

        playBtn = new PonzObj();
        playBtn.w = 128;
        playBtn.h = 128;
        playBtn.x = Gdx.graphics.getWidth() - playBtn.w - 32;
        playBtn.y = 32;

        Bg_0 = new PonzObj();
        Bg_0.w = Gdx.graphics.getWidth();
        Bg_0.h = Gdx.graphics.getHeight();
        Bg_0.x = Gdx.graphics.getWidth() / 2 - Bg_0.w / 2;
        Bg_0.y = Gdx.graphics.getHeight() / 2 - Bg_0.h / 2;
        Bg_0.objTexture = AssetMgr.Inst().GetTexture("BG_0");

        Bg_1 = new PonzObj();
        Bg_1.w = Gdx.graphics.getWidth();
        Bg_1.h = Gdx.graphics.getHeight();
        Bg_1.x = Gdx.graphics.getWidth() / 2 - Bg_1.w / 2;
        Bg_1.y = Gdx.graphics.getHeight() / 2 - Bg_1.h / 2;
        Bg_1.objTexture = AssetMgr.Inst().GetTexture("BG_1");

        Bg_2 = new PonzObj();
        Bg_2.w = Gdx.graphics.getWidth() ;
        Bg_2.h = Gdx.graphics.getHeight();
        Bg_2.x = Gdx.graphics.getWidth() / 2 - Bg_2.w / 2;
        Bg_2.y = Gdx.graphics.getHeight() / 2 - Bg_2.h / 2;
        Bg_2.objTexture = AssetMgr.Inst().GetTexture("BG_2");

        HeadingFont = AssetMgr.HeadingFont;
        authorText = AssetMgr.debugFont;

        // reset to yin world
        GameMgr.Inst().SetCurWorld(1);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void updateScreen(float fixedStep) {

    }

    @Override
    public void renderScreen(float delta) {
        spriteBatch.begin();

        switch (GameMgr.CurTheme) {
            case 0:
                Bg_0.draw(spriteBatch);
                break;
            case 1:
                Bg_1.draw(spriteBatch);
                break;
            case 2:
                Bg_2.draw(spriteBatch);
                break;

            default:
                Bg_0.draw(spriteBatch);
                break;
        }

        spriteBatch.draw(AssetMgr.Inst().GetTexture("Play"),
                playBtn.x, playBtn.y, playBtn.w, playBtn.h);

        HeadingFont.getData().setScale(1f, 1f);
        HeadingFont.draw(spriteBatch, "Yin and Yang !!!", Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() - 128);

        authorText.getData().setScale(1.5f, 1.5f);
        authorText.draw(spriteBatch, " Developer : \n @paruthidotexe", 0, 64);
        authorText.getData().setScale(1,1);
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                GameMgr.CurTheme = 0;
                break;
            case Input.Keys.NUM_2:
                GameMgr.CurTheme = 1;
                break;
            case Input.Keys.NUM_3:
                GameMgr.CurTheme = 2;
                break;
            case Input.Keys.SPACE:
                OnPlayBtn();
                break;
        }
        return true;

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
            OnPlayBtn();
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


    void OnPlayBtn()
    {
        gameInst.setScreen(new InGameScreen(gameInst));
    }

}
