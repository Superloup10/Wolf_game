package fr.wolf.moteur.collision.entity;

import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.wolf.moteur.collision.utils.AABB;
import fr.wolf.moteur.collision.utils.Vector;
import fr.wolf.world.World;

public class Tile
{
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;

	public AABB aabb;

	public Vector position = new Vector();

	public boolean isSolid;

	public Tile(int x, int y)
	{
		this.position.x = x;
		this.position.y = y;
		this.isSolid = true;
		aabb = new AABB(TILE_WIDTH, TILE_HEIGHT);
	}

	public void render()
	{
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0f, 1, 1f);
		GL11.glVertex2f(this.position.x - TILE_WIDTH, this.position.y - TILE_HEIGHT);
		GL11.glVertex2f(this.position.x + TILE_WIDTH, this.position.y - TILE_HEIGHT);
		GL11.glVertex2f(this.position.x + TILE_WIDTH, this.position.y + TILE_HEIGHT);
		GL11.glVertex2f(this.position.x - TILE_WIDTH, this.position.y + TILE_HEIGHT);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void init()
	{
		aabb.update(position);
	}

	public boolean isSolid()
	{
		return isSolid;
	}

	public void setSolid(boolean isSolid)
	{
		this.isSolid = isSolid;
	}

	public static Tile getTileAt(int x, int y, World world)
	{
		List<Tile> tiles = world.tiles;
		for(int i = 0; i < tiles.size(); i++)
		{
			if(tiles.get(i).position.x == x && tiles.get(i).position.y == y)
			{
				return tiles.get(i);
			}
		}
		return null;
	}

	public static Tile getTile(Tile tileAt)
	{
		return tileAt;
	}
}