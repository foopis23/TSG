package foopis.main.rooms;

import foopis.main.TSG;

public class RoomTrappedTreasure extends Room
{
    private boolean tricked;
    private boolean treasureOpenned;

    public RoomTrappedTreasure ()
    {
        name="Treasure Room.";
        north=null;
        south=null;
        east=null;
        west=null;
        hasEntered=false;
        thotChance=.20;
        treasureOpenned=false;
        tricked=false;
    }

    public void action(TSG tsg)
    {
        if(!tricked)
        {
            tsg.appendMessage("The Treasure was a Trap!");
            tsg.encounter(1);
            tricked=true;
        }else{
            if(!treasureOpenned)
            {
                tsg.appendMessage("You opened the Treasure Chest!");
                tsg.getRandomIOW();
                treasureOpenned=true;
            }else{
                tsg.appendMessage("You have already been tricked");
            }
        }
    }

    @Override
    public void roomEntered(TSG tsg)
    {
        if(!hasEntered)
        {
            hasEntered=true;
            tsg.encounter(thotChance);
        }else{
            tsg.encounter(thotChance/2);
        }
    }
}
