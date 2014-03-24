package fr.wolf.entity;

public class Weapons
{
	private int damagesWeapons;
	private String nameWeapons;

	public Weapons(String nameWeapons, int damagesWeapons)
	{
		this.nameWeapons = nameWeapons;
		this.damagesWeapons = damagesWeapons;
	}

	public Weapons(String nameWeapons)
	{
		this.nameWeapons = nameWeapons;
		this.damagesWeapons = 1;
	}

	public Weapons(int damagesWeapons)
	{
		this.nameWeapons = "Epée cristant";
		this.damagesWeapons = damagesWeapons;
	}

	public final int getDamagesWeapons()
	{
		return damagesWeapons;
	}

	public void setDamagesWeapons(int damagesWeapons)
	{
		this.damagesWeapons = damagesWeapons;
	}

	public final String getNameWeapons()
	{
		return nameWeapons;
	}

	public void setNameWeapons(String nameWeapons)
	{
		this.nameWeapons = nameWeapons;
	}
}