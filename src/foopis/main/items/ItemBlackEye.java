package foopis.main.items;

import foopis.main.TSG;

public class ItemBlackEye implements Item
{

    @Override
    public boolean use(TSG tsg) {
        if(tsg.inCombat)
        {
            tsg.appendMessage("Thot Type: "+tsg.thot.getName()+ " Health: "+tsg.thot.getHealth()+" Damage: "+tsg.thot.getDamage());
        }else{
            double d = tsg.getCurrentRoom().getEncountChance() * 100;
            tsg.appendMessage("The Thot Encounter Chance in this room is "+d+"%");
        }
        return true;
    }

    @Override
    public String getName() {
        return "Black Eye";
    }
}
