package foopis.main.items;

import foopis.main.TSG;

public class ItemRamenNoodles extends Item
{
    public ItemRamenNoodles()
    {
        name = "Ramen Noodles";
    }

    public boolean use(TSG tsg)
    {
        int healthBoost = 10;
        int oldHealth = tsg.player.getHealth();
        tsg.player.heal(healthBoost);
        tsg.appendMessage("You have consumed RaMeN NoOdLeS");
        tsg.appendMessage("Its cheap, but still surprisingly filling");
        tsg.appendMessage("Health: "+oldHealth+" -+"+healthBoost+ "-> "+tsg.player.getHealth());
        return true;
    }
}
