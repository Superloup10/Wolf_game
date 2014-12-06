package fr.wolf.engine;

import java.awt.Rectangle;

public class Physics
{
    public static GameObject checkCollision(GameObject go1, GameObject go2)
    {
        return checkCollision(new Rectangle((int)go1.getX(), (int)go1.getY(), (int)go1.getSizeX(), (int)go1.getSizeY()), go2);
    }

    public static GameObject checkCollision(Rectangle r1, GameObject go2)
    {
        Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)go2.getSizeX(), (int)go2.getSizeY());

        boolean res = r1.intersects(r2);
        if(res)
            return go2;
        else
            return null;
    }
}