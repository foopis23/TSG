package foopis.main.commands;

import foopis.main.TSG;

public class CommandRoomAction implements Command {

    String command = "RoomAction";

    @Override
    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.toLowerCase().trim())&&!tsg.inCombat)
        {
            tsg.dungeonHandler.getCurrentRoom().action(tsg);
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
