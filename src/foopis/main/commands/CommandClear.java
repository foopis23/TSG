package foopis.main.commands;

import foopis.main.TSG;

public class CommandClear extends Command {

    public CommandClear()
    {
        command = "Clear";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            tsg.clearDisplay();
            return true;
        }else return false;
    }
}
