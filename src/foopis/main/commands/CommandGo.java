package foopis.main.commands;

import foopis.main.TSG;

public class CommandGo extends Command
{
    public CommandGo()
    {
        command = "Go";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            if(!tsg.inCombat) {
                if (input.toLowerCase().trim().contains("north") || input.toLowerCase().trim().contains("no")) {
                    tsg.dungeonHandler.go(tsg, TSG.NORTH);
                } else if (input.toLowerCase().trim().contains("east") || input.toLowerCase().trim().contains("ea")) {
                    tsg.dungeonHandler.go(tsg, TSG.EAST);
                } else if (input.toLowerCase().trim().contains("south") || input.toLowerCase().trim().contains("so")) {
                    tsg.dungeonHandler.go(tsg, TSG.SOUTH);
                } else if (input.toLowerCase().trim().contains("west") || input.toLowerCase().trim().contains("we")) {
                    tsg.dungeonHandler.go(tsg, TSG.WEST);
                } else {
                    tsg.appendMessage("You entered a invalid direction ('go [North, East, West, South]");
                }
                return true;
            }else
            {
                tsg.appendMessage("You cannot use this command in combat!");
                return true;
            }
        }else{
            return false;
        }
    }
}
