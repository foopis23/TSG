package foopis.main.items;

import foopis.main.TSG;

public class ItemQuarter extends Item
{
    public ItemQuarter()
    {
        name = "Quarter Pounder with Cheese";
    }

    public boolean use(TSG tsg)
    {
        tsg.appendMessage("You have consumed a number 3, sub Artisan Roll, add Mac Sauce and Shredded Lettuce");
        tsg.appendMessage("You feel thicc now");
        tsg.player.lowerMaximumHealth(tsg,1);
        tsg.player.heal(tsg,50);
        return true;
    }
}
