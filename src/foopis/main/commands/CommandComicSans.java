package foopis.main.commands;

import foopis.main.TSG;

import java.awt.*;

public class CommandComicSans extends Command
{

    public CommandComicSans()
    {
        command = "ComicSans";
        isAttackMove = false;
    }

    public boolean run(String input, TSG tsg) {
        if(input.toLowerCase().contains(command.trim().toLowerCase()))
        {
            tsg.changeFont("Comic Sans MS", Font.TRUETYPE_FONT,12);
            return true;
        }else return false;
    }
}
