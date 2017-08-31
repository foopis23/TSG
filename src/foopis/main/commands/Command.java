package foopis.main.commands;

import foopis.main.TSG;

public interface Command
{
    boolean run(String input, TSG tsg);
    String getCommand();
    boolean isAttackMove();
}
