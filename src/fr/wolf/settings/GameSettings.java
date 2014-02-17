package fr.wolf.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import fr.wolf.Wolf;
import fr.wolf.moteur.Keybinding;
import fr.wolf.settings.Enums.EnumDifficulty;

public class GameSettings
{
	public Keybinding keybindForward;// Avancer
	public Keybinding keybindBack;// Reculer
	public Keybinding keybindJump;// Sauter
	public Keybinding keybindDescend;// Descendre
	public Keybinding keybindAttack;// Attaquer
	public Keybinding keybindMorph;// Transformation
	public Keybinding keybindWard;// Parer

	protected Wolf wolf;
	private File optionsFile;
	public EnumDifficulty difficulty;
	public String language;

	public GameSettings(Wolf wolf, File file)
	{
		/*
		 * this.keybindForward = new Keybinding("key.forward", 0,
		 * "key.category.movement"); this.keybindBack = new
		 * Keybinding("key.back", 0, "key.category.movement"); this.keybindJump
		 * = new Keybinding("key.jump", 0, "key.category.movement");
		 * this.keybindDescend = new Keybinding("key.descend", 0,
		 * "key.category.movement"); this.keybindAttack = new
		 * Keybinding("key.attack", 0, "key.category.gameplay");
		 * this.keybindMorph = new Keybinding("key.morph", 0,
		 * "key.category.gameplay"); this.keybindWard = new
		 * Keybinding("key.Ward", 0, "key.category.gameplay");
		 */
		this.difficulty = EnumDifficulty.NORMAL;
		this.language = "fr_FR";
		this.wolf = wolf;
		this.optionsFile = new File(file, "options.txt");
		this.loadOptions();
	}

	public GameSettings()
	{
		this.difficulty = EnumDifficulty.NORMAL;
		this.language = "fr_FR";
	}

	public void loadOptions()
	{
		try
		{
			if (!this.optionsFile.exists())
			{
				return;
			}

			BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
			String s = "";

			while ((s = bufferedreader.readLine()) != null)
			{
				try
				{
					String[] astring = s.split(":");

					if(astring[0].equals("difficulty"))
					{
						this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(astring[1]));
					}

					if(astring[0].equals("lang") && astring.length >= 2)
					{
						this.language = astring[1];
					}
				} catch(Exception exception)
				{
					System.out.println(exception);
				}
			}
			bufferedreader.close();
		} catch(Exception exception1)
		{
			System.out.println(exception1);
		}
	}

	public void saveOptions()
	{
		try
		{
			PrintWriter printwriter = new PrintWriter(new FileWriter(this.optionsFile));
			printwriter.println("difficulty:" + this.difficulty.getDifficultyId());
			printwriter.println("lang:" + this.language);

			printwriter.close();
		} catch(Exception exception)
		{
			System.out.println(exception);
		}
	}
}