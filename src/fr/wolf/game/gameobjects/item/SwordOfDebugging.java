package fr.wolf.game.gameobjects.item;

public class SwordOfDebugging extends EquippableItem
{
    private static final float SIZE = 32;

    private int damage;

    public SwordOfDebugging(float x, float y)
    {
        init(x, y, 1.0F, 0.5F, 0F, SIZE, SIZE, "The Legendary Sword Of Debugging", WEAPON_SLOT);
        damage = 3;
    }
}