package fr.wolf.game.gameobjects;

public class Stats
{
    // public static final double LEVEL_CONST = 25.0D * Math.pow(3.0D, (3.0D / 2.0D)); OLD LEVEL CONSTANT
    public static final int MAX_XP = 999999;
    public static final int MAX_LEVEL = 99;
    public static final double LEVEL_CONST = (double)MAX_XP / ((double)MAX_LEVEL * (double)MAX_LEVEL);

    private StatScale scale;
    private float xp;
    private int level, health;
    private boolean levelable;

    public Stats(float xp, boolean levelable)
    {
        this.levelable = levelable;
        scale = new StatScale();
        // /!\WARNING: Entering the land of the temporary code
        scale.generateStatScale();
        // Now leaving the land of the temporary code

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

    public int getLevel()
    {
        if(!levelable)
            return level;

        return (int)Math.sqrt((double)xp / LEVEL_CONST) + 1;
        /*
         * OLD LEVELING FORMULA double x = xp + 105.0D; double a = Math.sqrt(243.0D * (x * x) + 4050.0D * x + 17500.0D); double c = (3.0D * x + 25.0D) / 25.0D; double d = Math.cbrt(a / LEVEL_CONST + c); return (int)(d - 1.0 / d * 3.0) - 1;
         */
    }

    public int getCurrentHealth()
    {
        int max = getMaxHealth();
        if(health > max)
            health = max;

        return health;
    }

    public int getMaxHealth()
    {
        return (int)(getLevel() * scale.getScale(StatScale.VITALITY) * 10);
    }

    public float getSpeed()
    {
        return (float)(getLevel() * scale.getScale(StatScale.SPEED) * 10);
    }

    public float getStrength()
    {
        return (float)(getLevel() * scale.getScale(StatScale.STRENGTH) * 10);
    }

    public float getPhysicalDefence()
    {
        return (float)(getLevel() * scale.getScale(StatScale.PHYSICAL_DEFENSE) * 10);
    }

    public float getMagic()
    {
        return (float)(getLevel() * scale.getScale(StatScale.MAGIC) * 10);
    }

    public float getMagicDefence()
    {
        return (float)(getLevel() * scale.getScale(StatScale.MAGIC_DEFENCE) * 10);
    }

    public void addXP(float amount)
    {
        xp += amount;
        if(xp > MAX_XP)
            xp = MAX_XP;
    }

    public void damage(int amt)
    {
        health -= amt;
    }
}