package fr.wolf.game.gameobjects;

import fr.wolf.engine.Equipment;
import fr.wolf.engine.GameObject;
import fr.wolf.engine.Inventory;
import fr.wolf.game.Delay;
import fr.wolf.game.Time;
import fr.wolf.game.Util;
import fr.wolf.game.Wolf;
import fr.wolf.game.gameobjects.item.Item;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class Player extends StatObject
{
    public static final int SIZE = 32;
    public static final int FORWARD = 0;
    public static final int LEFT = 1;
    public static final int BACKWARD = 2;
    public static final int RIGHT = 3;

    private Inventory inventory;
    private Equipment equipment;
    private int attackRange;
    private int facingDirection;
    private Delay attackDelay;
    private int attackDamage;

    public Player(float x, float y)
    {
        init(x, y, 0.01F, 1.0F, 0.25F, SIZE, SIZE, PLAYER_ID);
        stats = new Stats(0, true);
        inventory = new Inventory(20);
        equipment = new Equipment(inventory);
        attackDelay = new Delay(500);
        attackRange = 49;
        attackDamage = 1;
        facingDirection = 0;
        attackDelay.terminate();
    }

    @Override
    public void update()
    {
        // System.out.println("Stats: \n-Speed: " + getSpeed() + "\n-Level: " + getLevel() + "\n-MaxHP: " + getMaxHealth() + "\n-HP: " + getCurrentHealth() + "\n-Strength: " + getStrength() + "\n-Magic: " + getMagic());
        ArrayList<GameObject> objects = Wolf.rectangleCollide(x, y, x + SIZE, y + SIZE);

        for(GameObject go : objects)
        {
            if(go.getType() == ITEM_ID)
            {
                System.out.println("You just picked up " + ((Item)go).getName() + "!");
                addItem((Item)go);
                go.remove();
            }
        }
    }

    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_Z))
        {
            move(0, 1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q))
        {
            move(-1, 0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            move(0, -1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            move(1, 0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.isOver())
            attack();
    }

    public void attack()
    {
        System.out.println("We're attacking!");

        ArrayList<GameObject> objects = new ArrayList<GameObject>();

        if(facingDirection == FORWARD)
            objects = Wolf.rectangleCollide(x, y, x + SIZE, y + attackRange);
        else if(facingDirection == LEFT)
            objects = Wolf.rectangleCollide(x, y, x - attackRange + SIZE, y + SIZE);
        else if(facingDirection == BACKWARD)
            objects = Wolf.rectangleCollide(x, y - attackRange + SIZE, x + SIZE, y);
        else if(facingDirection == RIGHT)
            objects = Wolf.rectangleCollide(x, y, x + attackRange, y + SIZE);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        for(GameObject go : objects)
        {
            if(go.getType() == ENEMY_ID)
                enemies.add((Enemy)go);
        }

        if(enemies.size() > 0)
        {
            Enemy target = enemies.get(0);
            if(enemies.size() > 1)
            {
                for(Enemy e : enemies)
                    if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
                        target = e;
            }

            target.damage(attackDamage);
            System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth());
        }
        else
            System.out.println(" : No target");

        attackDelay.restart();
    }

    private void move(float magX, float magY)
    {
        if(magX == 0 && magY == 1)
            facingDirection = FORWARD;
        if(magX == -1 && magY == 0)
            facingDirection = LEFT;
        if(magX == 0 && magY == -1)
            facingDirection = BACKWARD;
        if(magX == 1 && magY == 0)
            facingDirection = RIGHT;
        x += getSpeed() * magX * Time.getDelta();
        y += getSpeed() * magY * Time.getDelta();
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void addXP(float amount)
    {
        stats.addXP(amount);
    }
}