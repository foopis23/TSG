package foopis.main.items;

import foopis.main.TSG;

public class ItemAshScroll extends Item
{
    public ItemAshScroll()
    {
        name = "Ash's Scroll";
    }

    public boolean use(TSG tsg)
    {
        if(tsg.inCombat)
        {
            tsg.appendMessage("You unlock the seal of the scroll releasing its true power");
            tsg.appendMessage("Your hit box has now been shrunk for this battle, thanks Ubisoft");
            tsg.thot.setHitChance(0);
            return true;
        }else{
            tsg.appendMessage("You can only use this item in combat");
            return false;
        }
    }
}
