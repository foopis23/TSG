package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponCarbine implements Weapon
{
    int uses = 3;
    @Override
    public int getDamage() {
        return 200;
    }

    @Override
    public boolean use(TSG tsg)
    {
        tsg.appendMessage("You spawn peeked the " +tsg.thot.getName());
        tsg.attack(this);
        uses--;
        if(uses<=0)
        {
            tsg.appendMessage("Your weapon has had its Acog removed and is no longer useful, Thanks Ubisoft");
            tsg.weapon=null;
        }
        return true;
    }

    @Override
    public String getName() {
        return "416-C Carbine";
    }
}
