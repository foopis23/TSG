package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandEncounter extends Command
{
    public CommandEncounter()
    {
        command = "Encounter";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if (isThisCommand(command))
        {
            if(!tsg.inCombat) {
                tsg.appendMessage("[Debug]: Now starting a new encounter");
                tsg.encounter(1);
            }else{
                tsg.appendMessage("[Debug: You cannot start a encounter while in combat!");
            }
            return true;
        }else{
            return false;
        }

    }
}
