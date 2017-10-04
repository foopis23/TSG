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
                tsg.appendMessage("You have "+tsg.player.getStatPoints()+" skill points to spend");
                tsg.appendMessage("To use skill points type "+command+" [S, I, H, or L]");
                return true;
            }

            if(args.get(0).toLowerCase().equals("s")||args.get(0).toLowerCase().equals("strength"))
            {
                tsg.player.levelUpStrength(tsg);
            }else if(args.get(0).toLowerCase().equals("i")||args.get(0).toLowerCase().equals("intelligence"))
            {
                tsg.player.levelUpIntelligence(tsg);
            }else if(args.get(0).toLowerCase().equals("h")||args.get(0).toLowerCase().equals("health"))
            {
                tsg.player.levelUpHealth(tsg);
            }else if(args.get(0).toLowerCase().equals("l")||args.get(0).toLowerCase().equals("luck"))
            {
                tsg.player.levelUpLuck(tsg);
            }else{
                tsg.appendMessage("You cannot level up \""+args.get(0)+"\"! What are you stupid?");
            }
            return true;
        }
        return false;
    }
}
