package fr.wolf.moteur.collision.entity;

public class DummyEntity extends Entity
{
    public DummyEntity(int width, int height, int x, int y)
    {
        super(width, height, x, y);
    }

    @Override
    public void init()
    {

    }

    /*
     * @Override public void render() { // GameClassAABB.font.drawString(10, 40, "HitBox dummy : X : " + // hitbox.center.x + " Y : " + hitbox.center.y); GL11.glPushMatrix(); GL11.glBegin(GL11.GL_QUADS); GL11.glColor3f(1, 1, 0.1f); GL11.glVertex2f(this.position.x - (w / 2), this.position.y - (h / 2)); GL11.glVertex2f(this.position.x + (w / 2), this.position.y - (h / 2));
     * GL11.glVertex2f(this.position.x + (w / 2), this.position.y + (h / 2)); GL11.glVertex2f(this.position.x - (w / 2), this.position.y + (h / 2)); GL11.glEnd(); GL11.glPopMatrix(); }
     */

    @Override
    public void update(int delta)
    {
        super.update(delta);

    }

    @Override
    public void onCollide(Entity entity)
    {

    }
}