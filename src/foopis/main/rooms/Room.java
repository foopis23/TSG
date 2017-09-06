package foopis.main.rooms;

import foopis.main.TSG;

public class Room
{
    protected String name;
    protected Room north;
    protected Room south;
    protected Room east;
    protected Room west;
    protected int x;
    protected int y;
    protected boolean hasEntered;
    protected double thotChance;
    
    public void action(TSG tsg) {
        tsg.appendMessage("Undefined room");
    }

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

    public double getEncountChance() {
        return thotChance;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }

    public void setNorth(Room r) {
        north=r;
    }

    public void setSouth(Room r) {
        south=r;
    }

    public void setEast(Room r) {
        east=r;
    }

    public void setWest(Room r)
    {
        west = r;
    }

    public Room getNorth() {
        return north;
    }

    public Room getWest() {
        return west;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public String getName() {
        return name;
    }
}
