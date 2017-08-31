package foopis.main.rooms;

import foopis.main.TSG;

public class RoomEmpty implements Room
{
    private boolean canNorth;
    private boolean canSouth;
    private boolean canEast;
    private boolean canWest;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private boolean hasEntered;
    private double thotChance;

    public RoomEmpty ()
    {
        canNorth = false;
        canSouth = false;
        canEast = false;
        canWest = false;
        north = null;
        south = null;
        east = null;
        west = null;
        hasEntered = false;
        thotChance = .15;
    }

    @Override
    public void action(TSG tsg)
    {
        tsg.appendMessage("The room is empty");
    }

    @Override
    public void roomEntered(TSG tsg, Room cameFrom, int direction) {
        String s = "You have entered a Empty Room. ";

        if (!hasEntered)
        {
            firstEnter(tsg, cameFrom, direction);
            tsg.appendMessage(s+getDoors(cameFrom));
            tsg.encounter(thotChance);
        }else{
            tsg.appendMessage(s+getDoors(cameFrom));
            tsg.encounter((thotChance/2));
        }
    }

    @Override
    public void firstEnter(TSG tsg, Room cameFrom, int direction)
    {
        if(cameFrom!=null)
        {
            if(direction==TSG.WEST)
            {
                east = cameFrom;
            }else if(direction==TSG.EAST)
            {
                west = cameFrom;
            }else if(direction==TSG.SOUTH)
            {
                north = cameFrom;
            }else if(direction==TSG.NORTH)
            {
                south = cameFrom;
            }
        }

        if(east==null)
        {
            int i = tsg.random.nextInt((tsg.roomLimit-1));
            if(i<tsg.roomLimit-tsg.potentialRooms)
            {
                canEast=true;
                tsg.potentialRooms++;
            }
        }else{
            canEast = true;
        }

        if(west==null)
        {
            int i = tsg.random.nextInt((tsg.roomLimit-1));
            if(i<tsg.roomLimit-tsg.potentialRooms)
            {
                canWest=true;
                tsg.potentialRooms++;
            }
        }else{
            canWest = true;
        }

        if(north==null)
        {
            int i = tsg.random.nextInt((tsg.roomLimit-1));
            if(i<tsg.roomLimit-tsg.potentialRooms)
            {
                canNorth=true;
                tsg.potentialRooms++;
            }
        }else{
            canNorth=true;
        }

        if(south==null)
        {
            int i = tsg.random.nextInt((tsg.roomLimit-1));
            if(i<tsg.roomLimit-tsg.potentialRooms)
            {
                canSouth=true;
                tsg.potentialRooms++;
            }
        }else{
            canSouth=true;
        }
        hasEntered = true;
    }

    public String getDoors(Room cameFrom)
    {
        String s = "There are open doors to the ";

        if(canWest)
        {
            s+= "West";
            if(cameFrom==west&&west!=null)
            {
                s+="(Entered From), ";
            }else{
                s+=", ";
            }
        }

        if(canEast)
        {
            s+= "East";
            if(cameFrom==east&&east!=null)
            {
                s+="(Entered From), ";
            }else{
                s+=", ";
            }
        }

        if(canNorth)
        {
            s+= "North";
            if(cameFrom==north&&north!=null)
            {
                s+="(Entered From), ";
            }else{
                s+=", ";
            }
        }

        if(canSouth)
        {
            s+= "South";
            if(cameFrom==south&&south!=null)
            {
                s+="(Entered From), ";
            }else{
                s+=", ";
            }
        }

        return s;
    }

    @Override
    public double getEncountChance() {
        return thotChance;
    }

    @Override
    public void go(TSG tsg, int direction)
    {
        if(direction==TSG.NORTH&&canNorth)
        {
            if(north==null) {north = tsg.getRandomRoom();}
            tsg.enterRoom(north,this, direction);
        }else if(direction==TSG.EAST&&canEast)
        {
            if(east==null) {east = tsg.getRandomRoom();}
            tsg.enterRoom(east,this, direction);
        }else if(direction==TSG.SOUTH&&canSouth)
        {
            if(south==null) {south = tsg.getRandomRoom();}
            tsg.enterRoom(south,this, direction);
        }else if(direction==TSG.WEST&&canWest)
        {
            if(west==null) {west = tsg.getRandomRoom();}
            tsg.enterRoom(west,this, direction);
        }else{
            tsg.appendMessage("You can not go that way! (OOF)");
        }
    }
}
