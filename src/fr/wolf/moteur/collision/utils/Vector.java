package fr.wolf.moteur.collision.utils;

public class Vector
{
	public float x;
	public float y;

	public Vector()
	{
		x = 0.0f;
		y = 0.0f;
	}

	public float distSQ(final Vector vec)
	{
		float distX = x - vec.x;
		float distY = y - vec.y;

		return distX * distX + distY * distY;
	}
}