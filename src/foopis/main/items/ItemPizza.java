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
            tsg.appendMessage("You consumed that BONELESS Pizza");
            tsg.player.heal(tsg,healthBoost);
        }else{
            tsg.appendMessage("THERE ARE BONES IN THIS PIZZA!");
            tsg.player.takeDamage(tsg,healthBoost/2);
        }
        return true;
    }
}
