package fr.wolf.game.gameobjects.item;

public class Cube extends Item
{
    public static final float SIZE = 32;

    public Cube(float x, float y)
    {
        init(x, y, 1.0F, 0.5F, 0F, SIZE, SIZE, "The Cube");
    }
}