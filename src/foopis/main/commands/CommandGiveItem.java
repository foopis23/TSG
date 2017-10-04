package foopis.main.commands;

import foopis.main.TSG;
import foopis.main.items.Item;

import java.util.LinkedList;

public class CommandGiveItem extends Command
{

    public CommandGiveItem()
    {
        command ="Give";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {

        if(isThisCommand(command))
        {
            if(args.size()>1) {
                if(args.get(0).toLowerCase().equals("item")||args.get(0).toLowerCase().equals("i")) {
                    String itemName = args.get(0);
                    Item item = tsg.getItemByName(itemName);
                    if (item != null) {
                        tsg.player.obtainItem(item, tsg);
                    } else {
                        tsg.appendMessage("[Debug]: Could not find Item!");
                    }
                }
            }else{
                tsg.appendMessage("To use this command enter \""+command+" [Item or Weapon] [Name]\"");
            }
            return true;
        }else{
            return false;
        }
    }
}
