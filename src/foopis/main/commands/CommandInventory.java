package foopis.main.commands;

import foopis.main.TSG;

public class CommandInventory extends Command
{

    public CommandInventory()
    {
        command = "Inventory";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            String s=tsg.getInventory();
            tsg.appendMessage("---------------Inventory---------------");
            tsg.appendMessage(s);
            tsg.appendMessage("------------------------------------------");
            return true;
        }else return false;
    }
}
