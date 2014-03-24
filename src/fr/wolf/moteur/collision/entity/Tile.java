package fr.wolf.moteur.collision.entity;

import fr.wolf.moteur.graphic.Renderer;
import fr.wolf.moteur.graphic.Sprite;

public class Tile
{
	private int x, y;
	private Sprite sprite;
	private int ID;
	public static Tile[] allTiles = new Tile[2]; // La valeur dépend du nombre de vos tiles

	// public static Tile cobblestone, dirt;

	public Tile(int ID, Sprite sprite)
	{
		this.ID = ID;
		this.sprite = sprite;
		allTiles[this.ID] = this;
	}

	public Tile(int ID, Sprite sprite, int x, int y)
	{
		this.ID = ID;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	public void render(int a, int b)
	{
		Renderer.renderTexturedSquare(sprite, a, b);
	}

	public static Tile getInstance(Tile tile, int x, int y)
	{
		return new Tile(tile.ID, tile.sprite, x, y);
	}

	public void init() {
		// cobblestone = new Tile(0, Sprite.cobblestone);
		// dirt = new Tile(1, Sprite.dirt);
	}
}