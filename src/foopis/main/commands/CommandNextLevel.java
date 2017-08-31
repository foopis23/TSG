package foopis.main.commands;

import foopis.main.TSG;

public class CommandNextLevel implements Command
{
    String command = "xp";
    @Override
    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            tsg.appendMessage("Next Level: "+tsg.xp+"/"+tsg.xpToLevel+" until level "+ (tsg.level+1));
            return true;
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
