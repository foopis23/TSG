package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandLevelUp extends Command
{
    public CommandLevelUp()
    {
        command = "LevelUp";
        isAttackMove = false;
    }

    @Override
    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            if(args.size()<1)
            {
                tsg.appendMessage("You have "+tsg.player.getStatPoints()+" stat points to spend!");
                tsg.appendMessage("To use stat points type "+command+" [S, I, H, or L]");
            }
            return true;
        }
        return false;
    }
}
