package foopis.main.commands;

import foopis.main.TSG;

public class CommandGiveItem implements Command
{
    private String  command ="GiveItem";



    @Override
    public boolean run(String input, TSG tsg) {

        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
                String s = input.substring(8,input.length());
                System.out.println(s);
                int i = Integer.parseInt(s);
                if(i<tsg.getItems().size()) {
                    tsg.obtainItem(tsg.getItems().get(i));
                    return true;
                }else {
                    tsg.appendMessage("Could not find Item ID");
                    return true;
                }
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
