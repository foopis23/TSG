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
        double healthBoost = 20;
        if(i<100*chanceOfBoneless)
        {
            int oldHealth = tsg.health;
            tsg.health+=healthBoost;
            if(tsg.health>tsg.healthLimit)
            {
                tsg.health=(tsg.health-(tsg.health-tsg.healthLimit));
            }
            tsg.appendMessage("You consumed that BONELESS Pizza");
            tsg.appendMessage("Health: "+oldHealth+" -+"+healthBoost+ "-> "+tsg.health);
        }else{
            tsg.appendMessage("THERE ARE BONES IN THIS PIZZA!");
            int oldHealth = tsg.health;
            tsg.health-=(healthBoost/2);
            if(tsg.health>tsg.healthLimit)
            {
                tsg.health=(tsg.health-(tsg.health-tsg.healthLimit));
            }
            tsg.appendMessage("Health: "+oldHealth+" --"+healthBoost/2+ "-> "+tsg.health);
        }
        return true;
    }
}
