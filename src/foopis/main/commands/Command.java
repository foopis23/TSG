package foopis.main.commands;

import foopis.main.TSG;

public class Command
{
    protected String command;
    protected boolean isAttackMove;

    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            tsg.appendMessage("This is default command thing");
            return true;
        }else{
            return false;
        }
    }

    public String getCommand()
    {
        return command;
    }

    public boolean isAttackMove()
    {
        return isAttackMove;
    }
}
