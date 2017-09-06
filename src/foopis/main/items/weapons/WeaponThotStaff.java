package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponThotStaff extends Weapon
{

    public WeaponThotStaff()
    {
        name = "Thot Slayin Staff";
        damage = 20;
    }

    public boolean use(TSG tsg)
    {
        tsg.appendMessage("BE GONE THOT!");
        tsg.attack(this);
        return true;
    }
}
