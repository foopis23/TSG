package foopis.main;

import foopis.main.commands.*;
import foopis.main.enemies.Thot;
import foopis.main.items.*;
import foopis.main.items.weapons.*;
import foopis.main.rooms.*;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TSG{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final String TITLE = "TSG";
    public static final String VERSION = "v2.0 Alpha";

    public Item obtainedItem;
    public Weapon obtainedWeapon;
    public Thot thot;
    public DungeonHandler dungeonHandler;

    //Player Variables///////////////
    public int health;
    public int healthLimit;
    public int damageBoost;
    public int defenseBoost;
    public Weapon weapon;
    public int xp;
    public int xpToLevel;
    public int level;
    public Item item1;
    public Item item2;
    public Item item3;
    /////////////////////////////////

    //GameStates/////////////////////
    private boolean debug;
    public boolean inCombat;
    /////////////////////////////////

    //GameSystems////////////////////
    private Display display;
    public Random random;
    private boolean hasText;
    /////////////////////////////////

    //Lists//////////////////////////
    CommandHandler commandHandler;
    private LinkedList<Item> items;
    private LinkedList<Weapon> weapons;
    /////////////////////////////////

    public TSG()
    {
        display = new Display(this);
        random = new Random();
        debug = true;
        initCommands();
        initItems();
        initWeapons();
        initDungeon();
        initGame();
    }

    private void initCommands()
    {
        commandHandler = new CommandHandler();

        commandHandler.add(new CommandHelp());
        commandHandler.add(new CommandClear());
        commandHandler.add(new CommandComicSans());
        commandHandler.add(new CommandUseItem());
        commandHandler.add(new CommandItems());
        commandHandler.add(new CommandAttack());
        commandHandler.add(new CommandGo());
        commandHandler.add(new CommandRoomAction());
        commandHandler.add(new CommandLook());
        commandHandler.add(new CommandStats());
        if(debug)
        {
            commandHandler.add(new CommandGiveItem());
        }
    }

    private void initItems()
    {
        items = new LinkedList<>();

        items.add(new ItemOnigiri());
        items.add(new ItemDankMeme());
        items.add(new ItemPizza());
        items.add(new ItemAshScroll());
        items.add(new ItemBlackEye());
    }

    private void initWeapons()
    {
        weapons = new LinkedList<>();

        weapons.add(new WeaponNormieSword());
        weapons.add(new WeaponDiamondPicaxe());
        weapons.add(new WeaponThotStaff());
        weapons.add(new WeaponCarbine());
    }

    private void initDungeon()
    {
        dungeonHandler = new DungeonHandler();
    }

    public void initGame()
    {

        display.requestFocus();
        level = 1;
        xp = 0;
        xpToLevel = 100;
        health = 100;
        healthLimit = 100;
        damageBoost = 0;
        defenseBoost = 0;
        weapon = weapons.get(0);
        inCombat = false;
        item1 = items.get(0);
        item2 = null;
        item3 = null;
        obtainedItem = null;
        obtainedWeapon = null;
        hasText = false;
        thot = null;
        dungeonHandler.createFloor(this);
    }

    public void attackPlayer(int damage)
    {
        health-=damage;
    }

    public void attack(Weapon w)
    {
        if(inCombat) {
            if (w != null) {
                int d = w.getDamage() + damageBoost;
                if (thot != null) {
                    thot.takeDamage(d);
                }
                appendMessage("You did " + d + " damage with " + w.getName());
            } else {
                if (thot != null) {
                    thot.takeDamage(2);
                }
                appendMessage("You did 2 damage with your fist");
            }
        }else{
            appendMessage("You aren't in combat right now");
        }
    }

    public void encounter(double chance)
    {
        //int sampleSize = 1000;
        //if(random.nextInt(sampleSize-1)<=(sampleSize*chance))
        //{
            //thot = new Thot(level);
            //inCombat = true;
            //appendMessage("You have been encountered by "+thot.getName());
        //}
    }

    public void levelUp()
    {
        appendMessage("LEVEL UP!");
        appendMessage("Level: "+this.level+" -+1--> "+(this.level+1));
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

    public void thotDefeated(int xp)
    {
        appendMessage(thot.getName()+" has been slain");
        appendMessage("XP: "+this.xp+" -+"+xp+"--> "+(this.xp+xp));
        inCombat = false;
        this.xp+=xp;

        if(this.xp>=xpToLevel)
        {
            if(level<10)
            {
                levelUp();
            }
        }
        thot = null;
    }

    public String getInventory()
    {
        String s="Item-1: ";
        if(item1!=null) {s+=item1.getName()+"\n";}
        else{s+="empty \n";}

        s+="Item-2: ";
        if(item2!=null) {s+=item2.getName()+"\n";}
        else{s+="empty\n";}

        s+="Item-3: ";
        if(item3!=null) {s+=item3.getName()+"\n";}
        else{s+="empty\n";}

        s+="Weapon: ";
        if(weapon!=null) {s+=weapon.getName()+"";}
        else{s+="empty";}

        return s;
    }

    public void getRandomIOW()
    {
        int i= random.nextInt(weapons.size()+items.size()-1);
        Item item = null;
        if(i>weapons.size())
        {
            item = items.get((i-(weapons.size()-1)));
            obtainItem(item);
        }else{
            item = weapons.get(i);
            obtainWeapon(item);
        }
    }

    public void obtainItem(Item item)
    {
        appendMessage("You have obtained "+item.getName());
        obtainedItem = item;
        if(item1==null)
        {
            item1=item;
            obtainedItem=null;
        }else if(item2==null)
        {
            item2=item;
            obtainedItem=null;
        }else if(item3==null)
        {
            item3=item;
            obtainedItem=null;
        }else{
            appendMessage(getInventory());
            appendMessage("Your Inventory is full, if you want to replace an Item enter the #(1-3) now, if not enter no");
;
        }
    }

    public void obtainWeapon(Item item)
    {
        try{
            Weapon w = (Weapon) item;
            obtainedWeapon = w;
            appendMessage("You have obtained "+w.getName());
            if(weapon!=null)
            {
                appendMessage(getInventory());
                appendMessage("You already have a weapon, if you would like to replace it enter yes, if not no");
            }else{
                weapon = w;
                obtainedWeapon=null;
            }

        }catch (Exception e)
        {
            appendMessage("NOT WEAPON ERROR");
        }
    }

    public void gameOver()
    {
        appendMessage("GameOver! You died at level "+level);
        appendMessage("Starting New Game!");
        initGame();
    }

    void runAction(String input)
    {
        commandHandler.RunAction(input,this);

        if(health<=0)
        {
            gameOver();
        }
    }

    public void appendMessage(String message)
    {
        if(hasText) {
            display.append("\n" + message);
        }else{
            display.append(message);;
            hasText=true;
        }
    }

    public void clearDisplay()
    {
        display.setText(null);
        hasText=false;
    }

    public void changeFont(String font, int type, int size)
    {
        display.setFont(new Font(font, type, size));
    }

    public static void main(String[] args)
    {
        new TSG();
    }

    public LinkedList<Command> getCommands()
    {
        return commandHandler;
    }

    public LinkedList<Item> getItems()
    {
        return items;
    }
}