package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandRoomAction extends Command {

    public CommandRoomAction()
    {
        command="RoomAction";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if (isThisCommand(command))
        {
            if(!tsg.inCombat) {
                tsg.dungeonHandler.getCurrentRoom().action(tsg);
            }else{
                tsg.appendMessage("You can't use that command in combat!");
            }
            return true;
        } else {
            return false;
        }
    }
}
