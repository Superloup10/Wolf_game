package fr.wolf.game.gameobjects.world;

import fr.wolf.engine.GameObject;

public class Air extends GameObject
{
    public Air(float x, float y, float sizeX, float sizeY)
    {
        init(x, y, 1.0F, 0.5F, 0F, sizeX, sizeY, DEFAULT_ID);
        setSolid(false);
    }
}