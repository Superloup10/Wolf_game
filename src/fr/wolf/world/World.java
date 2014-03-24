package fr.wolf.world;

import java.util.ArrayList;
import java.util.List;

import fr.wolf.moteur.collision.entity.Entity;
import fr.wolf.world.enums.EnumWorld;

public class World
{
	// World contains entities, tiles etc...

	public List<Entity> entities = new ArrayList<Entity>();
	public EnumWorld world;

	public World()
	{
		init();
	}

	public void init()
	{

	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}
}