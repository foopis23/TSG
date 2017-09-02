package foopis.main.commands;

import foopis.main.TSG;

public class CommandInventory implements Command
{
   private  String command = "Inventory";

    @Override
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

    @Override
    public String getCommand()
    {
        return command;
    }

    @Override
    public boolean isAttackMove() {
        return false;
    }
}
