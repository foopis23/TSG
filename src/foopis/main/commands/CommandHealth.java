package foopis.main.commands;

import foopis.main.TSG;

public class CommandHealth implements Command
{
    private String command = "Health";

    @Override
    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            tsg.appendMessage("Health: "+tsg.health+"/"+tsg.healthLimit);
            return true;
        }else return false;
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
