package com.znop.twobuttongrowing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

/**
 * Created by user on 12-12-2015.
 */
public class GameMgr {
    private static GameMgr instance = null;
    private GameMgr()
    {
    }

    public static GameMgr Inst() {
        if(instance == null) {
            instance = new GameMgr();
        }
        return instance;
    }

    // GameState

    // GameScreen

    // Theme
    public static int CurTheme = 0;

    int curWorld = 1; // 1 = black; 2 = white
    int maxWorldCount = 2;

    public AudioMgr audioMgr = new AudioMgr();

    private static int counter = 1;
    public static void saveScreenshot(){
        try{
            FileHandle fh;
            do{
                fh = new FileHandle("screenshot" + counter++ + ".png");
            }while (fh.exists());
            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();
        }catch (Exception e){
        }
    }

    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

        if (yDown) {
            // Flip the pixmap upside down
            ByteBuffer pixels = pixmap.getPixels();
            int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
            pixels.clear();
        }

        return pixmap;
    }

    public void moveToNextWorld()
    {
        curWorld++;
        if(curWorld > maxWorldCount)
            curWorld = 1;
    }

    public int GetCurWorld()
    {
        return curWorld;
    }

    public void SetCurWorld(int worldVal)
    {
        curWorld = worldVal;
    }
}
