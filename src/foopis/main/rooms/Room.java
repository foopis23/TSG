package foopis.main.rooms;

import foopis.main.TSG;

public class Room
{
    protected String name;
    protected Room[] exits = new Room[4];
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

    public void setRoom(int direction, Room room) {
        exits[direction] = room;
    }
    public Room getRoom(int direction) {
        return exits[direction];
    }
    
    public String getName() {
        return name;
    }
}
