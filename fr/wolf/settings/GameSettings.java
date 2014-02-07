package fr.wolf.settings;

import java.io.File;

import fr.wolf.Wolf;
import fr.wolf.moteur.Keybinding;

public class GameSettings
{
	public Keybinding keybindForward;// Avancer
	public Keybinding keybindBack;// Reculer
	public Keybinding keybindJump;// Sauter
	public Keybinding keybindSneak;// Descendre
	public Keybinding keybindAttack;// Attaquer
	public Keybinding keybindMorph;// Transformation
	public Keybinding keybindBlock;// Parer

	protected Wolf wolf;
	private File optionsFile;

	public GameSettings(Wolf wolf, File file)
	{
		/*
		 * this.keybindForward = new Keybinding("key.forward", 0,
		 * "key.category.movement"); this.keybindBack = new
		 * Keybinding("key.back", 0, "key.category.movement"); this.keybindJump
		 * = new Keybinding("key.jump", 0, "key.category.movement");
		 * this.keybindSneak = new Keybinding("key.sneak", 0,
		 * "key.category.movement"); this.keybindAttack = new
		 * Keybinding("key.attack", 0, "key.category.gameplay");
		 * this.keybindMorph = new Keybinding("key.morph", 0,
		 * "key.category.gameplay"); this.keybindBlock = new
		 * Keybinding("key.block", 0, "key.category.gameplay");
		 */
		this.wolf = wolf;
		this.optionsFile = new File(file, "options.xml");
	}
}