package foopis.main.commands;

import foopis.main.TSG;

public class CommandLook implements Command
{
    String command = "Look";
    @Override
    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            if(!tsg.inCombat)
            {
                tsg.dungeonHandler.look(tsg);
            }else{
                tsg.appendMessage("You can't use this command while in combat");
            }
            return  true;
        }else{
            return false;
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public boolean isAttackMove() {
        return false;
    }
}
