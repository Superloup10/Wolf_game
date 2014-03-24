package fr.wolf.moteur.collision.entity;

import java.util.ArrayList;
import java.util.List;

import fr.wolf.Wolf;
import fr.wolf.world.World;

public class EntityManager
{
	// Entity[] entities;
	List<Entity> entities = new ArrayList<Entity>();
	public World world = Wolf.world;

	public EntityManager()
	{

	}

	public void update(int delta)
	{
		for(Entity entity : entities)
		{
			entity.update(delta);
		}
	}

	public void init()
	{
		for(Entity entity : entities)
		{
			entity.init();
		}
	}

	/*public void render()
	{
		for(Entity entity : entities)
		{
			entity.render();
		}
	}*/

	public void registerEntity(Entity entity)
	{
		entities.add(entity);
		world.addEntity(entity);
	}
}