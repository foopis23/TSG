package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class Command
{
    protected String command;
    protected boolean isAttackMove;

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(command.toLowerCase().equals(this.command.toLowerCase()))
        {
            tsg.appendMessage("This is default command thing");
            return true;
        }else{
            return false;
        }
    }

    public boolean isThisCommand(String command)
    {
        return command.toLowerCase().equals(this.command.toLowerCase());
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
