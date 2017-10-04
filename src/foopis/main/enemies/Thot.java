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
        name = "Normie Thot";
        level = generator.nextInt(level+10)+generator.nextInt(level)/2;
        health = generator.nextInt(2^level+20)+10;
        damage = generator.nextInt(2^level+8)+1;
        xpDropped = health + damage /2;
    }

    public void action(TSG tsg)
    {
        if(health>0) {
            if (tsg.inCombat) {
                if(generator.nextInt(100)<100*hitChance)
                {
                    tsg.player.takeDamage(tsg,damage);
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

    public void dialogue(TSG tsg)
    {
        int difference = tsg.player.getLevel()-level;

        if(difference<=0)
        {
            tsg.appendMessage("She is way out of your league and you can't even find a way to open a conversation");
            return;
        }

        int chance = difference*10;
        tsg.appendMessage("[You]: Stop being a thot!");

        if(chance<generator.nextInt(99))
        {
            tsg.appendMessage("[Thot]: You might be right... I need to go reconsider my life choices..");
            tsg.thotDefeated(xpDropped/2);
        }else{
            tsg.appendMessage("[Thot]: You think you can tell me how to live my life! You are just jealous because you aren't me!");
        }
    }
}
