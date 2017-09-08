package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponSagiriVoice extends Weapon
{

    public WeaponSagiriVoice()
    {
        name = "sagiri-chan no koe";
        damage = 15;
    }

    public boolean use(TSG tsg)
    {
        tsg.appendMessage("BAKA-ONIICHAN!");
        return true;
    }
}
