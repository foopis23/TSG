package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandGiveItem extends Command
{

    public CommandGiveItem()
    {
        command ="GiveItem";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {

        if(isThisCommand(command))
        {
                String s = args.get(0);
                System.out.println(s);
                int i = Integer.parseInt(s);
                if(i<tsg.getItems().size()) {
                    tsg.player.obtainItem(tsg.getItems().get(i), tsg);
                    return true;
                }else {
                    tsg.appendMessage("Could not find Item ID");
                    return true;
                }
        }else{
            return false;
        }
    }
}
