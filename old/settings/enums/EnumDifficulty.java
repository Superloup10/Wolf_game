package fr.wolf.settings.enums;

public enum EnumDifficulty
{
    EASY(0, "options.difficulty.easy"), NORMAL(1, "options.difficulty.normal"), HARD(2, "options.difficulty.hard");

    private static EnumDifficulty[] difficultyEnums = new EnumDifficulty[values().length];

    private final int difficultyId;
    private final String difficultyResourceKey;

    private EnumDifficulty(int i, String string)
    {
        this.difficultyId = i;
        this.difficultyResourceKey = string;
    }

    public int getDifficultyId()
    {
        return difficultyId;
    }

    public static EnumDifficulty getDifficultyEnum(int i)
    {
        return difficultyEnums[i % difficultyEnums.length];
    }

    public String getDifficultyResourceKey()
    {
        return difficultyResourceKey;
    }

    static
    {
        EnumDifficulty[] difficulties = values();
        int i = difficulties.length;

        for(int i2 = 0; i2 < i; ++i2)
        {
            EnumDifficulty enumDifficulty = difficulties[i2];
            difficultyEnums[enumDifficulty.difficultyId] = enumDifficulty;
        }
    }
}