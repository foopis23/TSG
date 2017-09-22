package foopis.main;

import foopis.main.items.Item;
import foopis.main.items.weapons.Weapon;

import java.util.LinkedList;

public class Player
{
    //Health
    private int health;
    private int maximumHealth;

    //Buffs
    private int damageBoost;
    private int defenseBoost;

    //Level Stuff
    private int xp;
    private int xpToLevel;
    private int level;

    //Inventory Stuff
    private LinkedList<Item> items;
    private Weapon weapon;
    private Item obtainedItem;
    private Weapon obtainedWeapon;
    private int inventorySize;

    public Player(TSG tsg)
    {
        items = new LinkedList<>();
        reset(tsg);
    }

    public void reset(TSG tsg)
    {
        health = 100;
        maximumHealth = health;
        damageBoost = 0;
        defenseBoost = 0;
        xp=0;
        xpToLevel = 100;
        level = 1;
        items.clear();
        items.add(tsg.getItemByName("Ramen"));
        weapon = tsg.getWeaponByName("Normie Sword");
        obtainedItem = null;
        obtainedWeapon = null;
        inventorySize = 5;
    }

    public void update(TSG tsg)
    {
        if(items.size()>inventorySize)
        {//Check for inventory size to big oof
            for(int i = inventorySize;i<items.size();i++)
            {
                tsg.appendMessage("Inventory size to thicc, report to developer if you see this message");
                items.remove(i);
            }
        }

        if(health>maximumHealth)
        {//Check More Health Than Maximum
            health = health -(health-maximumHealth);
        }

        if(health<=0)
        {//Health is zero, meaning player died
            tsg.gameOver();
        }

        if(xp>=xpToLevel)
        {//Checking xp to make sure player levels up
            levelUp(tsg);
        }
    }


    public void attack(TSG tsg)
    {
        {
            if (tsg.inCombat) {
                if (weapon != null) {
                    int d = weapon.getDamage() + damageBoost;
                    if (tsg.thot != null) {
                        tsg.thot.takeDamage(d);
                        tsg.appendMessage("You did " + d + " damage with " + weapon.getName());
                        weapon.use(tsg);
                    }
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

    public String getInventory()
    {
        String s="----------------------------Inventory----------------------------";
        s+="\n";
        for(int i=0;i<inventorySize;i++)
        {
            if(i<items.size()){s+= (i+1) +") "+ items.get(i).getName() +"\n";}else{s+= (i+1) +") Empty\n";}
        }
        return s;
    }

    public String getStats()
    {
        String s ="------------------------------Stats-------------------------------";
        s+="\n";
        s+="Level: " + level;
        s+="\n";
        s+="Xp: " + xp + "/" + xpToLevel;
        s+="\n";
        s+="Health: " + health + "/" + maximumHealth;
        s+="\n";
        s+="Damage Boost: " + damageBoost;
        s+="\n";
        s+="Defense Boost: " + defenseBoost;
        return s;
    }

    //Stats Modifiers/////////////////////////////////////////
    public int takeDamage(TSG tsg, int damage)
    {
        tsg.appendMessage("You took "+damage+" damage");
        health-=(damage-defenseBoost);
        return health;
    }

    public int heal(TSG tsg,int health)
    {
        tsg.appendMessage("You gain "+health+" health");
        this.health+=health;
        return health;
    }

    public void levelUp(TSG tsg)
    {
        tsg.appendMessage("LEVEL UP!");
        tsg.appendMessage("Level: "+this.level+" --(+1)--> "+(this.level+1));
        level++;
        this.xp -= xpToLevel;
        xpToLevel+=50;
        maximumHealth+=5;
        health+=5;
    }

    public int gainExperience(int xp, TSG tsg)
    {
        tsg.appendMessage("You've gained "+ xp +"xp");
        this.xp+=xp;
        return xp;
    }

    public int raiseMaximumHealth(TSG tsg, int difference)
    {
        tsg.appendMessage("You will to live has risen "+difference);
        maximumHealth+=difference;
        return maximumHealth;
    }

    public int lowerMaximumHealth(TSG tsg, int difference)
    {
        tsg.appendMessage("Your will to live has dropped "+difference);
        maximumHealth-=difference;
        return maximumHealth;
    }

    public int raiseDefense(TSG tsg, int difference)
    {
        tsg.appendMessage("Your Defense has risen "+difference);
        defenseBoost+=difference;
        return defenseBoost;
    }

    public int lowerDefense(TSG tsg, int differnece)
    {
        tsg.appendMessage("Your defense has dropped "+differnece);
        defenseBoost-=differnece;
        return defenseBoost;
    }

    public int raiseDamage(TSG tsg, int difference)
    {
        tsg.appendMessage("Your damage has risen "+difference);
        damageBoost+=difference;
        return damageBoost;
    }

    public int dropDamage(TSG tsg, int difference)
    {
        tsg.appendMessage("Your damage has dropped "+difference);
        damageBoost-=difference;
        return damageBoost;
    }

    public int raiseInventorySize(TSG tsg, int difference)
    {
        tsg.appendMessage("Your inventory size has increased "+difference+" slots");
        inventorySize+=difference;
        return inventorySize;
    }
    //Stats Modifiers/////////////////////////////////////////

    //Item Stuff/////////////////////////////////////////////
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

    public void replaceItem(int i)
    {
        items.set(i,obtainedItem);
        obtainedItem = null;
    }

    public void scrapObtainedWeapon()
    {
        obtainedWeapon = null;
    }

    public void scarpObtainedItem()
    {
        obtainedItem=null;
    }

    public void useItem(int i,TSG tsg)
    {
        if(i-1<inventorySize&&i-1<items.size()) {
            if (items.get(i - 1).use(tsg))
            {
                items.remove(i - 1);
            }
        }else{
            tsg.appendMessage("This item slot is empty");
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
        if(items.size()<inventorySize)
        {
            items.add(obtainedItem);
            obtainedItem = null;
        }else{
            tsg.appendMessage("You inventory is filled, If you would like to replace it enter a number between 1 and "+inventorySize+", if not enter no");
        }
    }
    //Item Stuff/////////////////////////////////////////////

    //Getters////////////////////////////////////////////////
    public int getHealth()
    {
        return health;
    }

    public int getMaximumHealth()
    {
        return maximumHealth;
    }

    public int getDamageBoost()
    {
        return damageBoost;
    }

    public int getDefenseBoost()
    {
        return defenseBoost;
    }

    public int getXp()
    {
        return xp;
    }

    public int getXpToLevel()
    {
        return xpToLevel;
    }

    public int getLevel()
    {
        return level;
    }

    public LinkedList<Item> getItems()
    {
        return items;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public int getInventorySize()
    {
        return inventorySize;
    }
    //Getters////////////////////////////////////////////////
}