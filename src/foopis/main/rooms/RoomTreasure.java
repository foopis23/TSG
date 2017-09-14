package foopis.main.rooms;

import foopis.main.TSG;

public class RoomTreasure extends Room
{
    private boolean treasureOpenned;

    public RoomTreasure ()
    {
        name="Treasure Room";
        hasEntered=false;
        thotChance=.20;
        treasureOpenned=false;
    }

    public void action(TSG tsg)
    {
        if(!treasureOpenned)
        {
            tsg.appendMessage("You opened the Treasure Chest!");
            tsg.getRandomIOW();
            treasureOpenned = true;
        }else{
            tsg.appendMessage("You have already opened This Treasure Chest");
        }
    }
}
