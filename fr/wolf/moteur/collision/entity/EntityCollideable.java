package fr.wolf.moteur.collision.entity;

import fr.wolf.moteur.collision.utils.AABB;
import fr.wolf.moteur.collision.utils.Vector;

public abstract class EntityCollideable extends Entity
{
	protected AABB hitbox;
	protected Vector position = new Vector();

	/**
	 * Entity that has a hitbox
	 * 
	 * @param width Size ( X )
	 * @param height Size ( Y )
	 * @param x Spawning location X
	 * @param y Spawning location Y
	 */
	public EntityCollideable(int width, int height, int x, int y)
	{
		this.hitbox = new AABB(width, height);
		this.position.x = x;
		this.position.y = y;
	}

	@Override
	public void update(int delta)
	{
		hitbox.update(position);
	}
}