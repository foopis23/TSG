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
                if(tsg.item1!=null) {
                    if(tsg.item1.use(tsg)) {
                        tsg.item1 = null;
                    }
                }else
                {
                    tsg.appendMessage("Item 1 slot is empty");
                }
            }else if(input.contains("2"))
            {
                if(tsg.item2!=null) {
                    if(tsg.item2.use(tsg)) {
                        tsg.item2 = null;
                    }
                }else
                {
                    tsg.appendMessage("Item 2 slot is empty");
                }
            }else if(input.contains("3"))
            {
                if(tsg.item3!=null) {
                    if(tsg.item3.use(tsg)) {
                        tsg.item3 = null;
                    }
                }else
                {
                    tsg.appendMessage("Item 3 slot is empty");
                }
            }else{
                tsg.appendMessage("Please type useitem (# of item you want to use 1-3)");
            }

            return true;
        }else return false;
    }
}
