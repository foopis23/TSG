package foopis.main;

import foopis.main.commands.Command;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class CommandHandler extends LinkedList<Command>
{
    private boolean ran = false;
    private boolean combatMove = false;

    public void RunAction(String input, TSG tsg)
    {
        ran = false;
        combatMove = false;

        System.out.println("Raw Input: "+input);
        String command = getCommand(input);
        LinkedList<String> args = getArguments(input);

        if(tsg.player.getObtainedItem()!=null)
        {
            ran = itemScrapping(command,tsg);
        }else if(tsg.player.getObtainedWeapon()!=null)
        {
            ran = weaponScrapping(command,tsg);
        }else{
            ran = RunCommands(command,args,tsg);
        }

        if(combatMove)
        {
            if(tsg.inCombat)
            {
                if(tsg.thot!=null)
                {
                    tsg.thot.action(tsg);
                }else{
                    tsg.appendMessage("Combat Machin brok");
                }
            }
        }

        if (!ran) {
            tsg.appendMessage("Unknown Command");
        }
    }

    public boolean RunCommands(String command, LinkedList<String> args, TSG tsg)
    {
        for (Command c : this) {
            if (c.run(command,args, tsg))
            {
                combatMove=c.isAttackMove();
                return true;
            }
        }

        if(command.toLowerCase().equals("=oofdebugthots!"))
        {
            tsg.enterDebug();
            tsg.appendMessage("[Debug]: Entering Debug Mode");
            return true;
        }

        return false;
    }

    public boolean weaponScrapping(String input, TSG tsg)
    {
        if(input.toLowerCase().contains("yes"))
        {
            tsg.appendMessage("You replaced "+tsg.player.getWeapon().getName()+" with "+ tsg.player.getObtainedWeapon().getName());
            tsg.player.replaceWeapon();
            return true;
        }else if(input.toLowerCase().contains("no"))
        {
            tsg.appendMessage("You scrapped "+ tsg.player.getObtainedWeapon().getName());
            tsg.player.scrapObtainedWeapon();
            return true;
        }else{
            tsg.appendMessage("That is not a valid command right now!");
            return true;
        }
    }


    public boolean itemScrapping(String command, TSG tsg)
    {
        if (command.toLowerCase().equals("no"))
        {
            tsg.appendMessage("You scrapped the Item");
            tsg.player.scarpObtainedItem();
            return true;
        }else{
            try{
                int i = Integer.parseInt(command);
                tsg.player.replaceItem(i);
            }catch (Exception e)
            {
                tsg.appendMessage("That is not a valid command right now!");
                System.err.println(e);
            }
            return true;
        }
    }

    public String getCommand(String input)
    {
        if (input.contains(" ")) {input = input.substring(0,input.indexOf(" "));}
        System.out.println("Command: "+input);

        return input;
    }

    public LinkedList<String> getArguments(String input)
    {
        LinkedList<String> args = new LinkedList<>();
        int[] spaceIndexs = {-1,-1,-1};

        int counter = 0;
        for( int i=0; i<input.length(); i++ )
        {
            if( input.charAt(i) == ' ' )
            {
                spaceIndexs[counter] = i;
                counter++;

                if(counter <= spaceIndexs.length) {break;}
            }
        }

        int startPoint = 0;
        int endPoint = 0;
        for(int i=0; i<spaceIndexs.length;i++)
        {
            if(spaceIndexs[i]!=-1) {startPoint = spaceIndexs[i]+1;}else{break;}

            if(i+1<spaceIndexs.length&&spaceIndexs[i+1]!=-1){endPoint = spaceIndexs[i+1];}else{endPoint = input.length();}

            args.add(input.substring(startPoint,endPoint));
        }

        System.out.println("Args: ");

        for(String a: args)
        {
            System.out.println(a+"\n");
        }

        return args;
    }
}