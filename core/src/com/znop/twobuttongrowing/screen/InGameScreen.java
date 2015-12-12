package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.znop.twobuttongrowing.AssetMgr;
import com.znop.twobuttongrowing.PonzObj;
import com.znop.twobuttongrowing.TwoButtonGrowing;

/**
 * Created by user on 12-12-2015.
 */
public class InGameScreen extends PonzScreen{

    private OrthographicCamera camera;

    private PonzObj playerWhite;
    private PonzObj playerBlack;

    private PonzObj coinBlack;
    private PonzObj coinWhite;

    // HUD
    private PonzObj homeBtn;
    private PonzObj pauseBtn;

    public InGameScreen(TwoButtonGrowing game)
    {
        super(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Rectangle curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = Gdx.graphics.getWidth() / 2 -  curRect.width / 2;
        curRect.y = Gdx.graphics.getHeight() / 2 - curRect.height / 2;
        playerWhite = new PonzObj(curRect);

        curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = Gdx.graphics.getWidth() / 2 -  curRect.width / 2;
        curRect.y = Gdx.graphics.getHeight() / 2 - curRect.height / 2 + curRect.height;
        playerBlack = new PonzObj(curRect);

        curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = Gdx.graphics.getWidth() / 2 -  curRect.width / 2;
        curRect.y = 0;
        coinBlack = new PonzObj(curRect);

        curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = Gdx.graphics.getWidth() / 2 -  curRect.width / 2;
        curRect.y = Gdx.graphics.getHeight() - curRect.height ;
        coinWhite = new PonzObj(curRect);

        curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = 0;
        curRect.y = Gdx.graphics.getHeight() - curRect.height ;
        homeBtn = new PonzObj(curRect);
        homeBtn.objTexture = AssetMgr.Inst().GetTexture("Home");

        curRect = new Rectangle();
        curRect.width = 32;
        curRect.height = 32;
        curRect.x = curRect.width / 2 + curRect.width;
        curRect.y = Gdx.graphics.getHeight() - curRect.height ;
        pauseBtn = new PonzObj(curRect);
        pauseBtn.objTexture = AssetMgr.Inst().GetTexture("Pause");

        Gdx.input.setInputProcessor(this);
    }


    @Override
    protected void updateScreen(float fixedStep) {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerWhite.x -= 200 * Gdx.graphics.getDeltaTime();
            playerBlack.x -= 200 * Gdx.graphics.getDeltaTime();
            //GameMgr.Inst().audioMgr.PlaySFX(AssetMgr.Inst().GetSound("TouchSFX"));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            playerWhite.x += 200 * Gdx.graphics.getDeltaTime();
            playerBlack.x += 200 * Gdx.graphics.getDeltaTime();
            //GameMgr.Inst().audioMgr.PlaySFX(AssetMgr.Inst().GetSound("TouchSFX"));
        }

        coinWhite.y -= fixedStep * 50;
        coinBlack.y += fixedStep * 50;
        if(coinBlack.y > Gdx.graphics.getHeight() + coinBlack.h * 5)
            coinBlack.y = 0;
        if(coinWhite.y < 0)
            coinWhite.y = Gdx.graphics.getHeight();
    }

    @Override
    public void renderScreen(float delta) {
        spriteBatch.begin();

        spriteBatch.draw(AssetMgr.Inst().GetTexture("White"),
                playerWhite.x, playerWhite.y,
                playerWhite.w, playerWhite.h);

        spriteBatch.draw(AssetMgr.Inst().GetTexture("Black"),
                playerBlack.x, playerBlack.y,
                playerBlack.w, playerBlack.h);

        spriteBatch.draw(AssetMgr.Inst().GetTexture("White"),
                coinWhite.x, coinWhite.y,
                coinWhite.w, coinWhite.h);

        spriteBatch.draw(AssetMgr.Inst().GetTexture("Black"),
                coinBlack.x, coinBlack.y,
                coinBlack.w, coinBlack.h);

        // HUD
        homeBtn.draw(spriteBatch);
        pauseBtn.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        Gdx.app.debug("ANY", "SPACE");
        switch (keycode) {
            case Input.Keys.SPACE:
                float tmpVal =  playerBlack.y;
                playerBlack.y = playerWhite.y;
                playerWhite.y = tmpVal;
                Gdx.app.debug("SPACE", "SPACE");
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
        if(homeBtn.IsInside(screenX, Gdx.graphics.getHeight() - screenY))
        {
            gameInst.setScreen(new MainMenuScreen(gameInst));
        }
        else if(pauseBtn.IsInside(screenX, Gdx.graphics.getHeight() - screenY))
        {

        }
        else {
            float tmpVal = playerBlack.y;
            playerBlack.y = playerWhite.y;
            playerWhite.y = tmpVal;
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
