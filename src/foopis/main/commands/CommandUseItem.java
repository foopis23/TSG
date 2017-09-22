package foopis.main.commands;


import foopis.main.TSG;

import java.util.LinkedList;

public class CommandUseItem extends Command
{
    public CommandUseItem()
    {
        command = "Use";
        isAttackMove = true;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if(isThisCommand(command))
        {
            if(args.size()>0) {
                String itemString = args.get(0);
                int itemNumber = Integer.parseInt(itemString);
                if(itemNumber<=tsg.player.getInventorySize()) {
                    tsg.player.useItem(itemNumber, tsg);
                }else{
                    tsg.appendMessage("You can only use an item between 1 and "+tsg.player.getInventorySize());
                }
            }else{
                tsg.appendMessage("Please enter \""+command+" [Inventory Slot Number]\" to use this command");
            }
            return true;
        }else return false;
    }
}
