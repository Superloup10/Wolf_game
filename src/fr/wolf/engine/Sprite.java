package fr.wolf.engine;

import org.lwjgl.opengl.GL11;

public class Sprite
{
    private float r;// red
    private float g;// green
    private float b;// blue

    private float sx;
    private float sy;

    public Sprite(float r, float g, float b, float sx, float sy)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sx = sx;
        this.sy = sy;
    }

    public void render()
    {
        GL11.glColor3f(r, g, b);

        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex2f(0, 0);
            GL11.glVertex2f(0, sy);
            GL11.glVertex2f(sx, sy);
            GL11.glVertex2f(sx, 0);
        }
        GL11.glEnd();
    }

    public float getSx()
    {
        return sx;
    }

    public void setSx(float sx)
    {
        this.sx = sx;
    }

    public float getSy()
    {
        return sy;
    }

    public void setSy(float sy)
    {
        this.sy = sy;
    }
}