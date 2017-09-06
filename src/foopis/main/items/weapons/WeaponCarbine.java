package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponCarbine extends Weapon
{

    public WeaponCarbine()
    {
        name = "416-C Carbine";
        damage = 300;
    }

    int uses = 3;

    public boolean use(TSG tsg)
    {
        if(uses>0) {
            tsg.appendMessage("You spawn peeked the " + tsg.thot.getName());
        }else{
            tsg.appendMessage("You don't spawn peek the "+tsg.thot.getName());
        }
        uses--;
        if(uses<=0)
        {
            tsg.appendMessage("Your weapon has had its Acog removed and is no longer useful, Thanks Ubisoft");
            this.damage=8;
        }
        return true;
    }
}
