package fr.wolf.game;

import fr.wolf.engine.GameObject;
import fr.wolf.engine.Physics;
import fr.wolf.game.gameobjects.Player;
import fr.wolf.game.gameobjects.Vampire;
import fr.wolf.game.gameobjects.item.Cube;
import fr.wolf.game.gameobjects.item.Wall;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class Wolf
{
    public static Wolf wolf;

    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> remove;
    private Player player;

    public Wolf()
    {
        objects = new ArrayList<GameObject>();
        remove = new ArrayList<GameObject>();

        player = new Player(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2);

        objects.add(player);
        generateTestLevel();
        objects.add(new Cube(200, 400));
        objects.add(new Vampire(300, 400, 1));
    }

    public void generateTestLevel()
    {
        // Generate First Room
        objects.add(new Wall(200, 200, 10, 300));
        objects.add(new Wall(500, 200, 10, 100));
        objects.add(new Wall(500, 400, 10, 100));
        objects.add(new Wall(200, 200, 300, 10));
        objects.add(new Wall(200, 500, 100, 10));
        objects.add(new Wall(400, 500, 100, 10));

        // Generate Hallway 1
        objects.add(new Wall(300, 500, 10, 200));
        objects.add(new Wall(400, 500, 10, 200));

        // Generate Second Room
        objects.add(new Wall(400, 700, 100, 10));
        objects.add(new Wall(200, 700, 100, 10));
        objects.add(new Wall(200, 700, 10, 300));
        objects.add(new Wall(500, 700, 10, 300));
        objects.add(new Wall(200, 1000, 300, 10));

        // Generate Hallway 2
        objects.add(new Wall(500, 300, 100, 10));
        objects.add(new Wall(500, 400, 100, 10));

        // Generate Boss Room
        objects.add(new Wall(900, 200, 10, 300));
        objects.add(new Wall(600, 200, 10, 100));
        objects.add(new Wall(600, 400, 10, 100));
        objects.add(new Wall(600, 200, 300, 10));
        objects.add(new Wall(600, 500, 300, 10));

    }

    public void getInput()
    {
        player.getInput();
    }

    public void update()
    {
        for(GameObject go : objects)
        {
            if(!go.isRemove())
                go.update();
            else
                remove.add(go);
        }

        for(GameObject go : remove)
        {
            objects.remove(go);
        }
    }

    public void render()
    {
        for(GameObject go : objects)
        {
            go.render();
        }
    }

    public ArrayList<GameObject> getObjects()
    {
        return objects;
    }

    public static ArrayList<GameObject> sphereCollide(float x, float y, float radius)
    {
        ArrayList<GameObject> res = new ArrayList<GameObject>();

        for(GameObject go : wolf.getObjects())
        {
            if(Util.dist(go.getX(), go.getY(), x, y) < radius)
                res.add(go);
        }

        return res;
    }

    public static ArrayList<GameObject> rectangleCollide(float x1, float y1, float x2, float y2)
    {
        ArrayList<GameObject> res = new ArrayList<GameObject>();

        float sx = x2 - x1;
        float sy = y2 - y1;

        Rectangle collider = new Rectangle((int)x1, (int)y1, (int)sx, (int)sy);

        for(GameObject go : wolf.getObjects())
        {
            if(Physics.checkCollision(collider, go) != null)
                res.add(go);
        }
        return res;
    }
}