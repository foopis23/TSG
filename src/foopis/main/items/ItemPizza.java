package foopis.main.items;

import foopis.main.TSG;

public class ItemPizza extends Item {

    public ItemPizza()
    {
        name = "Pizza";
    }

    public boolean use(TSG tsg)
    {
        int i = tsg.random.nextInt(100);
        double chanceOfBoneless = .8;
        int healthBoost = 20;
        if(i<100*chanceOfBoneless)
        {
            int oldHealth = tsg.player.getHealth();
            tsg.player.heal(healthBoost);
            tsg.appendMessage("You consumed that BONELESS Pizza");
            tsg.appendMessage("Health: "+oldHealth+" --(+"+healthBoost+ ")--> "+tsg.player.getHealth());
        }else{
            tsg.appendMessage("THERE ARE BONES IN THIS PIZZA!");
            int oldHealth = tsg.player.getHealth();
            tsg.player.takeDamage(healthBoost/2);
            tsg.appendMessage("Health: "+oldHealth+" --(-"+healthBoost/2+ ")--> "+tsg.player.getHealth());
        }
        return true;
    }
}
