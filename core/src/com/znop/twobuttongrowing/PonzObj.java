package com.znop.twobuttongrowing;

import android.graphics.Rect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public Rectangle bounds;

    public PonzObj()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        bounds = new Rectangle(x, y, w, h);
    }

    public PonzObj(float x1, float y1, float w1, float h1)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
        bounds = new Rectangle(x, y, w, h);
    }
    public PonzObj(Rectangle val)
    {
        x = val.x;
        y = val.y;
        w = val.width;
        h = val.height;
        bounds = new Rectangle(x, y, w, h);
    }

    public boolean IsInside(float xVal, float yVal)
    {
        bounds.x = x;
        bounds.y = y;
        bounds.width = w;
        bounds.height = h;
        return bounds.contains(xVal, yVal);
    }


    public void draw(SpriteBatch sb)
    {
        sb.draw(objTexture, x, y, w, h);
    }
}
