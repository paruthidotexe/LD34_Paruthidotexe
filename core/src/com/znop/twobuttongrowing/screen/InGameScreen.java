package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.znop.twobuttongrowing.*;

import java.util.Random;

/**
 * Created by @paruthidotexe on 12-12-2015.
 * Note: Use this code as u wish, but don't sell the code, let it be free like libgdx.
 *      If any optimizations/ bugs ping me @paruthidotexe
 *      If you become rich by making games, try like me
 *      donating 10% to those people who are in need.
 *      Happy Gaming !!!
 */

public class InGameScreen extends PonzScreen{

    private OrthographicCamera bgCam;
    private OrthographicCamera gameCam;
    private OrthographicCamera hudCam;
    Viewport viewport;

    private PonzObj player;
    float playerSpeed = 2;

    private PonzObj coinBlack;
    private PonzObj coinWhite;

    private PonzObj parallax_03;
    private PonzObj parallax_05;
    private PonzObj parallax_08;
    private PonzObj parallax_09;

    // side scrolling
    float camOffset = 0;

    int blockTileCount = 10240;
    PonzObj[] Block1;
    PonzObj[] Block2;
    PonzObj[] Block3;

    PonzObj[] BgTiles;

    // HUD
    private PonzObj homeBtn;
    private PonzObj pauseBtn;
    BitmapFont playerType;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // physics
    World world;
    Box2DDebugRenderer debugRenderer;
    Matrix4 b2dDebugMatrix;
    Vector2 gravityVal = new Vector2(0, -10f);
    boolean doSleep = true;
    float pixelToMeters = 128;
    float TimeStep = 1 / 60f;
    int VelocityItreation = 8;
    int PositionItreation = 3;

    boolean gameStarted = false;
    float time = 2;
    BitmapFont helpMsg;
    float curSpeed = 4.0f;

    public InGameScreen(TwoButtonGrowing game)
    {
        super(game);
        // bg camera
        bgCam = new OrthographicCamera();
        bgCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // game cam
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //gameCam.translate(gameCam.viewportWidth / 2, gameCam.viewportHeight/2);
        // viewport = new FillViewport(1024, 512, gameCam);
        // viewport.apply();
        gameCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        gameCam.rotate(-10);

        // Hud Cam
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Box2d
        Box2D.init();

        world = new World(gravityVal, doSleep);
        debugRenderer = new Box2DDebugRenderer();

        Rectangle curRect = new Rectangle();
        curRect.width = 64;
        curRect.height = 64;
        curRect.x = Gdx.graphics.getWidth()/2 - curRect.width/2;
        curRect.y = Gdx.graphics.getHeight()/2 - curRect.height/2;
        player = new PonzObj(curRect);
        player.dynamicTexture = true;
        player.world1Texture = AssetMgr.Inst().GetTexture("Yin");
        player.world2Texture = AssetMgr.Inst().GetTexture("Yang");
        player.EnablePhysics(world, BodyDef.BodyType.DynamicBody);
        player.worldId = GameMgr.Inst().GetCurWorld();

        camOffset = gameCam.position.x - player.x;

        curRect = new Rectangle();
        curRect.width = Gdx.graphics.getWidth();
        curRect.height = Gdx.graphics.getHeight();
        curRect.x = 0;
        curRect.y = 0;
        parallax_03 = new PonzObj(curRect);
        parallax_03.objTexture = AssetMgr.Inst().GetTexture("BG_2");

        curRect = new Rectangle();
        curRect.width = Gdx.graphics.getWidth();
        curRect.height = Gdx.graphics.getHeight()/3;
        curRect.x = 0;
        curRect.y = 0;
        parallax_05 = new PonzObj(curRect);
        parallax_05.objTexture = AssetMgr.Inst().GetTexture("parallax_05");

        Block1 = new PonzObj[blockTileCount];
        int curColor = 1;
        int nextTileCount = 16;
        for(int i = 0; i < blockTileCount;i++)
        {
            curRect = new Rectangle();
            curRect.width = 64;
            curRect.height = 32;
            curRect.x = curRect.width + i * curRect.width;
            curRect.y = 64;
            Block1[i] = new PonzObj(curRect);
            Block1[i].dynamicTexture = false;
            Random rand = new Random();
            if(i >= nextTileCount)
            {
                //curColor = 1 + rand.nextInt()%2;
                nextTileCount = i + 5 + rand.nextInt(5);
                if(curColor == 1)
                    curColor = 2;
                else if(curColor == 2)
                    curColor = 1;
            }
            if(curColor == 1) {
                Block1[i].objTexture = AssetMgr.Inst().GetTexture("Black");
            }
            else
                Block1[i].objTexture = AssetMgr.Inst().GetTexture("White");
            Block1[i].worldId = curColor;
            Block1[i].EnablePhysics(world, BodyDef.BodyType.StaticBody);
            Block1[i].UpdatePhysics();
        }

        BgTiles = new PonzObj[64];
        int index = 0;
        for(int i = 0; i < 8;i++) {
            for(int j = 0; j < 8;j++) {
                curRect = new Rectangle();
                curRect.width = 128;
                curRect.height = 128;
                curRect.x = i * curRect.width;
                curRect.y = j * curRect.height;
                BgTiles[index] = new PonzObj(curRect);
                BgTiles[index].dynamicTexture = true;
                BgTiles[index].world1Texture = AssetMgr.Inst().GetTexture("Black");
                BgTiles[index].world2Texture = AssetMgr.Inst().GetTexture("White3");
                index++;
            }
        }

        // HUD
        curRect = new Rectangle();
        curRect.width = 64;
        curRect.height = 64;
        curRect.x = Gdx.graphics.getWidth() - curRect.width;
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

        playerType = AssetMgr.HeadingFont;
        helpMsg = AssetMgr.HeadingFont;

        gameStarted = false;

        Gdx.input.setInputProcessor(this);
    }


    @Override
    protected void updateScreen(float fixedStep) {
        if(gameStarted)
            player.body.setLinearVelocity(curSpeed, player.body.getLinearVelocity().y);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameCam.translate(-10, 0);
            //playerWhite.x -= 200 * Gdx.graphics.getDeltaTime();
            //playerBlack.x -= 200 * Gdx.graphics.getDeltaTime();
            //GameMgr.Inst().audioMgr.PlaySFX(AssetMgr.Inst().GetSound("TouchSFX"));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            gameCam.translate(10, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            gameCam.translate(0, 10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            gameCam.translate(0, -10);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            SetCurWorld(1);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            SetCurWorld(2);
        }
        if(player.y < -300)
        {
            gameInst.setScreen(new MainMenuScreen(gameInst));
        }
    }

    @Override
    public void renderScreen(float delta) {

        time -= delta;
        if(time < 0)
        {
            gameStarted = true;
        }

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bgCam.update();
        spriteBatch.setProjectionMatrix(bgCam.combined);
        spriteBatch.begin();
        for(int i = 0; i < 64;i++) {
            BgTiles[i].draw(spriteBatch);
        }
        spriteBatch.end();


        gameCam.position.set(new Vector3(player.x, player.y, 0));
        gameCam.update();
        spriteBatch.setProjectionMatrix(gameCam.combined);
        b2dDebugMatrix = spriteBatch.getProjectionMatrix().cpy().scale(
                GameConst.PIXELS_TO_METERS, GameConst.PIXELS_TO_METERS, 0);
        spriteBatch.begin();

        for(int i = 0; i < blockTileCount;i++)
        {
            Block1[i].draw(spriteBatch);
        }

        player.draw(spriteBatch);

        spriteBatch.end();

        // box2d debug rennderer
        //debugRenderer.render(world, b2dDebugMatrix);

        // HUD
        hudCam.update();
        spriteBatch.setProjectionMatrix(hudCam.combined);
        spriteBatch.begin();
        homeBtn.draw(spriteBatch);
        //pauseBtn.draw(spriteBatch);
        if(GameMgr.Inst().GetCurWorld() == 1)
        {
            playerType.getData().setScale(2f, 2f);
            playerType.draw(spriteBatch, "Yin", Gdx.graphics.getWidth()/2 - 64, Gdx.graphics.getHeight() - 8);
        }
        else if(GameMgr.Inst().GetCurWorld() == 2)
        {
            playerType.getData().setScale(2f, 2f);
            playerType.draw(spriteBatch, "Yang", Gdx.graphics.getWidth()/2 - 64, Gdx.graphics.getHeight() - 8);
        }
        helpMsg.getData().setScale(1, 1);
        helpMsg.draw(spriteBatch, " Press A for Yin \n Press D for Yang \n Happy Gaming",
                16, Gdx.graphics.getHeight()-16);
        helpMsg.getData().setScale(1, 1);
        spriteBatch.end();
        world.step(TimeStep, VelocityItreation, PositionItreation);
    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode) {
            case Input.Keys.SPACE:
                GameMgr.Inst().moveToNextWorld();
                UpdateWorldChange();
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
        Gdx.app.log("touchDown", screenX + ", " + screenY + "] NEW [" +
                gameCam.position.x + screenX + ", " + gameCam.position.y + screenY);
        if(homeBtn.IsInside(screenX, Gdx.graphics.getHeight() - screenY))
        {
            gameInst.setScreen(new MainMenuScreen(gameInst));
        }
        else if(pauseBtn.IsInside(screenX, Gdx.graphics.getHeight() - screenY))
        {

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

    @Override
    public void dispose()
    {
        world.dispose();
        debugRenderer.dispose();
    }


    void SetCurWorld(int worldVal)
    {
        GameMgr.Inst().SetCurWorld(worldVal);
        UpdateWorldChange();
    }


    void UpdateWorldChange()
    {
        for(int i = 0; i < blockTileCount;i++)
        {
            Block1[i].UpdatePhysics();
        }
    }

}
