package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandHelp implements Command {

    private String command = "Help";

    @Override
    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            LinkedList<Command> commands = tsg.getCommands();
            StringBuilder s = new StringBuilder();
            s.append("Commands: ");
            for(int i=0;i<commands.size();i++)
            {
                s.append(commands.get(i).getCommand());

                if(commands.size()-(i)>1)
                {
                    s.append(", ");
                }
            }
            tsg.appendMessage(s.toString());
            return true;
        }else return false;
    }

    @Override
    public String getCommand()
    {
        return command;
    }

    @Override
    public boolean isAttackMove() {
        return false;
    }
}
