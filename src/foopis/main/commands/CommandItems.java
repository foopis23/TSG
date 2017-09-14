package foopis.main.commands;

import foopis.main.TSG;

public class CommandItems extends Command
{
    public CommandItems()
    {
        command = "Inventory";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            String s=tsg.getInventory();
            tsg.appendMessage(s);
            return true;
        }else return false;
    }
}
