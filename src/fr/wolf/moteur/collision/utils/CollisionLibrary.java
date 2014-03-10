package fr.wolf.moteur.collision.utils;

public class CollisionLibrary
{
	public static boolean testAABBAABB(final AABB box1, final AABB box2)
	{
		if((box2.center.x >= box1.center.x + box1.w) || (box2.center.x + box2.w <= box1.center.x) || (box2.center.y >= box1.center.y + box1.h) || (box2.center.y + box2.h <= box1.center.y))
			return false;//Pas touché
		return true;//touché
	}

	public static boolean testCircleCircle(final Circle c1, final Circle c2)
	{
		final float distSQ = c1.center.distSQ(c2.center);
		final float radiusSum = c1.radius + c2.radius;

		return distSQ <= radiusSum * radiusSum;
	}

	public static boolean testCircleAABB(final Circle circle, final AABB box)
	{
		// get the squared distance between circle center and the AABB
		float sqDist = sqDistPointAABB(circle.center, box);
		float r = circle.radius;

		return sqDist <= r * r;
	}

	public static float sqDistPointAABB(final Vector p, final AABB aabb)
	{
		float sqDist = 0.0f;
		float v;
		float minX, minY, maxX, maxY;

		// get the minX, maxX, minY and maxY points of the AABB
		minX = aabb.center.x - aabb.w;
		maxX = aabb.center.x + aabb.w;

		minY = aabb.center.y - aabb.h;
		maxY = aabb.center.y + aabb.h;

		// test the bounds against the points X axis
		v = p.x;

		if (v < minX)
			sqDist += (minX - v) * (minX - v);
		if (v > maxX)
			sqDist += (v - maxX) * (v - maxX);

		// test the bounds against the points Y axis
		v = p.y;

		if (v < minY)
			sqDist += (minY - v) * (minY - v);
		if (v > maxY)
			sqDist += (v - maxY) * (v - maxY);

		return sqDist;
	}
}