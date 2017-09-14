package foopis.main.commands;

import foopis.main.TSG;

public class CommandLook extends Command
{

    public CommandLook()
    {
        command = "Look";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
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
