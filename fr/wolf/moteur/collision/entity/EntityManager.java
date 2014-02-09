package fr.wolf.moteur.collision.entity;

import java.util.ArrayList;
import java.util.List;

import fr.wolf.moteur.collision.utils.CollisionLibrary;

public class EntityManager
{
	// Entity[] entities;
	List<Entity> entities = new ArrayList<Entity>();
	static List<EntityCollideable> collEntity = new ArrayList<EntityCollideable>();

	public EntityManager()
	{

	}

	public void update(int delta)
	{
		for (Entity entity : entities)
		{
			entity.update(delta);
		}
	}

	public void init()
	{
		for (Entity entity : entities)
		{
			entity.init();
		}
	}

	public static boolean collisionCheck()
	{
		for (EntityCollideable entity1 : collEntity)
		{
			for (EntityCollideable entity2 : collEntity)
			{
				if (entity1 != entity2)
				{
					EntityCollideable ent1 = (EntityCollideable) entity1;
					EntityCollideable ent2 = (EntityCollideable) entity2;

					if (CollisionLibrary.testAABBAABB(ent1.hitbox, ent2.hitbox))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean collisionCheck(EntityCollideable entity1)
	{
		for (EntityCollideable entity2 : collEntity)
		{
			if (entity1 != entity2)
			{
				if (CollisionLibrary.testAABBAABB(entity1.hitbox, entity2.hitbox))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}

	public void render()
	{
		for (Entity entity : entities)
		{
			entity.render();
		}
	}

	public void registerEntity(Entity entity)
	{
		entities.add(entity);
		if (entity instanceof EntityCollideable)
		{
			collEntity.add((EntityCollideable) entity);
		}
	}
}