package foopis.main.enemies;

import foopis.main.TSG;

public interface Enemy
{
    void action(TSG tsg);
    void takeDamage(int damage);
    void setHitChance(double i);
    int getHealth();
    int getDamage();
    String getName();
}
