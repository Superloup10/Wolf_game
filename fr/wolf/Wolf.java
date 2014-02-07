package fr.wolf;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import fr.wolf.moteur.Keybinding;

public class Wolf
{
	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Display.setTitle("Wolf");
			Display.setResizable(true);
		} catch(LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		// init OpenGL here

		while(!Display.isCloseRequested())
		{
			// render OpenGL here
			Keybinding.input();
			Display.update();
		}
		Display.destroy();
	}
}