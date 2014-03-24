package fr.wolf.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.wolf.moteur.collision.entity.Entity;

public class Player extends Entity
{
	private int heal;
	private int mana;
	private int armor;
	private int damages;
	private int level;
	
	private String pseudo;
	private String messageStatus;
	
	private Weapons weapons;
	
	public Player(int width, int height, int x, int y)
	{
		super(width, height, x, y);
	}
	
	public Player(String pseudo, int heal, int mana, int armor, int level, int damagesWeapons, String nameWeapons)
	{
		this.pseudo = pseudo;
		this.heal = heal;
		this.mana = mana;
		this.armor = armor;
		this.level = level;
		this.weapons = new Weapons(nameWeapons, damagesWeapons * this.level);
		
		if(this.level < 1)
			this.level = 1;
	}
	
	public Player(String pseudo, int heal, int mana, int armor, int level)
	{
		this.pseudo = pseudo;
		this.heal = heal;
		this.mana = mana;
		this.armor = armor;
		this.level = level;
		this.weapons = new Weapons(1 * this.level);

	}
	
	public Player(String pseudo, int heal, int armor, int damagesWeapons, String nameWeapons)
	{
		this.pseudo = pseudo;
		this.heal = heal;
		this.mana = heal / 2;
		this.armor = armor;
		this.level = 1;
		this.weapons = new Weapons(nameWeapons, damagesWeapons * this.level);
	}
	
	public Player(String pseudo, int heal, int mana)
	{
		this.pseudo = pseudo;
		this.heal = heal;
		this.mana = mana;
		this.armor = 5;
		this.level = 1;
		this.weapons = new Weapons(1 * this.level);
	}
	
	private void receiveDamage(int damages)
	{
		this.heal -= damages * this.level;
		
		if(this.heal < 0)
		{
			this.heal = 0;
		}
	}
	
	public void attack(Player player)
	{
		player.receiveDamage(this.weapons.getDamagesWeapons());
		
		if(player.getHeal() == 0)
		{
			this.level += 1;
		}
	}
	
	public boolean isShown()
	{
		if(this.getHeal() > 0)
		{
			this.messageStatus = "Vivant";
			return true;
		}
		else
		{
			this.messageStatus = "Mort";
			return false;
		}
	}

	public int getHeal()
	{
		return this.heal;
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

	/*@Override
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
	}*/

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