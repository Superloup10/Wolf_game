package fr.wolf.engine;

import org.lwjgl.opengl.GL11;

public class Sprite
{
    private float r;// red
    private float g;// green
    private float b;// blue

    private float sizeX;
    private float sizeY;

    public Sprite(float r, float g, float b, float sx, float sy)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sizeX = sx;
        this.sizeY = sy;
    }

    public void render()
    {
        GL11.glColor3f(r, g, b);

        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex2f(0, 0);
            GL11.glVertex2f(0, sizeY);
            GL11.glVertex2f(sizeX, sizeY);
            GL11.glVertex2f(sizeX, 0);
        }
        GL11.glEnd();
    }

    public float getSizeX()
    {
        return sizeX;
    }

    public void setSizeX(float size)
    {
        this.sizeX = size;
    }

    public float getSizeY()
    {
        return sizeY;
    }

    public void setSizeY(float size)
    {
        this.sizeY = size;
    }
}