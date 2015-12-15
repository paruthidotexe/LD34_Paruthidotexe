package com.znop.twobuttongrowing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by user on 12-12-2015.
 */
public class PonzObj {
    public float x;
    public float y;
    public float w;
    public float h;
    public Rectangle bounds;
    public Texture objTexture;
    public boolean dynamicTexture = false;
    public int worldId;
    public Texture world1Texture;
    public Texture world2Texture;

    public Body body;
    boolean isPhysicsEnabled = false;

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


    public void EnablePhysics(World world, BodyDef.BodyType bodyDeftype)
    {
        // body def
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyDeftype;
        bodyDef.position.set((x + w / 2)/GameConst.PIXELS_TO_METERS, (y + h / 2)/GameConst.PIXELS_TO_METERS);
        body = world.createBody(bodyDef);

        // shape~
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(w/2/GameConst.PIXELS_TO_METERS, h/2/GameConst.PIXELS_TO_METERS);

        // fixture Def
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.05f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);

        isPhysicsEnabled = true;

        boxShape.dispose();
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
        if(dynamicTexture)
        {
            if(GameMgr.Inst().curWorld == 1 && world1Texture != null)
            {
                if(isPhysicsEnabled)
                {
                    x =  body.getPosition().x * GameConst.PIXELS_TO_METERS - w / 2;
                    y = body.getPosition().y * GameConst.PIXELS_TO_METERS - h / 2;
                }
                sb.draw(world1Texture, x, y, w, h);
            }
            else if(GameMgr.Inst().curWorld == 2 && world2Texture != null)
            {
                if(isPhysicsEnabled)
                {
                    x =  body.getPosition().x * GameConst.PIXELS_TO_METERS - w / 2;
                    y = body.getPosition().y * GameConst.PIXELS_TO_METERS - h / 2;
                }
                sb.draw(world2Texture, x, y, w, h);
            }
        }
        else {
            if(objTexture != null)
                sb.draw(objTexture, x, y, w, h);
        }
    }


    public void UpdatePhysics()
    {
        if(GameMgr.Inst().curWorld == worldId) {
            isPhysicsEnabled = true;
            body.setActive(true);
        }
        else {
            isPhysicsEnabled = false;
            body.setActive(false);
        }
    }
}
