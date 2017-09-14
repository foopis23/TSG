package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponNormieSword extends Weapon
{

    public WeaponNormieSword()
    {
        name = "Normie Sword";
        damage = 10;
    }

    public boolean use(TSG tsg)
    {
        return true;
    }
}
