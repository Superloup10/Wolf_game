package fr.wolf.game.gameobjects.item;

import fr.wolf.engine.GameObject;

public class Wall extends GameObject
{
    public Wall(float x, float y, float sizeX, float sizeY)
    {
        init(x, y, 1.0F, 0.5F, 0F, sizeX, sizeY, DEFAULT_ID);
        setSolid(true);
    }
}