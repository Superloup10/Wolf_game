package fr.wolf.moteur.collision.utils;

public class AABB
{
    public Vector center;
    public float w;
    public float h;

    public AABB(final float width, final float height)
    {
        center = new Vector();
        w = width * 0.5f;
        h = height * 0.5f;
    }

    public void update(final Vector position)
    {
        center.x = position.x;
        center.y = position.y;
    }
}