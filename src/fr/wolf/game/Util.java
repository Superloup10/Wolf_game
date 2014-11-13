package fr.wolf.game;

import fr.wolf.engine.GameObject;

public class Util
{
    public static boolean lineOfSight(GameObject go, GameObject go2)
    {
        return true;
    }

    public static float dist(float x1, float y1, float x2, float y2)
    {
        double x = x2 - x1;
        double y = y2 - y1;
        return (float)Math.sqrt((x * x) + (y * y));
    }
}