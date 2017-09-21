package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandHelp extends Command {

    public CommandHelp()
    {
        command = "Help";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
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
}
