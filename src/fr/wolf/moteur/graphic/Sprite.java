package fr.wolf.moteur.graphic;

public class Sprite
{
	public SpriteSheet sheet;
	public int x, y;
	public int size;

	//public static Sprite cobblestone, dirt;

	public Sprite(SpriteSheet sheet, int x, int y, int size)
	{
		this.sheet = sheet;
		this.x = x;
		this.y = y;
		this.size = 32;
	}

	public Sprite(SpriteSheet sheet, int x, int y)
	{
		this(sheet, x, y, 32); // The default sprite size is 32
	}

	public static void init()
	{
		//cobblestone = new Sprite(SpriteSheet.tiles, 0, 0);
		//dirt = new Sprite(SpriteSheet.tiles, 1, 0);
	}
}