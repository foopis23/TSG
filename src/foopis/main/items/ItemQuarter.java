package foopis.main.items;

import foopis.main.TSG;

public class ItemQuarter extends Item
{
    public ItemQuarter()
    {
        name = "Quater Pounder with Cheese";
    }

    public boolean use(TSG tsg)
    {
        int healthBoost = 10;
        int oldHealth = tsg.player.getHealth();
        tsg.player.heal(healthBoost);
        tsg.appendMessage("You have consumed a number 3, sub Artisan Roll, add Mac Sauce and Shredded Lettuce");
        tsg.appendMessage("You feel thicc now");
        tsg.appendMessage("Will To Live: "+tsg.player.getHealthLimit()+"--1-->"+(tsg.player.getHealthLimit()-1));
        tsg.player.setMaxHealth(tsg.player.getHealthLimit()-1);
        tsg.appendMessage("Health: "+oldHealth+" -+"+healthBoost+ "-> "+tsg.player.getHealth());
        return true;
    }
}
