package foopis.main;

import foopis.main.rooms.*;

import java.util.LinkedList;
import java.util.Random;

public class DungeonHandler
{
    private int roomLimit = 50;
    private double specialRoomAmount = .25;
    private Random random;
    private LinkedList<Room> aRooms;
    private LinkedList<Room> tRooms;
    private Room currentRoom;

    public DungeonHandler()
    {
        random = new Random();
        currentRoom = null;
        aRooms = new LinkedList<>();
        tRooms = new LinkedList<>();
    }

    public void go(TSG tsg, int direction)
    {
        int x = currentRoom.getX();
        int y = currentRoom.getY();
        Room r = null;

        if(direction==TSG.NORTH)
        {
            r = findRoom(x,y+1);
            if(r!=null) {
                enterRoom(r,tsg);
            }else{
                tsg.appendMessage("You cannot go this direction");
            }
        }

        if(direction==TSG.SOUTH)
        {
            r = findRoom(x,y-1);
            if(r!=null) {
                enterRoom(r,tsg);
            }else{
                tsg.appendMessage("You cannot go this direction");
            }
        }

        if(direction==TSG.EAST)
        {
            r = findRoom(x+1,y);
            if(r!=null) {
                enterRoom(r,tsg);
            }else{
                tsg.appendMessage("You cannot go this direction");
            }
        }

        if(direction==TSG.WEST)
        {
            r = findRoom(x-1,y);
            if(r!=null) {
                enterRoom(r,tsg);
            }else{
                tsg.appendMessage("You cannot go this direction");
            }
        }


    }

    public void createFloor(TSG tsg)
    {
        aRooms.clear();
        tRooms.clear();
        aRooms.addAll(createAllRooms(tsg));
        Room r = aRooms.get(random.nextInt(aRooms.size()-1));
        aRooms.remove(r);
        tRooms.add(r);
        currentRoom = r;
        r.setX(roomLimit);
        r.setY(roomLimit);
        createDoors(r);
        enterRoom(r,tsg);
    }


    public Room findRoom(int x, int y)
    {
        for(Room r: tRooms)
        {
            if(r.getX()==x&&r.getY()==y)
            {
                return r;
            }
        }
        return null;
    }

    public void look(TSG tsg)
    {
        tsg.appendMessage("You are in a "+currentRoom.getName());
        Room n = currentRoom.getNorth();
        Room s = currentRoom.getSouth();
        Room e = currentRoom.getEast();
        Room w = currentRoom.getWest();

        String text = "There are doors to the ";

        if(n!=null)
        {
            text+="North ";
        }

        if(s!=null)
        {
            text+="South ";
        }

        if(e!=null)
        {
            text+="East ";
        }

        if(w!=null)
        {
            text+="West ";
        }

        tsg.appendMessage(text);
    }

    public void displayRoomInfo(TSG tsg)
    {
        tsg.appendMessage("You have entered a "+currentRoom.getName());
        Room n = currentRoom.getNorth();
        Room s = currentRoom.getSouth();
        Room e = currentRoom.getEast();
        Room w = currentRoom.getWest();

        String text = "There are door(s) to the ";

        if(n!=null)
        {
            text+="North ";
        }

        if(s!=null)
        {
            text+="South ";
        }

        if(e!=null)
        {
            text+="East ";
        }

        if(w!=null)
        {
            text+="West ";
        }

        tsg.appendMessage(text);
    }

    public int getPotentialRooms()
    {
        return aRooms.size();
    }


    public void enterRoom(Room r,TSG tsg)
    {
        currentRoom = r;
        displayRoomInfo(tsg);
        currentRoom.roomEntered(tsg);
    }

    public Room getCurrentRoom()
    {
        return  currentRoom;
    }

    private LinkedList<Room> createAllRooms(TSG tsg)
    {
        LinkedList<Room> r = new LinkedList<>();
        r.add(new RoomLadder());

        int rl = roomLimit-1;

        for(int i=0;i<=rl*(1-specialRoomAmount);i++)
        {
            Room a = new RoomEmpty();
            a.setX(-roomLimit);
            a.setY(-roomLimit);
            r.add(a);
        }

        for(int i=0;i<=rl*specialRoomAmount;i++)
        {
            Room a = getRandomSpeicalRoom(tsg);
            a.setX(-roomLimit);
            a.setY(-roomLimit);
            r.add(a);
        }

        return r;
    }


    private Room getRandomSpeicalRoom(TSG tsg)
    {
        int i = tsg.random.nextInt(3);
        switch (i)
        {
            case 0:
                return new RoomTrappedTreasure();
            case 1:
                return new RoomHiddenTreasure();
            case 2:
                return new RoomTreasure();
            default:
                System.out.println("Invalid Room ID");
                return null;
        }
    }

    private void createDoors(Room r)
    {
        Room east = findRoom(r.getX()+1,r.getY());
        Room west = findRoom(r.getX()-1,r.getY());
        Room north = findRoom(r.getX(),r.getY()+1);
        Room south = findRoom(r.getX(),r.getY()-1);
        boolean roomCreated = false;

        if(east==null)
        {
            if(aRooms.size()>1) {
                int i = random.nextInt(roomLimit - 1);
                if (i < roomLimit - getPotentialRooms()) {
                    Room g = aRooms.get(random.nextInt(aRooms.size() - 1));
                    aRooms.remove(g);
                    tRooms.add(g);
                    g.setX(r.getX() + 1);
                    g.setY(r.getY());
                    r.setEast(g);
                    createDoors(g);
                    roomCreated = true;
                }
            }
        }else{
            r.setEast(east);
        }

        if(west==null)
        {
            if(aRooms.size()>1) {
                int i = random.nextInt(roomLimit - 1);
                if (i < roomLimit - getPotentialRooms()) {
                    Room g = aRooms.get(random.nextInt(aRooms.size() - 1));
                    aRooms.remove(g);
                    tRooms.add(g);
                    g.setX(r.getX() - 1);
                    g.setY(r.getY());
                    r.setWest(g);
                    createDoors(g);
                    roomCreated = true;
                }
            }
        }else{
            r.setWest(west);
        }

        if(north==null)
        {
            if(aRooms.size()>1) {
                int i = random.nextInt(roomLimit - 1);
                if (i < roomLimit - getPotentialRooms()) {
                    Room g = aRooms.get(random.nextInt(aRooms.size() - 1));
                    aRooms.remove(g);
                    tRooms.add(g);
                    g.setX(r.getX());
                    g.setY(r.getY() + 1);
                    r.setNorth(g);
                    createDoors(g);
                    roomCreated = true;
                }
            }
        }else{
            r.setNorth(north);
        }

        if(south==null)
        {
            if(aRooms.size()>1) {
                int i = random.nextInt(roomLimit - 1);
                if (i < roomLimit - getPotentialRooms()) {
                    Room g = aRooms.get(random.nextInt(aRooms.size() - 1));
                    aRooms.remove(g);
                    tRooms.add(g);
                    g.setX(r.getX());
                    g.setY(r.getY() - 1);
                    r.setSouth(g);
                    createDoors(g);
                    roomCreated = true;
                }
            }
        }else{
            r.setSouth(south);
        }

        if (roomCreated==false&&aRooms.size()>1)
        {
            if(north==null)
            {
                Room g = aRooms.get(random.nextInt(aRooms.size()-1));
                aRooms.remove(g);
                tRooms.add(g);
                g.setX(r.getX());
                g.setY(r.getY()+1);
                r.setNorth(g);
                createDoors(g);
            }else if(south==null){
                Room g = aRooms.get(random.nextInt(aRooms.size()-1));
                aRooms.remove(g);
                tRooms.add(g);
                g.setX(r.getX());
                g.setY(r.getY()-1);
                r.setSouth(g);
                createDoors(g);
            }else if(east==null)
            {
                Room g = aRooms.get(random.nextInt(aRooms.size()-1));
                aRooms.remove(g);
                tRooms.add(g);
                g.setX(r.getX()+1);
                g.setY(r.getY());
                r.setEast(g);
                createDoors(g);
            }else if(west==null)
            {
                Room g = aRooms.get(random.nextInt(aRooms.size()-1));
                aRooms.remove(g);
                tRooms.add(g);
                g.setX(r.getX()-1);
                g.setY(r.getY());
                r.setWest(g);
                createDoors(g);
            }
        }
    }
}
