package foopis.main;

import foopis.main.commands.*;
import foopis.main.enemies.Thot;
import foopis.main.items.*;
import foopis.main.items.weapons.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class TSG{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final String TITLE = "TSG";
    public static final String VERSION = "v2.1 Alpha";

    public Thot thot;
    public DungeonHandler dungeonHandler;
    public Player player;

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
        commandHandler.add(new CommandInventory());
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
        items.add(new ItemQuarter());
        items.add(new ItemRamenNoodles());
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
        player = new Player(this);
        inCombat = false;
        hasText = false;
        thot = null;
        dungeonHandler.createFloor(this);
    }

    public void encounter(double chance)
    {
        int sampleSize = 1000;
        if(random.nextInt(sampleSize-1)<=(sampleSize*chance))
        {
            thot = new Thot(player.getLevel(), this);
            inCombat = true;
            appendMessage("You have been encountered by "+thot.getName());
        }
    }

    public void thotDefeated(int xp)
    {
        appendMessage(thot.getName()+" has been slain");
        player.gainExperience(xp,this);
        inCombat = false;
        thot = null;
    }

    public Item getItemByName(String name)
    {
        for(Item item: items)
        {
            if(item.getName().trim().toLowerCase().contains(name.toLowerCase().trim()))
            {
                return item;
            }
        }
        return null;
    }

    public Weapon getWeaponByName(String name)
    {
        for(Weapon weapon: weapons)
        {
            if(weapon.getName().trim().toLowerCase().contains(name.trim().toLowerCase()))
            {
                return weapon;
            }
        }
        return null;
    }

    public void getRandomIOW()
    {
        double chanceOfItem = .65;
        int i= random.nextInt(99);

        if(chanceOfItem<(99*chanceOfItem))
        {
            i = random.nextInt(items.size()-1);
            player.obtainItem(items.get(i), this);
        }else{
            i = random.nextInt(weapons.size()-1);
            player.obtainWeapon(weapons.get(i),this);
        }
    }

    public void gameOver()
    {
        appendMessage("GameOver! You died at level "+player.getLevel());
        appendMessage("Starting New Game!");
        player.reset(this);
        inCombat = false;
        hasText = false;
        thot = null;
        dungeonHandler.createFloor(this);
    }

    void runAction(String input)
    {
        commandHandler.RunAction(input,this);
        player.run(this);
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
        display.changeFont(new Font(font, type, size));
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
