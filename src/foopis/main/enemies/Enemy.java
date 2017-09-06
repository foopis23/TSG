package foopis.main.enemies;

import foopis.main.TSG;

public class Enemy
{
    protected int health;
    protected int damage;
    protected String name;
    protected double hitChance;
    protected int xpDropped;

    public void action(TSG tsg)
    {
        tsg.appendMessage("This is a default Action for thots");
    }

    public void takeDamage(int damage)
    {
        health -= damage;
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
