package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandToggleEncounter extends Command
{
    public CommandToggleEncounter()
    {
        command = "ToggleEncounter";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if (isThisCommand(command))
        {
            tsg.canEncounter = !tsg.canEncounter;

            tsg.appendMessage("[Debug]: Encounters have been set to: "+tsg.canEncounter);
            return true;
        }else{
            return false;
        }
    }
}
