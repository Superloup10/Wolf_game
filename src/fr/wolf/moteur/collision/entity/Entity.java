package fr.wolf.moteur.collision.entity;

import java.util.ArrayList;
import java.util.List;

import fr.wolf.Wolf;
import fr.wolf.World;
import fr.wolf.moteur.collision.utils.AABB;
import fr.wolf.moteur.collision.utils.CollisionLibrary;
import fr.wolf.moteur.collision.utils.Vector;

public abstract class Entity
{
	protected World world = Wolf.world;
	protected Vector position = new Vector();
	protected float w;
	protected float h;

	protected AABB aabb;

	public boolean noclip = false;

	protected List<Entity> alreadyCollisionsChecked = new ArrayList<Entity>();

	public Entity(int width, int height, int x, int y)
	{
		this.aabb = new AABB(width, height);
		this.w = width;
		this.h = height;
		this.position.x = x;
		this.position.y = y;
	}

	public abstract void init();

	public abstract void render();

	public void update(int delta)
	{
		aabb.update(position);
		if (!noclip)
		{
			// this.checkCollisions(this);
		}
	}

	public boolean checkCollisions()
	{
		for(int i = 0; i < world.entities.size(); i++)
		{
			for(Entity entity : world.entities)
			{
				for(Entity entity2 : world.entities)
				{
					if(entity != entity2)
					{
						if(CollisionLibrary.testAABBAABB(entity.aabb, entity2.aabb))
						{
							return true;
						} else
						{
							return false;
						}
					}
				}
			}
		}
		return false;

	}

	public boolean checkCollisions(Entity entity)
	{
		for(int i = 0; i < world.entities.size(); i++)
		{
			for(Entity entity2 : world.entities)
			{
				if(entity != entity2)
				{
					if(CollisionLibrary.testAABBAABB(entity.aabb, entity2.aabb))
					{
						return true;
					} else
					{
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean canGoto(int x, int y, Entity entity)
	{
		for(int i = 0; i < world.entities.size(); i++)
		{
			for(Tile tiles : world.tiles)
			{
				if (CollisionLibrary.testAABBAABB(entity.aabb, tiles.aabb)) {
					return false;
				} else
				{
					return true;
				}
			}
		}
		return false;
	}

	public abstract void onCollide(Entity entity);
}