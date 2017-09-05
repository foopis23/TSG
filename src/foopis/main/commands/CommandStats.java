package foopis.main.commands;

import foopis.main.TSG;

public class CommandStats extends Command {

    public CommandStats()
    {
        command = "Stats";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if (input.toLowerCase().contains(command.trim().toLowerCase())) {
            tsg.appendMessage("----------Stats----------");
            tsg.appendMessage("Level: " + tsg.level);
            tsg.appendMessage("Xp: " + tsg.xp + "/" + tsg.xpToLevel);
            tsg.appendMessage("Health: " + tsg.health + "/" + tsg.healthLimit);
            tsg.appendMessage("Damage Boost: " + tsg.damageBoost);
            tsg.appendMessage("Defense Boost: " + tsg.defenseBoost);
            tsg.appendMessage("-------------------------");
            return true;
        }
        return false;
    }
}
