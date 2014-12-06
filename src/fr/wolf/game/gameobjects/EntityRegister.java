package fr.wolf.game.gameobjects;

import fr.wolf.engine.GameObject;
import fr.wolf.game.Wolf;
import fr.wolf.game.gameobjects.world.Wall;

import java.util.ArrayList;

public class EntityRegister
{
    public static void generateTestLevel()
    {
        // Generate First Room
        for(int i = 0; i <= 10; i++)
        {
            addWall(10 + i, 10);
        }
        // objects.add(new Wall(200, 200, 10, 300));
        // objects.add(new Wall(500, 200, 10, 100));
        // objects.add(new Wall(500, 400, 10, 100));
        // objects.add(new Wall(200, 200, 300, 10));
        // objects.add(new Wall(200, 500, 100, 10));
        // objects.add(new Wall(400, 500, 100, 10));
        //
        // // Generate Hallway 1
        // objects.add(new Wall(300, 500, 10, 200));
        // objects.add(new Wall(400, 500, 10, 200));
        //
        // // Generate Second Room
        // objects.add(new Wall(400, 700, 100, 10));
        // objects.add(new Wall(200, 700, 100, 10));
        // objects.add(new Wall(200, 700, 10, 300));
        // objects.add(new Wall(500, 700, 10, 300));
        // objects.add(new Wall(200, 1000, 300, 10));
        //
        // // Generate Hallway 2
        // objects.add(new Wall(500, 300, 100, 10));
        // objects.add(new Wall(500, 400, 100, 10));
        //
        // // Generate Boss Room
        // objects.add(new Wall(900, 200, 10, 300));
        // objects.add(new Wall(600, 200, 10, 100));
        // objects.add(new Wall(600, 400, 10, 100));
        // objects.add(new Wall(600, 200, 300, 10));
        // objects.add(new Wall(600, 500, 300, 10));
    }

    public static boolean addWall(int x, int y)
    {
        ArrayList<GameObject> objects = Wolf.getWolf().getObjects();
        objects.add(new Wall(x * 8, y * 8, 8, 8));
        return false;
    }
}