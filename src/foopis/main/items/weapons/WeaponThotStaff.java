package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponThotStaff implements Weapon
{

    @Override
    public int getDamage() {
        return 20;
    }

    @Override
    public boolean use(TSG tsg)
    {
        tsg.appendMessage("BE GONE THOT!");
        tsg.attack(this);
        return true;
    }

    @Override
    public String getName() {
        return "Thot Slayin Staff";
    }
}
