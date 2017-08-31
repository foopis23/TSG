package foopis.main.enemies;

import foopis.main.TSG;

import java.util.Random;


public class Thot implements Enemy
{
    private String name;
    private int damage;
    private int health;
    private double hitChance;
    Random generator;
    int xpDropped;

    public Thot(int luck)
    {
        generator = new Random();
        hitChance = 1;
        int i = 0;

        if(i==0)
        {
            name = "Normie Thot";
            health = generator.nextInt(luck*2+20)+10;
            damage = generator.nextInt(luck/2+8)+1;
        }else if(i==1)
        {
            name = "Stronk Thot";
            health = generator.nextInt(luck*2+30)+10;
            damage = generator.nextInt(luck/2+4)+1;
        }else if(i==2)
        {
            name = "Shrewd Thot";
            health = generator.nextInt(luck*2+10)+10;
            damage = generator.nextInt(luck/2+12)+1;
        }else{
            name = "Normie Thot";
            health = generator.nextInt(luck*2+20)+10;
            damage = generator.nextInt(luck/2+8)+1;
        }

        xpDropped = health+damage/2;
    }

    @Override
    public void action(TSG tsg)
    {
        if(health>0) {
            if (tsg.inCombat) {
                if(generator.nextInt(100)<100*hitChance)
                {
                    tsg.appendMessage(name + " attacked and did " + damage + " damage");
                    tsg.attackPlayer(damage);
                }else{
                    tsg.appendMessage(name+ " attacked, but its missed");
                }

                if(hitChance<1)
                {
                    hitChance+=.2;
                }else if(hitChance>1)
                {
                    hitChance = hitChance-(hitChance-1);
                }
            }
        }else{
            tsg.thotDefeated(xpDropped);
        }
    }

    @Override
    public void takeDamage(int damage)
    {
        health-=damage;
    }

    @Override
    public void setHitChance(double i) {
        hitChance = i;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
