package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandGo extends Command
{
    public CommandGo()
    {
        command = "Go";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            String direction = args.get(0);
            if(!tsg.inCombat) {
                if (direction.toLowerCase().equals("north")){
                    tsg.dungeonHandler.go(tsg, TSG.NORTH);
                } else if (direction.toLowerCase().equals("east")) {
                    tsg.dungeonHandler.go(tsg, TSG.EAST);
                } else if (direction.toLowerCase().equals("south")) {
                    tsg.dungeonHandler.go(tsg, TSG.SOUTH);
                } else if (direction.toLowerCase().equals("west")){
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
