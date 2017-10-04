package foopis.main;

import foopis.main.items.Item;
import foopis.main.items.weapons.Weapon;

import java.util.LinkedList;
import java.util.Random;

public class Player
{
    Random random;

    //Health
    private int health;
    private int maximumHealth;

    //Buffs
    private double damageBoost;
    private double defenseBoost;

    //Level Stuff
    private int xp;
    private int xpToLevel;
    private int level;
    private int mana;
    private int maximumMana;
    private int intelligenceLevel;
    private int strengthLevel;
    private int luckLevel;
    private int healthLevel;
    private int statPoints;
    private int floors;

    //Inventory Stuff
    private LinkedList<Item> items;
    private Weapon weapon;
    private Item obtainedItem;
    private Weapon obtainedWeapon;
    private int inventorySize;

    //Skills
    private boolean dialogue;
    private boolean itemBlock;
    private double unblockedDefense;
    private boolean hasBlocked;

    public Player(TSG tsg, Random random)
    {
        this.random = random;
        items = new LinkedList<>();
        reset(tsg);
    }

    public void reset(TSG tsg)
    {
        //Skills
        dialogue = false;
        itemBlock = false;

        ////////
        health = 100;
        maximumHealth = health;
        damageBoost = 0;
        defenseBoost = 0;
        xp=0;
        xpToLevel = 100;
        level = 1;
        intelligenceLevel = 0;
        strengthLevel = 0;
        luckLevel = 0;
        healthLevel = 0;
        unblockedDefense = -1;
        hasBlocked = false;
        statPoints = 0;
        maximumMana = 100;
        mana = maximumMana;
        items.clear();
        items.add(tsg.getItemByName("Ramen"));
        weapon = tsg.getWeaponByName("Normie Sword");
        obtainedItem = null;
        obtainedWeapon = null;
        inventorySize = 5;
        floors = 1;
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

        if(unblockedDefense>-1)
        {
            if(hasBlocked)
            {
                defenseBoost = unblockedDefense;
                unblockedDefense = -1;
            }else{
                hasBlocked = true;
            }
        }
    }

    public void dialogue(TSG tsg)
    {
        if(tsg.inCombat)
        {
            if(dialogue)
            {
                tsg.thot.dialogue(tsg);
            }else{
                tsg.appendMessage("You don't have enough intelligence to use dialogue!");
            }
        }else{
            tsg.appendMessage("You can only use this in combat!");
        }
    }

    public void attack(TSG tsg)
    {
        {
            if (tsg.inCombat) {
                if (weapon != null) {
                    int d = weapon.getDamage() + (int)(weapon.getDamage()*damageBoost);
                    if (tsg.thot != null) {
                        if(random.nextInt(99)<(Math.sqrt(luckLevel*10)+5))
                        {
                            tsg.appendMessage("You got a critical hit!");
                            d+=(d/2);
                        }
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
        String s="----------------------------Inventory----------------------------\n";
        s+="Weapon: "+weapon.getName()+"\n";

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
        s+="Mana: "+mana +"/" +maximumMana;
        s+="\n";
        s+="Strength: " + strengthLevel;
        s+="\n";
        s+="Intelligence: " + intelligenceLevel;
        s+="\n";
        s+="Luck: " + luckLevel;
        return s;
    }

    //Stats Modifiers/////////////////////////////////////////
    public int takeDamage(TSG tsg, int damage)
    {
        tsg.appendMessage("You took "+(damage-defenseBoost)+" damage");
        health-=(damage-((int)damage*defenseBoost));
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

        level++;
        statPoints++;
        this.xp -= xpToLevel;
        xpToLevel+=50;
    }

    public int gainExperience(int xp, TSG tsg)
    {
        tsg.appendMessage("You gained "+ xp +"xp");
        this.xp+=xp;
        return xp;
    }

    public int raiseInventorySize(TSG tsg, int difference)
    {
        tsg.appendMessage("Your inventory size has increased "+difference+" slots");
        inventorySize+=difference;
        return inventorySize;
    }

    public void levelUpStrength(TSG tsg)
    {
        if(statPoints>0)
        {
            statPoints--;
            strengthLevel++;
            inventorySize = ((int) strengthLevel/10) + 5;
            tsg.appendMessage("You leveled up your Strength!");
        }else{
            tsg.appendMessage("You don't have enough skill points for that!");
        }

        if(strengthLevel>=5)
        {
            if(!itemBlock)tsg.appendMessage("You have learned Item blocking");

            itemBlock = true;
        }
    }

    public void levelUpIntelligence(TSG tsg)
    {
        if(statPoints>0)
        {
            statPoints--;
            intelligenceLevel++;
            tsg.appendMessage("You leveled up your Intelligence");
        }else{
            tsg.appendMessage("You don't have enough skill points for that!");
        }

        if(intelligenceLevel>=5)
        {
            if(!dialogue)tsg.appendMessage("You have learned Dialogue!");

            dialogue = true;
        }
    }

    public void levelUpHealth(TSG tsg)
    {
        if(statPoints>0)
        {
            statPoints--;
            maximumHealth = (int) Math.sqrt(healthLevel*100)+100;
            health += (int) Math.sqrt(healthLevel*100);
            tsg.appendMessage("You leveled up your Health!");
        }else{
            tsg.appendMessage("You don't have enough skill points for that!");
        }
    }

    public void levelUpLuck(TSG tsg)
    {
        if(statPoints>0)
        {
            statPoints--;
            luckLevel++;
            tsg.appendMessage("You leveled up your Luck!");
        }else{
            tsg.appendMessage("You don't have enough skill points for that!");
        }
    }

    public void addStatPoint()
    {
        statPoints++;
    }

    public void nextFloor()
    {
        floors++;
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
        this.obtainedWeapon = null;
    }

    public void replaceItem(int i)
    {
        items.set(i-1,obtainedItem);
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
        if(itemBlock) {
            tsg.appendMessage("You have activated Item Block! Defense has risen to 25%!");
            unblockedDefense = defenseBoost;
            defenseBoost = .25;
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

    public int getXp()
    {
        return xp;
    }

    public int getXpToLevel()
    {
        return xpToLevel;
    }

    public int getStatPoints()
    {
        return statPoints;
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

    public int getFloors()
    {
        return floors;
    }
    //Getters////////////////////////////////////////////////
}