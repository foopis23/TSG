package foopis.main.commands;

import foopis.main.TSG;

public class CommandGo implements Command
{
    String command = "Go";

    @Override
    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.trim().toLowerCase())&&!tsg.inCombat)
        {
            if(input.toLowerCase().trim().contains("north"))
            {
                tsg.getCurrentRoom().go(tsg,TSG.NORTH);
            }else if(input.toLowerCase().trim().contains("east"))
            {
                tsg.getCurrentRoom().go(tsg,TSG.EAST);
            }else if(input.toLowerCase().trim().contains("south"))
            {
                tsg.getCurrentRoom().go(tsg,TSG.SOUTH);
            }else if(input.toLowerCase().trim().contains("west"))
            {
                tsg.getCurrentRoom().go(tsg,TSG.WEST);
            }else{
                tsg.appendMessage("You entered a invalid direction ('go [North, East, West, South]");
            }

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
