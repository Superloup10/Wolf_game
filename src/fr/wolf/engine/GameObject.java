package fr.wolf.engine;

import org.lwjgl.opengl.GL11;

public abstract class GameObject
{
    public static final int DEFAULT_ID = 0;
    public static final int ITEM_ID = 1;
    public static final int PLAYER_ID = 2;
    public static final int ENEMY_ID = 3;

    protected float x;
    protected float y; // extends vers z plus tard
    protected int type;
    protected Sprite spr;

    protected boolean[] flags = new boolean[2];

    public void update()
    {

    }

    public void render()
    {
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(x, y, 0);
            spr.render();
        }
        GL11.glPopMatrix();
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getSy()
    {
        return spr.getSy();
    }

    public float getSx()
    {
        return spr.getSx();
    }

    public int getType()
    {
        return type;
    }

    public boolean isRemove()
    {
        return flags[0];
    }

    public boolean isSolid()
    {
        return flags[1];
    }

    public void remove()
    {
        flags[0] = true;
    }

    public void setSolid(boolean value)
    {
        flags[1] = value;
    }

    protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.spr = new Sprite(r, g, b, sx, sy);
    }
}