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

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if (isThisCommand(command)) {
            if (args.size() < 1) {
                tsg.appendMessage("Please enter \"go [direction]\" or \"[n, s, e , or w]\"");
                return true;
            }

            String direction = args.get(0);

            if (!tsg.inCombat) {
                if (direction.toLowerCase().equals("north")) {
                    tsg.dungeonHandler.go(tsg, TSG.NORTH);
                } else if (direction.toLowerCase().equals("east")) {
                    tsg.dungeonHandler.go(tsg, TSG.EAST);
                } else if (direction.toLowerCase().equals("south")) {
                    tsg.dungeonHandler.go(tsg, TSG.SOUTH);
                } else if (direction.toLowerCase().equals("west")) {
                    tsg.dungeonHandler.go(tsg, TSG.WEST);
                } else {
                    tsg.appendMessage("You entered a invalid direction ('go [North, East, West, South]");
                }
                return true;
            } else {
                tsg.appendMessage("You can't use this command in combat!");
                return true;
            }
        } else if (command.toLowerCase().equals("n"))
        {
            if (!tsg.inCombat) {
                tsg.dungeonHandler.go(tsg, TSG.NORTH);
            }else{
                tsg.appendMessage("You can't use this command in combat!");
            }
            return true;

        } else if (command.toLowerCase().equals("e")) {
            if (!tsg.inCombat) {
                tsg.dungeonHandler.go(tsg, TSG.EAST);
            }else{
                tsg.appendMessage("You can't use this command in combat!");
            }
            return true;

        } else if (command.toLowerCase().equals("s")) {
            if (!tsg.inCombat) {
                tsg.dungeonHandler.go(tsg, TSG.SOUTH);
            }else{
                tsg.appendMessage("You can't use this command in combat!");
            }
            return true;
        } else if (command.toLowerCase().equals("w")) {
            if (!tsg.inCombat) {
                tsg.dungeonHandler.go(tsg, TSG.WEST);
            }else{
                tsg.appendMessage("You can't use this command in combat!");
            }
            return true;
        } else {
            return false;
        }

    }
}
