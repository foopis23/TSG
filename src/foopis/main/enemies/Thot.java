package foopis.main.enemies;

import foopis.main.TSG;

import java.util.Random;


public class Thot extends Enemy
{
    private Random generator;

    public Thot(int level, TSG tsg)
    {
        generator = tsg.random;
        hitChance = 1;
        int i = generator.nextInt(5);

        if(i==0)
        {
            name = "Normie Thot";
            health = generator.nextInt(level*2+20)+10;
            damage = generator.nextInt(level/2+8)+1;
        }else if(i==1)
        {
            name = "Stronk Thot";
            health = generator.nextInt(level*2+30)+10;
            damage = generator.nextInt(level/2+4)+1;
        }else if(i==2)
        {
            name = "Shrewd Thot";
            health = generator.nextInt(level*2+10)+10;
            damage = generator.nextInt(level/2+12)+1;
        }else{
            name = "Normie Thot";
            health = generator.nextInt(level*2+20)+10;
            damage = generator.nextInt(level/2+8)+1;
        }

        xpDropped = health+damage/2;
    }

    public void action(TSG tsg)
    {
        if(health>0) {
            if (tsg.inCombat) {
                if(generator.nextInt(100)<100*hitChance)
                {
                    tsg.appendMessage(name + " attacked and did " + damage + " damage");
                    tsg.player.takeDamage(damage);
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
}
