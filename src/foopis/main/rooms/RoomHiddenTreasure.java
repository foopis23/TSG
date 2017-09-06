package foopis.main.rooms;

import foopis.main.TSG;

public class RoomHiddenTreasure extends Room
{
    private boolean treasureLooted;
    
    public RoomHiddenTreasure ()
    {
        name="Empty Room";
        north=null;
        south=null;
        east=null;
        west=null;
        hasEntered=false;
        treasureLooted = false;
        thotChance=.20;
    }

    public void action(TSG tsg)
    {
        if(!treasureLooted) {
            tsg.appendMessage("You found a hidden treasure!");
            tsg.getRandomIOW();
            treasureLooted=true;
        }else{
            tsg.appendMessage("You already found this treasure");
        }
    }
}
