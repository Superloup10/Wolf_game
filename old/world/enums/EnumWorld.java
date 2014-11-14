package fr.wolf.world.enums;

public enum EnumWorld
{
    SMALL(0, "options.world.small"), MEDIUM(1, "options.world.medium"), LARGE(2, "options.wordl.large");

    private static EnumWorld worldEnums[] = new EnumWorld[values().length];

    private final int worldId;
    private final String worldResourceKey;

    private EnumWorld(int i, String string)
    {
        this.worldId = i;
        this.worldResourceKey = string;
    }

    public int getWorldId()
    {
        return worldId;
    }

    public static EnumWorld getWorldEnums(int i)
    {
        return worldEnums[i % worldEnums.length];
    }

    public String getWorldResourceKey()
    {
        return worldResourceKey;
    }

    static
    {
        EnumWorld[] world = values();
        int i = world.length;
        for(int i2 = 0; i2 < i; ++i2)
        {
            EnumWorld enumWorld = world[i2];
            worldEnums[enumWorld.worldId] = enumWorld;
        }
    }
}