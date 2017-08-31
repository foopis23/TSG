package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponNormieSword implements Weapon
{
    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public boolean use(TSG tsg)
    {
        tsg.attack(this);
        return true;
    }

    @Override
    public String getName() {
        return "Normie Sword";
    }
}
