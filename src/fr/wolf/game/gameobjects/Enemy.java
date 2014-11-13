package fr.wolf.game.gameobjects;

import fr.wolf.engine.GameObject;
import fr.wolf.engine.Sprite;
import fr.wolf.game.Delay;
import fr.wolf.game.Time;
import fr.wolf.game.Util;
import fr.wolf.game.Wolf;

import java.util.ArrayList;

public class Enemy extends StatObject
{
    public static final float DAMPING = 0.5F;
    private float sightRange;
    private StatObject target;
    private float attackRange;
    private int attackDamage;
    private Delay attackDelay;

    public Enemy(int level)
    {
        stats = new Stats(level, false);
        target = null;
        attackDelay = new Delay(500);
        attackRange = 48F;
        attackDamage = 1;
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
            if(Util.lineOfSight(this, target) && Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= attackRange)
            {
                if(attackDelay.isOver())
                    attack();
            }
            else
                chase();
        }

        if((stats.getCurrentHealth() <= 0))
            death();
    }

    protected void attack()
    {
        getTarget().damage(getAttackDamage());
        System.out.println("We're hit! :" + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
        attackDelay.restart();
    }

    protected void look()
    {
        ArrayList<GameObject> objects = Wolf.sphereCollide(x, y, sightRange);

        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget((StatObject)go);
    }

    protected void chase()
    {
        float speedX = (getTarget().getX() - x);
        float speedY = (getTarget().getY() - y);

        float maxSpeed = getStats().getSpeed() * DAMPING;

        if(speedX > maxSpeed)
            speedX = maxSpeed;
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;

        if(speedY > maxSpeed)
            speedY = maxSpeed;
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;

        x = x + speedX * Time.getDelta();
        y = y + speedY * Time.getDelta();
    }

    protected void death()
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
}