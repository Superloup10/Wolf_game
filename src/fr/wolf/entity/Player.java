package fr.wolf.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.wolf.moteur.collision.entity.Entity;

public class Player extends Entity
{
	public Player(int width, int height, int x, int y)
	{
		super(width, height, x, y);
	}

	public void input(int delta)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			int dy = (int) this.position.y;
			float oldY = this.position.y;
			dy -= 0.1f * delta;
			this.aabb.center.y = dy;
			if(!checkCollisions(this))
			{
				this.position.y -= 0.1f * delta;
				this.aabb.center.y = this.position.y;
			} else
			{
				this.position.y = oldY;
				this.aabb.center.y = this.position.y;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			int dy = (int) this.position.y;
			float oldY = this.position.y;
			dy += 0.1f * delta;
			this.aabb.center.y = dy;

			if(!checkCollisions(this))
			{
				this.position.y += 0.1f * delta;
				this.aabb.center.y = this.position.y;
			} else
			{
				this.position.y = oldY;
				this.aabb.center.y = this.position.y;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			int dx = (int) this.position.x;
			float oldX = this.position.x;
			dx -= 0.1f * delta;
			this.aabb.center.x = dx;
			if(!checkCollisions(this))
			{
				this.position.x -= 0.1f * delta;
				this.aabb.center.x = this.position.x;
			} else
			{
				this.position.x = oldX;
				this.aabb.center.x = this.position.x;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			int dx = (int) this.position.x;
			float oldX = this.position.x;
			dx += 0.1f * delta;
			this.aabb.center.x = dx;
			if(!checkCollisions(this))
			{
				this.position.x += 0.1f * delta;
				this.aabb.center.x = this.position.x;
			} else
			{
				this.position.x = oldX;
				this.aabb.center.x = this.position.x;
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
		GL11.glPushMatrix();// Sauvegarde Matrice
		GL11.glBegin(GL11.GL_QUADS);// Quadrilatere
		GL11.glColor3f(1, 1, 1f);// Couleur en RGB
		GL11.glVertex2f(this.position.x - (w / 2), this.position.y - (h / 2));// Sommet C
		GL11.glVertex2f(this.position.x + (w / 2), this.position.y - (h / 2));// Sommet D
		GL11.glVertex2f(this.position.x + (w / 2), this.position.y + (h / 2));// Sommet B
		GL11.glVertex2f(this.position.x - (w / 2), this.position.y + (h / 2));// Sommet A
		GL11.glEnd();
		GL11.glPopMatrix();// Restitue Matrice
	}

	@Override
	public void update(int delta)
	{
		super.update(delta);
		input(delta);
	}

	@Override
	public void onCollide(Entity entity)
	{

	}
}