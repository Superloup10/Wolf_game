package fr.wolf.game.gameobjects.item;

import fr.wolf.engine.GameObject;
import fr.wolf.engine.Sprite;

public class Item extends GameObject
{
    protected String name;

    public String getName()
    {
        return name;
    }

    protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
    {
        this.x = x;
        this.y = y;
        this.type = ITEM_ID;
        this.name = name;
        this.spr = new Sprite(r, g, b, sx, sy);
    }
}