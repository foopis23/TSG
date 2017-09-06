package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponDiamondPicaxe extends Weapon
{

    public WeaponDiamondPicaxe()
    {
        name = "Diamond Pickaxe";
        damage = 12;
    }

    public boolean use(TSG tsg)
    {
        tsg.attack(this);
        tsg.appendMessage("The Thot has cringed, accuracy dropped");
        tsg.thot.setHitChance(.75);
        return true;
    }
}
