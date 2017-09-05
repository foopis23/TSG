package foopis.main.rooms;

import foopis.main.TSG;

public class RoomLadder extends Room
{
    public RoomLadder ()
    {
        name="Ladder Room";
        north=null;
        south=null;
        east=null;
        west=null;
        hasEntered=false;
        thotChance=.20;
    }

    public void action(TSG tsg)
    {
        tsg.appendMessage("You went down the ladder to the next floor");
        tsg.dungeonHandler.createFloor(tsg);
    }
}
