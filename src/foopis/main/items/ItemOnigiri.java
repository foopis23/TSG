package foopis.main.items;

import foopis.main.TSG;

public class ItemOnigiri extends Item
{
    public ItemOnigiri()
    {
        name = "Onigiri";
    }

    public boolean use(TSG tsg)
    {
        int healthBoost = 25;
        int oldHealth = tsg.player.getHealth();
        tsg.player.heal(healthBoost);
        tsg.appendMessage("You have consumed an onigiri");
        tsg.appendMessage("You now have the power of god and anime on your side!");
        tsg.appendMessage("Health: "+oldHealth+" -+"+healthBoost+ "-> "+tsg.player.getHealth());
        return true;
    }
}
