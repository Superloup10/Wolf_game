package fr.wolf.game.gameobjects;

import fr.wolf.engine.GameObject;
import fr.wolf.engine.Inventory;
import fr.wolf.game.Wolf;
import fr.wolf.game.gameobjects.item.Item;
import fr.wolf.game.utils.Delay;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Player extends Entity
{

    private Inventory inventory;
    private Equipment equipment;

    public Player(float x, float y)
    {
        super(PLAYER_ID);
        init(x, y, 0.01F, 1.0F, 0.25F, SIZE, SIZE, PLAYER_ID);
        inventory = new Inventory(20);
        equipment = new Equipment(inventory);
        attackDelay = new Delay(500);
        attackRange = 49;
        attackDamage = 1;
    }

    @Override
    public void update()
    {
        // System.out.println("Stats: \n-Speed: " + getSpeed() + "\n-Level: " + getLevel() + "\n-MaxHP: " + getMaxHealth() + "\n-HP: " + getCurrentHealth() + "\n-Strength: " + getStrength() + "\n-Magic: " + getMagic());
        look();

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

    // @Override
    // public boolean canMove()
    // {
    // float newX = x + moveAmountX;
    // float newY = y + moveAmountY;
    //
    // moveAmountX = 0;
    // moveAmountY = 0;
    //
    // ArrayList<GameObject> objects = Wolf.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE);
    //
    // boolean move = true;
    //
    // for(GameObject go : objects)
    // {
    // if(go.isSolid())
    // move = false;
    // }
    //
    // if(!move)
    // return false;
    //
    // x = newX;
    // y = newY;
    //
    // return true;
    // }

    // @Override
    // public void move(float magX, float magY)
    // {
    // if(magX == 0 && magY == 1)
    // facingDirection = FORWARD;
    // if(magX == -1 && magY == 0)
    // facingDirection = LEFT;
    // if(magX == 0 && magY == -1)
    // facingDirection = BACKWARD;
    // if(magX == 1 && magY == 0)
    // facingDirection = RIGHT;
    // moveAmountX += 4.0F * magX * Time.getDelta();
    // moveAmountY += 4.0F * magY * Time.getDelta();
    // }

    // @Override
    // public void attack()
    // {
    // System.out.println("We're attacking!");
    //
    // ArrayList<GameObject> objects = new ArrayList<GameObject>();
    //
    // if(facingDirection == FORWARD)
    // objects = Wolf.rectangleCollide(x, y, x + SIZE, y + attackRange + SIZE);
    // else if(facingDirection == LEFT)
    // objects = Wolf.rectangleCollide(x, y, x - attackRange + SIZE, y + SIZE);
    // else if(facingDirection == BACKWARD)
    // objects = Wolf.rectangleCollide(x, y - attackRange + SIZE, x + SIZE, y);
    // else if(facingDirection == RIGHT)
    // objects = Wolf.rectangleCollide(x, y, x + attackRange, y + SIZE);
    //
    // ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    //
    // for(GameObject go : objects)
    // {
    // if(go.getType() == ENEMY_ID)
    // enemies.add((Enemy)go);
    // }
    //
    // if(enemies.size() > 0)
    // {
    // Enemy target = enemies.get(0);
    // if(enemies.size() > 1)
    // {
    // for(Enemy e : enemies)
    // if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
    // target = e;
    // }
    //
    // target.damage(attackDamage);
    // System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth());
    // }
    // else
    // System.out.println(" : No target");
    //
    // attackDelay.restart();
    // }

    @Override
    public void render()
    {
        GL11.glTranslatef(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2, 0);
        spr.render();
        GL11.glTranslatef(-x, -y, 0);
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void addXP(float amount)
    {
        stats.addXP(amount);
    }

    @Override
    public boolean look()
    {
        float newX = x + moveAmountX;
        float newY = y + moveAmountY;

        ArrayList<GameObject> objects = Wolf.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE);
        ArrayList<GameObject> items = new ArrayList<GameObject>();

        for(GameObject go : objects)
        {
            if(go.getType() == ITEM_ID)
                items.add(go);
        }

        for(GameObject go : items)
        {
            System.out.println("You just picked up " + ((Item)go).getName() + "!");
            addItem((Item)go);
            go.remove();
        }
        return true;
    }

}