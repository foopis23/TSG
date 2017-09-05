package foopis.main.commands;

import foopis.main.TSG;

public class CommandRoomAction extends Command {

    public CommandRoomAction()
    {
        command="RoomAction";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if (input.toLowerCase().contains(command.toLowerCase().trim()) && !tsg.inCombat) {
            tsg.dungeonHandler.getCurrentRoom().action(tsg);
            return true;
        } else {
            return false;
        }
    }
}
