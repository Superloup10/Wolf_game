package fr.wolf.game.utils;

public class MathsHelper
{
    public static boolean betweenS(double x1, double x2, double xCompare)
    {
        if(x1 < xCompare && xCompare < x2)
            return true;
        return false;
    }

    public static boolean between(double x1, double x2, double xCompare)
    {
        if(x1 <= xCompare && xCompare <= x2)
            return true;
        return false;
    }
}