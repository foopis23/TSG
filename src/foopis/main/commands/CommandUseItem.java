package foopis.main.commands;


import foopis.main.TSG;

public class CommandUseItem extends Command
{
    public CommandUseItem()
    {
        command = "UseItem";
        isAttackMove = true;
    }

    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            if(input.contains("1"))
            {
                tsg.player.useItem(1,tsg);
            }else if(input.contains("2")){
                tsg.player.useItem(2,tsg);
            }else if(input.contains("3"))
            {
                tsg.player.useItem(3,tsg);
            }else{
                tsg.appendMessage("To use an item, enter 'UseItem {#1-3}'");
            }
            return true;
        }else return false;
    }
}
