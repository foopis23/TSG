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
        tsg.appendMessage("You have consumed an onigiri");
        tsg.appendMessage("You now have the power of god and anime on your side!");
        tsg.player.heal(tsg,healthBoost);
        return true;
    }
}
