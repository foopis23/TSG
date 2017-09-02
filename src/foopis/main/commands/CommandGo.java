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
            if(input.toLowerCase().trim().contains("north")||input.toLowerCase().trim().contains("no"))
            {
                tsg.dungeonHandler.go(tsg,TSG.NORTH);
            }else if(input.toLowerCase().trim().contains("east")||input.toLowerCase().trim().contains("ea"))
            {
                tsg.dungeonHandler.go(tsg,TSG.EAST);
            }else if(input.toLowerCase().trim().contains("south")||input.toLowerCase().trim().contains("so"))
            {
                tsg.dungeonHandler.go(tsg,TSG.SOUTH);
            }else if(input.toLowerCase().trim().contains("west")||input.toLowerCase().trim().contains("we"))
            {
                tsg.dungeonHandler.go(tsg,TSG.WEST);
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
