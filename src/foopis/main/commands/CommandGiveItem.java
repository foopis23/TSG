package foopis.main.commands;

import foopis.main.TSG;
import foopis.main.items.Item;

import java.util.LinkedList;

public class CommandGiveItem extends Command
{

    public CommandGiveItem()
    {
        command ="GiveItem";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {

        if(isThisCommand(command))
        {
            if(args.size()>0) {
                String itemName = args.get(0);
                Item item = tsg.getItemByName(itemName);
                if(item!=null)
                {
                    tsg.player.obtainItem(item,tsg);
                }else{
                    tsg.appendMessage("[Debug]: Could not find Item!");
                }
            }else{
                tsg.appendMessage("To use this command enter \""+command+" [Item Name]\"");
            }
            return true;
        }else{
            return false;
        }
    }
}
