package com.znop.twobuttongrowing.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.znop.twobuttongrowing.AssetMgr;
import com.znop.twobuttongrowing.TwoButtonGrowing;

/**
 * Created by @paruthidotexe on 12-12-2015.
 * Note: Use this code as u wish, but dont sell the code.
 *      If any optimizations/ bugs ping me @paruthidotexe
 *      If you become rich by making games, try like me
 *      donating 10% to those people who are in need.
 *      Happy Gaming !!!
 */

public abstract class PonzScreen implements Screen, InputProcessor {

    public TwoButtonGrowing gameInst;

    protected final OrthographicCamera camera = new OrthographicCamera(48, 32);
    protected final SpriteBatch spriteBatch = new SpriteBatch();
    protected final Matrix4 normalProjection = new Matrix4();

    protected boolean stepped = false;
    protected boolean isGL20Available = true;

    private static final int MAX_FPS = 60;
    private static final int MIN_FPS = 15;
    private static final float TIME_STEP = 1f / MAX_FPS;
    private static final float MAX_STEPS = 1f + MAX_FPS / MIN_FPS;
    private static final float MAX_TIME_PER_FRAME = TIME_STEP * MAX_STEPS;
    private float stepTimeLeft;

    public PonzScreen(TwoButtonGrowing game)
    {
        gameInst = game;

        // reset camera
        camera.position.set(0, 0, 0);
        camera.update();

        // set a normal projection matrix
        normalProjection.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Does a fixed timestep independently of framerate.
     *
     * @param delta
     *            Delta time.
     * @return True if stepped, false otherwise.
     */
    private void fixedStep(float delta) {
        stepTimeLeft += delta;
        if (stepTimeLeft > MAX_TIME_PER_FRAME)
            stepTimeLeft = MAX_TIME_PER_FRAME;
        while (stepTimeLeft >= TIME_STEP) {
            updateScreen(TIME_STEP);
            stepTimeLeft -= TIME_STEP;
        }
    }

    /**
     * Updates the game logic.
     *
     * @param fixedStep
     *            A fixed timestep.
     */
    protected abstract void updateScreen(float fixedStep);


    /**
     * Renders the game graphics.
     *
     * @param delta
     *            Delta time.
     */
    public abstract void renderScreen(float delta);

    /**
     * Is the screen in DEBUG mode?
     *
     * @return If screen is in DEBUG mode returns true, otherwise returns false.
     */
    protected boolean isDebug() {
        return TwoButtonGrowing.DEBUG;
    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("Znop", "BaseScreen_render");
        fixedStep(delta);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.totalRenderCalls = 0;

        // render screen graphics
        renderScreen(delta);
/*
        // render fps if in debug mode
        if (isDebug()) {
            spriteBatch.getProjectionMatrix().set(normalProjection);
            spriteBatch.begin();
            //spriteBatch.disableBlending(); //ponz.2do for optimization
            AssetMgr.debugFont.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
            AssetMgr.debugFont.draw(spriteBatch, "DrawCalls: " + spriteBatch.totalRenderCalls, 100, 20);
            spriteBatch.end();
        }
        */
    }


    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    // Input
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
