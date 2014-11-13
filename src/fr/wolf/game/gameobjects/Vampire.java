package fr.wolf.game.gameobjects;

public class Vampire extends Enemy
{
    public static final int SIZE = 32;

    public Vampire(float x, float y, int level)
    {
        super(level);
        this.init(x, y, 0.2F, 0.2F, 1.0F, SIZE, SIZE, 0);
        setAttackDelay(200);
    }
}