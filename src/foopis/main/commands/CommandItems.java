package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandItems extends Command
{
    public CommandItems()
    {
        command = "Inventory";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            String s=tsg.player.getInventory();
            tsg.appendMessage(s);
            return true;
        }else return false;
    }
}
