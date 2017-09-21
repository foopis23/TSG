package foopis.main.commands;


import foopis.main.TSG;

import java.util.LinkedList;

public class CommandUseItem extends Command
{
    public CommandUseItem()
    {
        command = "UseItem";
        isAttackMove = true;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if(isThisCommand(command))
        {
            String itemNumber = args.get(0);

            if(itemNumber.equals("1"))
            {
                tsg.player.useItem(1,tsg);
            }else if(itemNumber.equals("2")){
                tsg.player.useItem(2,tsg);
            }else if(itemNumber.equals("3"))
            {
                tsg.player.useItem(3,tsg);
            }else{
                tsg.appendMessage("To use an item, enter 'UseItem {#1-3}'");
            }
            return true;
        }else return false;
    }
}
