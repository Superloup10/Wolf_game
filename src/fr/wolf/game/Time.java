package fr.wolf.game;

public class Time
{
    public static final float DAMPING = 18000000F;

    private static long curTime, lastTime;

    public static long getTime()
    {
        return System.nanoTime();
    }

    public static float getDelta()
    {
        return (curTime - lastTime) / DAMPING;
    }

    public static void update()
    {
        lastTime = curTime;
        curTime = getTime();
    }

    public static void init()
    {
        lastTime = getTime();
        curTime = getTime();
    }
}