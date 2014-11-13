package fr.wolf.game.gameobjects;

public class Stats
{
    public static final double LEVEL_CONST = 25.0D * Math.pow(3.0D, (3.0D / 2.0D));

    private float xp;
    private int level, health;
    private boolean levelable;

    public Stats(float xp, boolean levelable)
    {
        this.levelable = levelable;

        if(levelable)
        {
            this.xp = xp;
            this.level = 1;
        }
        else
        {
            this.xp = -1;
            this.level = (int)xp;
        }

        this.health = getMaxHealth();
    }

    public float getSpeed()
    {
        return 4.0F;
    }

    public int getCurrentHealth()
    {
        int max = getMaxHealth();
        if(health > max)
            health = max;

        return health;
    }

    public int getLevel()
    {
        if(!levelable)
            return level;

        double x = xp + 105.0D;

        double a = Math.sqrt(243.0D * (x * x) + 4050.0D * x + 17500.0D);
        double c = (3.0D * x + 25.0D) / 25.0D;
        double d = Math.cbrt(a / LEVEL_CONST + c);

        return (int)(d - 1.0 / d * 3.0) - 1;
    }

    public int getMaxHealth()
    {
        return getLevel() * 10;
    }

    public float getStrength()
    {
        return getLevel() * 4F;
    }

    public float getMagic()
    {
        return getLevel() * 4F;
    }

    public void addXP(float amount)
    {
        xp += amount;
    }

    public void damage(int amt)
    {
        health -= amt;
    }
}