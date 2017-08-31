package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponDiamondPicaxe implements Weapon
{
    @Override
    public boolean use(TSG tsg)
    {
        tsg.attack(this);
        tsg.appendMessage("The Thot has cringed, accuracy dropped");
        tsg.thot.setHitChance(.75);
        return true;
    }

    @Override
    public int getDamage() {
        return 8;
    }

    @Override
    public String getName() {
        return "Diamond Pickaxe";
    }
}
