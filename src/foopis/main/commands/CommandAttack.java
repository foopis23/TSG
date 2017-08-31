package foopis.main.commands;

import foopis.main.TSG;

public class CommandAttack implements Command
{
    private String command = "Attack";

    @Override
    public boolean run(String input, TSG tsg)
    {
        if(input.toLowerCase().contains(command.toLowerCase().trim()))
        {
            if(tsg.inCombat) {
                if(tsg.weapon!=null) {
                    tsg.weapon.use(tsg);
                }else{
                    tsg.attack(null);
                }
            }else{
                tsg.appendMessage("You can't attack outside of combat");
            }
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
        return true;
    }
}
