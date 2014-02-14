package fr.wolf;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import fr.wolf.moteur.Keybinding;
import fr.wolf.moteur.collision.entity.DummyEntity;
import fr.wolf.moteur.collision.entity.EntityManager;
import fr.wolf.moteur.collision.entity.Player;

public class Wolf
{
	int fps;
	long lastFps;	
	private long lastFrame;

	public Player player = new Player(10, 10, 150, 150);
	public DummyEntity dummy = new DummyEntity(10, 10, 280, 280);
	public EntityManager entityManager = new EntityManager();

	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(1280, 720));
			Display.create();
			Display.setTitle("Wolf");
			Display.setResizable(true);
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		// init OpenGL here
		initGL();

		entityManager.registerEntity(player);
		entityManager.registerEntity(dummy);
		entityManager.init();
		lastFps = getTime();
		while (!Display.isCloseRequested())
		{
			// render OpenGL here
			Keybinding.input();
			render();
			update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getDelta()
	{
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public void initGL()
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1);
		GL11.glViewport(0, 0, 1280, 720);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1280, 720, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		entityManager.render();
	}

	public void update()
	{
		int delta = getDelta();
		entityManager.update(delta);
		updateFps();
	}
	
	public void updateFps()
	{
		if (getTime() - lastFps > 1000)
		{
			fps = 0;
			lastFps += 1000;
		}
		fps++;
	}
}