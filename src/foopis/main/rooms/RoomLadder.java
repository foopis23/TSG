package foopis.main.rooms;

import foopis.main.TSG;

public class RoomLadder extends Room
{
    public RoomLadder ()
    {
        name="Ladder Room";
        hasEntered=false;
        thotChance=.20;
    }

    public void action(TSG tsg)
    {
        tsg.appendMessage("You went down the ladder to the next floor");
        tsg.player.nextFloor();
        tsg.dungeonHandler.createFloor(tsg);
    }
}
