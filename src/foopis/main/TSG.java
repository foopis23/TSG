package foopis.main;

import foopis.main.commands.*;
import foopis.main.enemies.Thot;
import foopis.main.items.*;
import foopis.main.rooms.*;
import foopis.main.items.weapons.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class TSG
{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public Thot thot;
    public DungeonHandler dungeonHandler;
    public Player player;

    //GameStates/////////////////////
    public boolean canEncounter;
    private boolean debug;
    public boolean inCombat;
    boolean running;
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
    private LinkedList<String> commandsEntered;
    /////////////////////////////////

    public TSG()
    {
        random = new Random();
        debug = false;
        canEncounter = true;
        initDungeon();
        display = new Display(this);
        display.redrawMap();
        initCommands();
        initItems();
        initWeapons();
        initGame();
    }

    private void initCommands()
    {
        commandHandler = new CommandHandler();
        commandsEntered = new LinkedList<>();

        commandHandler.add(new CommandHelp());
        commandHandler.add(new CommandClear());
        commandHandler.add(new CommandComicSans());
        commandHandler.add(new CommandUseItem());
        commandHandler.add(new CommandAttack());
        commandHandler.add(new CommandGo());
        commandHandler.add(new CommandRoomAction());
        commandHandler.add(new CommandLook());
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
        weapons.add(new WeaponYoyo());
    }

    private void initDungeon()
    {
        dungeonHandler = new DungeonHandler();
    }

    private void initGame()
    {
        player = new Player(this);
        inCombat = false;
        hasText = false;
        thot = null;
        dungeonHandler.createFloor(this);
        display.displayStats(player.getStats(),player.getInventory());
        running = true;
        display.redrawMap();
    }

    public void enterDebug()
    {
        debug = true;

        commandHandler.add(new CommandGiveItem());
        commandHandler.add(new CommandEncounter());
        commandHandler.add(new CommandToggleEncounter());
    }

    public void encounter(double chance)
    {
        if(canEncounter) {
            int sampleSize = 1000;
            if (random.nextInt(sampleSize - 1) <= (sampleSize * chance)) {
                thot = new Thot(player.getLevel(), this);
                inCombat = true;
                appendMessage("-----------------------------------------------Battle-------------------------------------------------");
                appendMessage("You have been encountered by " + thot.getName());
            }
        }
    }

    public void thotDefeated(int xp)
    {
        appendMessage(thot.getName()+" has been slain");
        player.gainExperience(xp,this);
        inCombat = false;
        thot = null;
        appendMessage("------------------------------------------------------------------------------------------------------");
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

        if(i<(99*chanceOfItem))
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
        appendMessage("GameOver! You died at level " );
        appendMessage("Starting New Game!+\n");
        player.reset(this);
        inCombat = false;
        hasText = false;
        thot = null;
        dungeonHandler.createFloor(this);
    }

    void runAction(String input)
    {
        commandsEntered.add(input);
        commandHandler.RunAction(input,this);
        player.update(this);
        display.displayStats(player.getStats(),player.getInventory());
        display.redrawMap();
    }

    public int getHistorySize()
    {
        return commandsEntered.size();
    }

    public String getCommandHistory(int i)
    {
        if(i<=commandsEntered.size()) {
            if(i==0)
            {
                return null;
            }else{
                return commandsEntered.get((commandsEntered.size()) - i);
            }
        }else{
            return null;
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
        display.clearDisplay();
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
    
    public LinkedList<Room> getRooms()
    {
        return dungeonHandler.getRooms();
    }
    
    public Room getCurrentRoom()
    {
        return dungeonHandler.getCurrentRoom();
    }

}
