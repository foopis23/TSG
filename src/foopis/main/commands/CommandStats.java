package foopis.main.commands;

import foopis.main.TSG;

public class CommandStats extends Command {

    public CommandStats()
    {
        command = "Stats";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if (input.toLowerCase().contains(command.trim().toLowerCase())) {
            System.out.println(tsg.player.getStats());
            return true;
        }
        return false;
    }
}
