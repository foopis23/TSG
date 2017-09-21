package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandClear extends Command {

    public CommandClear()
    {
        command = "Clear";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            tsg.clearDisplay();
            return true;
        }else return false;
    }
}
