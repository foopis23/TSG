package foopis.main.rooms;

import foopis.main.TSG;

public class RoomEmpty extends Room
{
    public RoomEmpty ()
    {
        name="Empty Room";
        north=null;
        south=null;
        east=null;
        west=null;
        hasEntered=false;
        thotChance=.20;
    }

    public void action(TSG tsg)
    {
        tsg.appendMessage("The room is empty");
    }
}
