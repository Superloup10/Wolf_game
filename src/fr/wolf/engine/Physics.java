package fr.wolf.engine;

import java.awt.Rectangle;

public class Physics
{
    public static GameObject checkCollision(GameObject go1, GameObject go2)
    {
        return checkCollision(new Rectangle((int)go1.getX(), (int)go1.getY(), (int)go1.getSx(), (int)go1.getSy()), go2);
    }

    public static GameObject checkCollision(Rectangle r1, GameObject go2)
    {
        Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)go2.getSx(), (int)go2.getSy());

        boolean res = r1.intersects(r2);
        if(res)
            return go2;
        else
            return null;
    }
}