package foopis.main.commands;

import foopis.main.TSG;

import java.util.LinkedList;

public class CommandAttack extends Command
{
    public CommandAttack()
    {
        command = "Attack";
        isAttackMove = true;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg)
    {
        if(isThisCommand(command))
        {
            if(tsg.inCombat) {
                    tsg.player.attack(tsg);
            }else{
                tsg.appendMessage("You can't attack outside of combat");
            }
            return true;
        }else{
            return false;
        }
    }
}
