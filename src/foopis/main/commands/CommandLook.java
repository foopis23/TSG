package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandLook extends Command
{

    public CommandLook()
    {
        command = "Look";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if(isThisCommand(command))
        {
            if(!tsg.inCombat)
            {
                tsg.dungeonHandler.displayRoomInfo(tsg, false);
            }else{
                tsg.appendMessage("You can't use this command while in combat");
            }
            return  true;
        }else{
            return false;
        }
    }
}
