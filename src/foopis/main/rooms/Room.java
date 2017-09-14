package foopis.main.rooms;

import foopis.main.TSG;

public class Room
{
    protected String name;
    protected boolean[] exits = {false, false, false, false};
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

    public void setExit(int direction, boolean exit) {
        exits[direction] = exit;
    }
    public boolean isExit(int direction) {
        return exits[direction];
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumExits()
    {
        int num = 0;
        for(boolean isExit: exits)
        {
            if(isExit)
            {
                num++;
            }
        }
        
        return num;
    }
}
