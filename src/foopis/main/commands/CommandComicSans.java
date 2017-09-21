package foopis.main.commands;

import foopis.main.TSG;

import java.awt.*;
import java.util.LinkedList;

public class CommandComicSans extends Command
{

    public CommandComicSans()
    {
        command = "ComicSans";
        isAttackMove = false;
    }

    public boolean run(String command, LinkedList<String> args, TSG tsg) {
        if(isThisCommand(command))
        {
            tsg.changeFont("Comic Sans MS", Font.TRUETYPE_FONT,12);
            return true;
        }else return false;
    }
}
