package fr.wolf.game.gameobjects;

import fr.wolf.engine.GameObject;
import fr.wolf.game.Wolf;
import fr.wolf.game.gameobjects.world.Air;
import fr.wolf.game.utils.Delay;
import fr.wolf.game.utils.EnumFacingDirection;
import fr.wolf.game.utils.Time;
import fr.wolf.game.utils.Util;

import java.util.ArrayList;

public class Entity extends StatObject
{
    public static final int SIZE = 8;
    protected static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;

    protected int attackRange;
    protected EnumFacingDirection facingDirection;
    protected Delay attackDelay;
    protected int attackDamage;
    protected int moveAmountX, moveAmountY;
    protected int id;
    protected float SPEED = 1.0F;

    public Entity(int id)
    {
        init(x, y, 0.01F, 1.0F, 0.25F, SIZE, SIZE, id);
        stats = new Stats(0, true);
        this.id = id;
        attackDelay = new Delay(500);
        attackRange = 49;
        attackDamage = 1;
        facingDirection = EnumFacingDirection.FORWARD;
        moveAmountX = 0;
        moveAmountY = 0;
        attackDelay.terminate();
    }

    @Override
    public void update()
    {
        // test if the entity is dead
        if((stats.getCurrentHealth() <= 0))
        {
            death();
        }
    }

    public GameObject[][] getCollideObjects()
    {
        // [x][y]

        GameObject[][] entityPosition = new GameObject[3][3];
        entityPosition[1][1] = this;
        ArrayList<GameObject> objects = Wolf.getWolf().getObjects();
        int size = objects.size();

        float entityX = this.getX();
        float entityY = this.getY();
        float entitySizeX = this.getSizeX();
        float entitySizeY = this.getSizeY();

        for(int i = 0; i < size; i++)
        {
            GameObject obj = objects.get(i);

            float objX = obj.getX();
            float objY = obj.getY();
            float objSizeX = obj.getSizeX();
            float objSizeY = obj.getSizeY();

            // AZE
            // Q*D
            // WXC
            if(objX < entityX && objX + objSizeX == entityX)
            {
                // x = 0
                if(objY < entityY && objY + objSizeY == entityY)
                    entityPosition[0][0] = obj;
                if(objY == entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[0][1] = obj;
                if(objY > entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[0][2] = obj;
            }
            if(objX == entityX && objX + objSizeX == entityX)
            {
                // x = 1
                if(objY < entityY && objY + objSizeY == entityY)
                    entityPosition[1][0] = obj;
                if(objY == entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[1][1] = this;
                if(objY > entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[1][2] = obj;
            }
            if(objX > entityX && objX - objSizeX == entityX + entitySizeX)
            {
                // x = 2
                if(objY < entityY && objY + objSizeY == entityY)
                    entityPosition[2][0] = obj;
                if(objY == entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[2][1] = obj;
                if(objY > entityY && objY - objSizeY == entityY + entitySizeY)
                    entityPosition[2][2] = obj;
            }

        }
        for(int i = 0; i <= 2; i++)
        {
            for(int j = 0; j <= 2; j++)
            {
                if(entityPosition[i][j] == null)
                    entityPosition[i][j] = new Air(entityX + ((1 + i) * 8), ((1 + j) * 8), 8, 8); // set to air
            }
        }
        return entityPosition;
    }

    public void move(float magX, float magY)
    {
        if(magX == 0 && magY == 1)
            facingDirection = EnumFacingDirection.FORWARD;
        if(magX == -1 && magY == 0)
            facingDirection = EnumFacingDirection.LEFT;
        if(magX == 0 && magY == -1)
            facingDirection = EnumFacingDirection.BACKWARD;
        if(magX == 1 && magY == 0)
            facingDirection = EnumFacingDirection.RIGHT;

        moveAmountX += SPEED * magX * Time.getDelta();
        moveAmountY += SPEED * magY * Time.getDelta();
        GameObject[][] objs = getCollideObjects();
        if(canMoveX(magX, objs))
            x += magX;
        if(canMoveY(magY, objs))
            y += magY;
    }

    private boolean canMoveX(float value, GameObject[][] objs)
    {
        GameObject obj = objs[(1 + (int)value)][1];
        if(obj.isSolid())
            return false;

        return true;
    }

    private boolean canMoveY(float value, GameObject[][] objs)
    {
        GameObject obj = objs[1][(1 + (int)value)];
        if(obj.isSolid())
            return false;

        return true;
    }

    public boolean hunt()
    {
        return true;
    }

    public boolean attack()
    {
        System.out.println("We're attacking!");

        ArrayList<GameObject> objects = new ArrayList<GameObject>();

        if(facingDirection == EnumFacingDirection.FORWARD)
            objects = Wolf.rectangleCollide(x, y, x + SIZE, y + attackRange + SIZE);
        else if(facingDirection == EnumFacingDirection.LEFT)
            objects = Wolf.rectangleCollide(x, y, x - attackRange + SIZE, y + SIZE);
        else if(facingDirection == EnumFacingDirection.BACKWARD)
            objects = Wolf.rectangleCollide(x, y - attackRange + SIZE, x + SIZE, y);
        else if(facingDirection == EnumFacingDirection.RIGHT)
            objects = Wolf.rectangleCollide(x, y, x + attackRange, y + SIZE);

        ArrayList<Entity> enemies = new ArrayList<Entity>();

        for(GameObject go : objects)
        {
            if(go instanceof Entity)
                enemies.add((Entity)go);
        }

        if(enemies.size() > 0)
        {
            Entity target = enemies.get(0);
            if(enemies.size() > 1)
            {
                for(Entity e : enemies)
                    if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
                        target = e;
            }

            target.damage(attackDamage);
            System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth());
        }
        else
            System.out.println(" : No target");

        attackDelay.restart();
        return true;
    }

    public boolean look()
    {
        return true;
    }

    public boolean death()
    {
        remove();
        return true;
    }

    /*
     * public boolean[] canMove2() { float newX = x + moveAmountX; float newY = y + moveAmountY; moveAmountX = 0; moveAmountY = 0; ArrayList<GameObject> objects = Wolf.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE); boolean move[] = new boolean[4]; for(GameObject go : objects) { float Wx = go.getX(); float Wy = go.getY(); float Ex = newX; float Ey = newY; // TODO prendre en compte
     * l'épaisseur if(Wx < Ex && Wy == Ey) { // no move in x- if(go.isSolid()) move[0] = false; else move[0] = true; } if(Wx > Ex && Wy == Ey) { // no move in x+ if(go.isSolid()) move[1] = false; else move[1] = true; } if(Wy < Ey && Wx == Ex) { // no move in y- if(go.isSolid()) move[2] = false; else move[2] = true; } if(Wy > Ey && Wx == Ex) { // no move in y+ if(go.isSolid()) move[3] = false; else
     * move[3] = true; } } x = newX; y = newY; return move; }
     */
}