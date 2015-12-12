package com.znop.twobuttongrowing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by user on 12-12-2015.
 */
public class PonzObj {
    public float x;
    public float y;
    public float w;
    public float h;
    public Texture objTexture;

    public PonzObj()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    public PonzObj(float x1, float y1, float w1, float h1)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
    }
    public PonzObj(Rectangle val)
    {
        x = val.x;
        y = val.y;
        w = val.width;
        h = val.height;
    }
}
