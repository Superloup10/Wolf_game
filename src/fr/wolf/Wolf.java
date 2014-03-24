package fr.wolf;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import fr.wolf.entity.Player;
import fr.wolf.moteur.Keybinding;
import fr.wolf.moteur.collision.entity.DummyEntity;
import fr.wolf.moteur.collision.entity.EntityManager;
import fr.wolf.moteur.graphic.Sprite;
import fr.wolf.moteur.graphic.SpriteSheet;
import fr.wolf.world.World;

public class Wolf
{
	int fps;
	long lastFps;	
	private long lastFrame;
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static World world = new World();

	public Player player = new Player(10, 10, 150, 150);
	public DummyEntity dummy = new DummyEntity(10, 10, 280, 280);
	public EntityManager entityManager = new EntityManager();
	
	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Display.setTitle("Wolf");
			Display.setResizable(true);
			SpriteSheet.init();
			Sprite.init();
			
			//Init Transparence
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			//Init Texture
			glEnable(GL_TEXTURE_2D);
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		// init OpenGL here
		//initGL();

		entityManager.registerEntity(player);
		entityManager.registerEntity(dummy);
		entityManager.init();
		lastFps = getTime();
		while (!Display.isCloseRequested() && !(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)))
		{
			// render OpenGL here
			Keybinding.input();
			render();
			update();
			WIDTH = Display.getWidth();
			HEIGHT = Display.getHeight();
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
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, WIDTH, HEIGHT, 0);
		glMatrixMode(GL_MODELVIEW);
		/*GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);*/
	}

	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		initGL();
		//entityManager.render();
		
		GL11.glBindTexture(GL_TEXTURE_2D, SpriteSheet.tiles.texture.id);
		for(int x = 0; x < WIDTH; x += 32)
		{
			for(int y = 0; y < HEIGHT; y += 32)
			{
				if( y % 64 == 0)
				{
					//Renderer.renderTexturedSquare(Sprite.dirt, x, y);
				} else {
					//Renderer.renderTexturedSquare(Sprite.cobblestone, x, y);
				}
			}
		}
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