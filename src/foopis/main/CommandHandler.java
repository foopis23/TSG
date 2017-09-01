package foopis.main;

import foopis.main.commands.Command;

import java.util.LinkedList;

public class CommandHandler extends LinkedList<Command>
{
    private boolean ran = false;
    private boolean combatMove = false;

    public void RunAction(String input, TSG tsg)
    {
        ran = false;
        combatMove = false;

        if(tsg.obtainedItem!=null)
        {
            itemScrapping(input,tsg);
        }else if(tsg.obtainedWeapon!=null)
        {
            weaponScrapping(input,tsg);
        }else{
            RunCommands(input,tsg);
        }

        if(combatMove)
        {
            if(tsg.inCombat)
            {
                if(tsg.thot!=null)
                {
                    tsg.thot.action(tsg);
                }else{
                    tsg.appendMessage("Combat Machin brok");
                }
            }
        }

        if (!ran) {
            tsg.appendMessage("Unknown Command");
        }

        if(tsg.health<=0)
        {
            tsg.appendMessage("GameOver! You died at level "+tsg.level);
            tsg.appendMessage("Starting New Game!");
            tsg.initGame();
        }

    }

    public boolean RunCommands(String input, TSG tsg)
    {
        for (Command c : this) {
            if (c.run(input, tsg)) {
                combatMove=c.isAttackMove();
                return true;
            }
        }
        return false;
    }

    public boolean weaponScrapping(String input, TSG tsg)
    {
        if(input.toLowerCase().contains("yes"))
        {
            tsg.appendMessage("You replaced "+tsg.weapon.getName()+" with "+ tsg.obtainedWeapon.getName());
            tsg.weapon = tsg.obtainedWeapon;
            tsg.obtainedWeapon = null;
            return true;
        }else if(input.toLowerCase().contains("no"))
        {
            tsg.appendMessage("You scraped "+ tsg.obtainedWeapon.getName());
            tsg.obtainedWeapon=null;
            return true;
        }else{
            tsg.appendMessage("That is not a valid command right now!");
            tsg.appendMessage("You already have a weapon, if you would like to replace it enter yes, if not no");
            return true;
        }
    }


    public boolean itemScrapping(String input, TSG tsg)
    {
        if (input.toLowerCase().contains("no")) {
            tsg.obtainedItem = null;
            tsg.appendMessage("You scraped the Item");
            return true;
        } else if (input.toLowerCase().contains("1")) {
            tsg.appendMessage(tsg.item1.getName() + " has been scraped for " + tsg.obtainedItem.getName());
            tsg.item1 = tsg.obtainedItem;
            tsg.obtainedItem = null;
            return true;
        } else if (input.toLowerCase().contains("2")) {
            tsg.appendMessage(tsg.item2.getName() + " has been scraped for " + tsg.obtainedItem.getName());
            tsg.item2 = tsg.obtainedItem;
            tsg.obtainedItem = null;
            return true;
        } else if (input.toLowerCase().contains("3")) {
            tsg.appendMessage(tsg.item3.getName() + " has been scraped for " + tsg.obtainedItem.getName());
            tsg.item3 = tsg.obtainedItem;
            tsg.obtainedItem = null;
            return true;
        } else {
            tsg.appendMessage("That is not a valid command right now!");
            tsg.appendMessage("Your Inventory is full, if you want to replace an Item type the # now, if not type no");
            return true;
        }
    }
}