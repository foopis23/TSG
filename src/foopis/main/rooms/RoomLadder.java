package foopis.main.rooms;

import foopis.main.TSG;

public class RoomLadder implements Room
{
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private int x;
    private int y;
    private boolean hasEntered;
    private double thotChance;


    public RoomLadder ()
    {
        north=null;
        south=null;
        east=null;
        west=null;
        hasEntered=false;
        thotChance=.20;
    }

    @Override
    public void action(TSG tsg)
    {
        tsg.appendMessage("You went down the ladder to the next floor");
        tsg.dungeonHandler.createFloor(tsg);
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

    @Override
    public double getEncountChance() {
        return thotChance;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public void setNorth(Room r) {
        north=r;
    }

    @Override
    public void setSouth(Room r) {
        south=r;
    }

    @Override
    public void setEast(Room r) {
        east=r;
    }

    @Override
    public void setWest(Room r)
    {
        west = r;
    }

    @Override
    public Room getNorth() {
        return north;
    }

    @Override
    public Room getWest() {
        return west;
    }

    @Override
    public Room getEast() {
        return east;
    }

    @Override
    public Room getSouth() {
        return south;
    }

    @Override
    public String getName() {
        return "Ladder Room";
    }

}
