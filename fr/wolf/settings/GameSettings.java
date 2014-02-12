package fr.wolf.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.wolf.Wolf;
import fr.wolf.moteur.Keybinding;

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
	private FileReader optionsFileReader = null;
	private BufferedReader br = null;
	private FileWriter optionsFileWriter = null;
	private BufferedWriter bw = null;

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
		this.wolf = wolf;
		this.optionsFile = new File(file, "options.xml");
		
		if(!optionsFile.exists())
		{
			try
			{
				this.optionsFile.createNewFile();
				this.optionsFileWriter = new FileWriter(optionsFile.getAbsoluteFile());
				this.bw = new BufferedWriter(optionsFileWriter);
				this.optionsFileReader = new FileReader(optionsFile.getAbsoluteFile());
				this.br = new BufferedReader(optionsFileReader);
				
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					this.bw.flush();
					this.bw.close();
					this.optionsFileWriter.close();
					this.br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}