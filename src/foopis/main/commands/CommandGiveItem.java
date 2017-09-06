package foopis.main.commands;

import foopis.main.TSG;

public class CommandGiveItem extends Command
{

    public CommandGiveItem()
    {
        command ="GiveItem";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {

        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
                String s = input.substring(8,input.length());
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
