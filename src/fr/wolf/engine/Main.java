package fr.wolf.engine;

import fr.wolf.game.Time;
import fr.wolf.game.Wolf;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main
{
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public static void main(String[] args)
    {
        initDisplay();
        initGL();
        initGame();

        gameLoop();

        cleanUp();
    }

    private static void initGame()
    {
        Wolf.wolf = new Wolf();
    }

    private static void cleanUp()
    {
        Display.destroy();
        Keyboard.destroy();
    }

    private static void gameLoop()
    {
        Time.init();

        int frames = 0;
        long lastTime = System.nanoTime();
        long totalTime = 0;

        while(!Display.isCloseRequested())
        {
            long now = System.nanoTime();
            long passed = now - lastTime;
            lastTime = now;
            totalTime += passed;

            if(totalTime >= 1000000000)
            {
                // System.out.println(frames);
                totalTime = 0;
                frames = 0;
            }

            Time.update();
            getInput();
            update();
            render();
            frames++;
        }
    }

    private static void render()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glLoadIdentity();

        Wolf.wolf.render();

        Display.update();
        Display.sync(60);
    }

    private static void update()
    {
        Wolf.wolf.update();
    }

    private static void getInput()
    {
        Wolf.wolf.getInput();
    }

    private static void initDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
            Keyboard.create();
            Display.setVSyncEnabled(true);
            Display.setTitle("Wolf");
        }
        catch(LWJGLException e)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void initGL()
    {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glClearColor(0, 0, 0, 0);
    }
}