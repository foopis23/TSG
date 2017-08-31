package foopis.main.commands;

import foopis.main.TSG;

public class CommandClear implements Command {

    private String command = "Clear";

    @Override
    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            tsg.clearDisplay();
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
