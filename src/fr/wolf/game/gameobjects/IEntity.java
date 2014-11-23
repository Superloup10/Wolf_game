package fr.wolf.game.gameobjects;

public interface IEntity
{
    public void update();

    public void attack();

    public void move(float magX, float magY);

    public void look();

    public void hunt();

    public void death();

    public void damage(int amount);

    public boolean canMove();
}