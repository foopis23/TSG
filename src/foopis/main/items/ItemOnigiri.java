package foopis.main.items;

import foopis.main.TSG;

public class ItemOnigiri implements Item
{

    @Override
    public boolean use(TSG tsg)
    {
        int healthBoost = 25;
        int oldHealth = tsg.health;
        tsg.health+=healthBoost;
        if(tsg.health>tsg.healthLimit)
        {
            tsg.health=(tsg.health-(tsg.health-tsg.healthLimit));
        }
        tsg.appendMessage("You have consumed an onigiri");
        tsg.appendMessage("You now have the power of god and anime on your side!");
        tsg.appendMessage("Health: "+oldHealth+" -+"+healthBoost+ "-> "+tsg.health);
        return true;
    }

    @Override
    public String getName()
    {
        return "Onigiri";
    }
}
