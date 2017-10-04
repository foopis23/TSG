package foopis.main.enemies;

import foopis.main.TSG;

public class Enemy
{
    protected int health;
    protected int damage;
    protected String name;
    protected double hitChance;
    protected int xpDropped;
    protected int level;

    public void action(TSG tsg)
    {
        tsg.appendMessage("This is a default Action for thots");
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public void dialogue(TSG tsg, int level)
    {
        tsg.appendMessage("This Enemy does not want to talk!");
    }

    public void setHitChance(double i)
    {
        hitChance = i;
    }

    public int getHealth()
    {
        return health;
    }

    public int getDamage()
    {
        return damage;
    }

    public String getName()
    {
        return name;
    }
}
