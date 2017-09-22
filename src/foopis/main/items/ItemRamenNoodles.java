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
        tsg.appendMessage("You have consumed RaMeN NoOdLeS");
        tsg.appendMessage("Its cheap, but still surprisingly filling");
        tsg.player.heal(tsg,healthBoost);
        return true;
    }
}
