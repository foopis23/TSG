package foopis.main.items.weapons;

import foopis.main.TSG;

public class WeaponYoyo extends Weapon {

    double chanceOfCombo;

    public WeaponYoyo()
    {
        name = "YoYo";
        damage = 6;
        chanceOfCombo = .50;
    }

    public boolean use(TSG tsg)
    {
        boolean combo = true;
        while(combo)
        {
            int r = tsg.random.nextInt(100);
            if(r<(100*chanceOfCombo))
            {
                chanceOfCombo -= .25;
                tsg.thot.takeDamage(damage);
                tsg.appendMessage("You landed a combo doing "+damage+" more damage");
            }else{
                chanceOfCombo = .75;
                combo = false;
            }
        }
        return true;
    }
}
