package fr.wolf.moteur.collision.utils;

public class AABB
{
	public Vector center;
	public float r[];

	public AABB(final float width, final float height)
	{
		center = new Vector();
		r = new float[2];
		r[0] = width * 0.5f;
		r[1] = height * 0.5f;
	}
	
	public void update(final Vector position)
	{
		center.x = position.x;
		center.y = position.y;
	}
}