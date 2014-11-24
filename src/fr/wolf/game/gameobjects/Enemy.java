package fr.wolf.game.gameobjects;

import fr.wolf.engine.GameObject;
import fr.wolf.engine.Sprite;
import fr.wolf.game.Delay;
import fr.wolf.game.Time;
import fr.wolf.game.Util;
import fr.wolf.game.Wolf;

import java.util.ArrayList;

public class Enemy extends StatObject implements IEntity
{
    public static final int SIZE = 32;
    public static final int FORWARD = 0;
    public static final int LEFT = 1;
    public static final int BACKWARD = 2;
    public static final int RIGHT = 3;
    public static final float DAMPING = 0.5F;

    private float sightRange;
    private StatObject target;
    private float attackRange;
    private int attackDamage;
    private int facingDirection;
    private Delay attackDelay;
    private float moveAmountX;
    private float moveAmountY;

    public Enemy(int level)
    {
        stats = new Stats(level, false);
        target = null;
        attackDelay = new Delay(500);
        attackRange = 48F;
        attackDamage = 1;
        facingDirection = 0;
        moveAmountX = 0;
        moveAmountY = 0;
        attackDelay.terminate();
        sightRange = 128F;
    }

    @Override
    public void update()
    {
        if(target == null)
            look();
        else
        {
            if(Util.lineOfSight(this, target) && Util.dist(moveAmountX, moveAmountY, getTarget().getX(), getTarget().getY()) <= attackRange)
            {
                if(attackDelay.isOver())
                    attack();
            }
            else
                hunt();
        }

        if((stats.getCurrentHealth() <= 0))
            death();
    }

    @Override
    public void attack()
    {
        getTarget().damage(getAttackDamage());
        System.out.println("We're hit! :" + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
        attackDelay.restart();
    }

    @Override
    public void look()
    {
        ArrayList<GameObject> objects = Wolf.sphereCollide(x, y, sightRange);

        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget((StatObject)go);
    }

    @Override
    public void move(float magX, float magY)
    {
        float distanceX = (getTarget().getX() - x);
        float distanceY = (getTarget().getY() - y);

        if(magX == 0 && magY == 1)
            facingDirection = FORWARD;
        if(magX == -1 && magY == 0)
            facingDirection = LEFT;
        if(magX == 0 && magY == -1)
            facingDirection = BACKWARD;
        if(magX == 1 && magY == 0)
            facingDirection = RIGHT;

        float maxDistance = 4.0F * DAMPING;

        if(distanceX > maxDistance)
            distanceX = maxDistance;
        if(distanceX < -maxDistance)
            distanceX = -maxDistance;

        if(distanceY > maxDistance)
            distanceY = maxDistance;
        if(distanceY < -maxDistance)
            distanceY = -maxDistance;

        moveAmountX = x + distanceX * Time.getDelta();
        moveAmountY = y + distanceY * Time.getDelta();
    }

    public boolean[] canMove2()
    {
        float newX = x + moveAmountX;
        float newY = y + moveAmountY;

        moveAmountX = 0;
        moveAmountY = 0;

        ArrayList<GameObject> objects = Wolf.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE);

        boolean move[] = new boolean[4];

        for(GameObject go : objects)
        {
            float Wx = go.getX();
            float Wy = go.getY();

            float Ex = newX;
            float Ey = newY;

            // TODO prendre en compte l'épaisseur

            if(Wx < Ex && Wy == Ey)
            {
                // no move in x-
                if(go.isSolid())
                    move[0] = false;
                else
                    move[0] = true;
            }
            if(Wx > Ex && Wy == Ey)
            {
                // no move in x+
                if(go.isSolid())
                    move[1] = false;
                else
                    move[1] = true;
            }
            if(Wy < Ey && Wx == Ex)
            {
                // no move in y-
                if(go.isSolid())
                    move[2] = false;
                else
                    move[2] = true;
            }
            if(Wy > Ey && Wx == Ex)
            {
                // no move in y+
                if(go.isSolid())
                    move[3] = false;
                else
                    move[3] = true;
            }
            // ##T
            // JTET
            // # T
        }

        x = newX;
        y = newY;

        return move;
    }

    @Override
    public void hunt()
    {
        float targetX = getTarget().getX();
        float targetY = getTarget().getY();

        if(Util.dist(x, y, getTarget().getX(), getTarget().getY()) > sightRange * 1.5F)
            target = null;

        if(getTarget() != null)
        {
            if(canMove2()[0] && targetX < x)
                move(-1, 0);// gauche
            if(canMove2()[1] && targetX > x)
                move(1, 0);// droite
            if(canMove2()[2] && targetY < y)
                move(0, -1);// bas
            if(canMove2()[3] && targetY > y)
                move(0, 1);// haut
        }
        // if(canMove() && getTarget() != null)
        // move(targetX, targetY);
    }

    @Override
    public void death()
    {
        remove();
    }

    public void setTarget(StatObject go)
    {
        target = go;
    }

    public StatObject getTarget()
    {
        return target;
    }

    public Stats getStats()
    {
        return stats;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackRange(float attackRange)
    {
        this.attackRange = attackRange;
    }

    public void setAttackDelay(int time)
    {
        this.attackDelay = new Delay(time);
        attackDelay.terminate();
    }

    public void setAttackDamage(int amt)
    {
        this.attackDamage = amt;
    }

    public void setSightRange(float dist)
    {
        this.sightRange = dist;
    }

    @Override
    protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
    {
        this.x = x;
        this.y = y;
        this.type = ENEMY_ID;
        this.spr = new Sprite(r, g, b, sx, sy);
    }

    @Override
    public boolean canMove()
    {
        return false;
    }
}