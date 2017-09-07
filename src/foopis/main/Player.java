package foopis.main;

import foopis.main.items.Item;
import foopis.main.items.weapons.Weapon;

public class Player
{
    //Health
    private int health;
    private int healthLimit;

    //Buffs
    private int damageBoost;
    private int defenseBoost;

    //Level Stuff
    private int xp;
    private int xpToLevel;
    private int level;

    //Inventory Stuff
    private Item[] items;
    private Weapon weapon;
    private Item obtainedItem;
    private Weapon obtainedWeapon;

    public Player(TSG tsg)
    {
        health = 100;
        healthLimit = health;
        damageBoost = 0;
        defenseBoost= 0;
        xp=0;
        xpToLevel = 100;
        level = 1;
        items = new Item[3];
        items[0] = tsg.getItemByName("Ramen");
        items[1] = null;
        items[2] = null;
        weapon = tsg.getWeaponByName("Normie Sword");
        obtainedItem = null;
        obtainedWeapon = null;
    }

    public void reset(TSG tsg)
    {
        health = 100;
        healthLimit = health;
        damageBoost = 0;
        defenseBoost = 0;
        xp=0;
        xpToLevel = 100;
        level = 1;
        items[0] = tsg.getItemByName("Ramen");
        items[1] = null;
        items[2] = null;
        weapon = tsg.getWeaponByName("Normie Sword");
        obtainedItem = null;
        obtainedWeapon = null;
    }

    public void run(TSG tsg)
    {
        if(health<=0)
        {
            tsg.gameOver();
        }

        if(xp>=xpToLevel)
        {
            levelUp(tsg);
        }
    }

    public void levelUp(TSG tsg)
    {
        tsg.appendMessage("LEVEL UP!");
        tsg.appendMessage("Level: "+this.level+" -+1--> "+(this.level+1));
        level++;
        this.xp -= xpToLevel;
        xpToLevel+=50;
        healthLimit+=10;
        health+=10;

        if(health>healthLimit)
        {
            health=health-(health-healthLimit);
        }
    }

    public void takeDamage(int damage)
    {
        health-=(damage-defenseBoost);
    }

    public void heal(int health)
    {
        this.health+=health;
        if(health>healthLimit)
        {
            this.health -= (this.health-healthLimit);
        }
    }

    public void gainExperience(int xp, TSG tsg)
    {
        tsg.appendMessage("XP: "+this.xp+" -+"+xp+"--> "+(this.xp+xp));
        this.xp+=xp;
    }

    public void attack(TSG tsg)
    {
        {
            if (tsg.inCombat) {
                if (weapon != null) {
                    int d = weapon.getDamage() + damageBoost;
                    if (tsg.thot != null) {
                        tsg.thot.takeDamage(d);
                        weapon.use(tsg);
                    }
                    tsg.appendMessage("You did " + d + " damage with " + weapon.getName());
                } else {
                    if (tsg.thot != null) {
                        tsg.thot.takeDamage(2);
                    }
                    tsg.appendMessage("You did 2 damage with your fist");
                }
            } else {
                tsg.appendMessage("You aren't in combat right now");
            }
        }
    }

    public void useItem(int i,TSG tsg)
    {
        if(items[i-1]!=null) {
            if (items[i - 1].use(tsg)) {
                items[i - 1] = null;
            }
        }else{
            tsg.appendMessage("Item slot "+i+" is empty");
        }
    }

    public void obtainWeapon(Item item, TSG tsg)
    {
        try{
            Weapon w = (Weapon) item;
            obtainedWeapon = w;
            tsg.appendMessage("You have obtained "+w.getName());
            if(weapon!=null)
            {
                String s=getInventory();
                tsg.appendMessage(s);
                tsg.appendMessage("You already have a weapon, if you would like to replace it enter yes, if not no");
            }else{
                weapon = w;
                obtainedWeapon=null;
            }

        }catch (Exception e)
        {
            tsg.appendMessage("NOT WEAPON ERROR");
        }
    }

    public void obtainItem(Item item, TSG tsg) {
        tsg.appendMessage("You have obtained " + item.getName());
        obtainedItem = item;
        if (items[0] == null) {
            items[0] = item;
            obtainedItem = null;
        } else if (items[1] == null) {
            items[1] = item;
            obtainedItem = null;
        } else if (items[2] == null) {
            items[2] = item;
            obtainedItem = null;
        } else {
            String s = getInventory();
            tsg.appendMessage(s);
            tsg.appendMessage("Your Inventory is full, if you want to replace an Item enter the #(1-3) now, if not enter no");
        }
    }

    public String getInventory()
    {
        String s="---------------Inventory---------------";
        s+="\n";
        s+="Item-1: ";
        if(items[0]!=null) {s+=items[0].getName()+"\n";}
        else{s+="empty \n";}

        s+="Item-2: ";
        if(items[1]!=null) {s+=items[1].getName()+"\n";}
        else{s+="empty\n";}

        s+="Item-3: ";
        if(items[2]!=null) {s+=items[2].getName()+"\n";}
        else{s+="empty\n";}

        s+="Weapon: ";
        if(weapon!=null) {s+=weapon.getName()+"";}
        else{s+="empty\n";}
        s+="------------------------------------------";

        return s;
    }

    public String getStats()
    {
        String s ="----------Stats----------";
        s+="\n";
        s+="Level: " + level;
        s+="\n";
        s+="Xp: " + xp + "/" + xpToLevel;
        s+="\n";
        s+="Health: " + health + "/" + healthLimit;
        s+="\n";
        s+="Damage Boost: " + damageBoost;
        s+="\n";
        s+="Defense Boost: " + defenseBoost;
        s+="\n";
        s+="-------------------------";
        return s;
    }

    public Item getObtainedItem()
    {
        return obtainedItem;
    }

    public Item getObtainedWeapon()
    {
        return obtainedWeapon;
    }

    public void replaceWeapon()
    {
        this.weapon = this.obtainedWeapon;
        this.obtainedItem = null;
    }

    public void scrapObtainedWeapon()
    {
        obtainedWeapon = null;
    }

    public void replaceItem(int i)
    {
        items[i-1] = obtainedItem;
        obtainedItem = null;
    }

    public void scarpObtainedItem()
    {
        obtainedItem=null;
    }

    public void setDamageBoost(int damageBoost)
    {
        this.damageBoost = damageBoost;
    }

    public void setDefenseBoost(int defenseBoost)
    {
        this.defenseBoost = defenseBoost;
    }

    public void setMaxHealth(int maxHealth)
    {
        healthLimit = maxHealth;
        if(health<healthLimit)
        {
            health-= (health-healthLimit);
        }
    }

    public int getLevel()
    {
        return level;
    }

    public Item getItems(int i)
    {
        return items[i-1];
    }

    public Item getWeapon()
    {
        return weapon;
    }

    public int getHealth()
    {
        return health;
    }

    public int getHealthLimit()
    {
        return healthLimit;
    }

    public int getDamageBoost()
    {
        return damageBoost;
    }

    public int getDefenseBoost()
    {
        return defenseBoost;
    }
}