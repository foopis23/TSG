package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandDialogue extends Command
{
    public CommandDialogue()
    {
        command = "Dialogue";
        isAttackMove = true;
    }

    @Override
    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            tsg.player.dialogue(tsg);
            return true;
        }
        return false;
    }
}
