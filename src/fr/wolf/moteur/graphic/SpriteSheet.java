package fr.wolf.moteur.graphic;

public class SpriteSheet
{
    public Texture texture;
    public static SpriteSheet tiles;

    // All the sprite sheets listed here.
    public SpriteSheet(String path)
    {
        this.texture = Texture.loadTexture(path);
    }

    public static void init()
    {
        tiles = new SpriteSheet("/textures/tilessheet.png");
    }
}