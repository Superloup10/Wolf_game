package fr.wolf.moteur.collision.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Player extends EntityCollideable
{
	public Player(int width, int height, int x, int y)
	{
		super(width, height, x, y);
	}
	
	public void input(int delta)
	{
		if(Keyboard.getEventKey() == Keyboard.KEY_SPACE)
		{
			float dy = this.position.y;
			dy -= 0.1 * delta;
			float oldY = this.position.y;
			hitbox.center.y = dy;
			if(!EntityManager.collisionCheck(this))
			{
				this.position.y = dy;
				hitbox.center.y = position.y;
			}
			else
			{
				this.position.y = oldY;
			}
		}
		if(Keyboard.getEventKey() == Keyboard.KEY_S)
		{
			float dy = this.position.y;
			dy += 0.1 * delta;
			float oldY = this.position.y;
			hitbox.center.y = dy;
			if(!EntityManager.collisionCheck(this))
			{
				this.position.y = dy;
				hitbox.center.y = position.y;
			}
			else
			{
				this.position.y = oldY;
			}
		}
		if(Keyboard.getEventKey() == Keyboard.KEY_D)
		{
			float dx = this.position.x;
			dx += 0.1 * delta;
			float oldX = this.position.x;
			hitbox.center.x = dx;
			if(!EntityManager.collisionCheck(this))
			{
				this.position.x = dx;
				hitbox.center.x = position.x;
			}
			else
			{
				this.position.x = oldX;
			}
		}
		if(Keyboard.getEventKey() == Keyboard.KEY_Q)
		{
			float dx = this.position.x;
			dx -= 0.1 * delta;
			float oldX = this.position.x;
			hitbox.center.x = dx;
			if(!EntityManager.collisionCheck(this))
			{
				this.position.x = dx;
				hitbox.center.x = position.x;
			}
			else
			{
				this.position.x = oldX;
			}
		}
	}
	
	@Override
	public void init()
	{
		System.out.println("[PLAYER]I was initialized. And it felt good.");
	}

	@Override
	public void render()
	{
		GL11.glPushMatrix();//Sauvegarde Matrice
		GL11.glBegin(GL11.GL_QUADS);//Quadrilatere
		GL11.glColor3f(1, 1, 1f);//Couleur en RGB
		GL11.glVertex2f(this.position.x - 50, this.position.y - 50);//Sommet C
		GL11.glVertex2f(this.position.x + 50, this.position.y - 50);//Sommet D
		GL11.glVertex2f(this.position.x + 50, this.position.y + 100);//Sommet B
		GL11.glVertex2f(this.position.x - 50, this.position.y + 100);//Sommet A
		GL11.glEnd();
		GL11.glPopMatrix();//Restitue Matrice
	}
	
	@Override
	public void update(int delta)
	{
		super.update(delta);
		input(delta);
	}
}