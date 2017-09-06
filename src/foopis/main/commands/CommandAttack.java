package foopis.main.commands;

import foopis.main.TSG;

public class CommandAttack extends Command
{
    public CommandAttack()
    {
        command = "Attack";
        isAttackMove = true;
    }

    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
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
