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

        if(tsg.player.getObtainedItem()!=null)
        {
            ran = itemScrapping(input,tsg);
        }else if(tsg.player.getObtainedWeapon()!=null)
        {
            ran = weaponScrapping(input,tsg);
        }else{
            ran = RunCommands(input,tsg);
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
            tsg.appendMessage("You replaced "+tsg.player.getWeapon().getName()+" with "+ tsg.player.getObtainedWeapon().getName());
            tsg.player.replaceWeapon();
            return true;
        }else if(input.toLowerCase().contains("no"))
        {
            tsg.appendMessage("You scrapped "+ tsg.player.getObtainedWeapon().getName());
            tsg.player.scrapObtainedWeapon();
            return true;
        }else{
            tsg.appendMessage("That is not a valid command right now!");
            tsg.appendMessage("You already have a weapon, if you would like to replace it enter yes, if not no");
            return true;
        }
    }


    public boolean itemScrapping(String input, TSG tsg)
    {
        if (input.toLowerCase().contains("no"))
        {
            tsg.appendMessage("You scrapped the Item");
            tsg.player.scarpObtainedItem();
            return true;
        } else if (input.toLowerCase().contains("1")) {
            tsg.appendMessage(tsg.player.getItems(1).getName() + " has been scrapped for " + tsg.player.getObtainedItem().getName());
            tsg.player.replaceItem(1);
            return true;
        } else if (input.toLowerCase().contains("2")) {
            tsg.appendMessage(tsg.player.getItems(2).getName() + " has been scrapped for " + tsg.player.getObtainedItem().getName());
            tsg.player.replaceItem(2);
            return true;
        } else if (input.toLowerCase().contains("3")) {
            tsg.appendMessage(tsg.player.getItems(3).getName() + " has been scrapped for " + tsg.player.getObtainedItem().getName());
            tsg.player.replaceItem(3);
            return true;
        } else {
            tsg.appendMessage("That is not a valid command right now!");
            tsg.appendMessage("Your Inventory is full, if you want to replace an Item type the # now, if not type no");
            return true;
        }
    }
}