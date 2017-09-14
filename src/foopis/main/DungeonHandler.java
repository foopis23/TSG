package foopis.main;

import foopis.main.rooms.*;

import java.util.LinkedList;

public class DungeonHandler
{
    private LinkedList<Room> rooms;
    private Room currentRoom;
    private int enteredFrom;
    private MapGenerator mapGen = new MapGenerator();

    public DungeonHandler()
    {
        currentRoom = null;
        enteredFrom = -1;
    }

    public void go(TSG tsg, int direction)
    {
        int x = currentRoom.getX();
        int y = currentRoom.getY();
        Room room = null;

        switch(direction)
        {
        case 0:
            room = findRoom(x,y-1);
            break;

        case 1:
            room = findRoom(x+1,y);
            break;

        case 2:
            room = findRoom(x,y+1);
            break;

        case 3:
            room = findRoom(x-1,y);
            break;
        }
        
        if(room != null && currentRoom.isExit(direction))
        {
            enteredFrom = (direction + 2) % 4;
            enterRoom(room, tsg);
        }
        else
        {
            tsg.appendMessage("You cannot go this direction");
        }
    }

    public void createFloor(TSG tsg)
    {
        rooms = mapGen.makeFloor(tsg);
        enterRoom(rooms.get(0), tsg);
    }

    public Room findRoom(int x, int y)
    {
        for(Room room: rooms)
        {
            if(room.getX() == x && room.getY() == y)
            {
                return room;
            }
        }
        
        return null;
    }

    public void displayRoomInfo(TSG tsg, boolean isNewRoom)
    {
        String preface = "You are in a ";
        if(isNewRoom)
        {
            preface = "You have entered a ";
        }

        tsg.appendMessage(preface + currentRoom.getName());
        
        String text = "There are doors to the ";

        int numExits = 0;
        String[] directions = {"North", "East", "South", "West"};
        boolean[] exits = {false, false, false, false};
        for(int i = 0; i < 4; i++)
        {
            if(currentRoom.isExit(i))
            {
                exits[i] = true;
                numExits++;
            }
        }

        String spacer;
        if(numExits <= 2)
        {
            spacer = " ";
        }
        else
        {
            spacer = ", ";
        }
        
        int roomCount = numExits;
        for(int i = 0; i < 4; i++)
        {
            if(exits[i])
            {
                String append = directions[i];
                
                if(enteredFrom == i)
                {
                    append += " (entered from)";
                }
                
                if(numExits > 1)
                {
                    if(roomCount == 1)
                    {
                        append = "and " + append;
                    }
                    else
                    {
                        append += spacer;
                    }
                }
                
                text += append;
                
                roomCount--;
            }
        }
        
        tsg.appendMessage(text);
    }

    public void enterRoom(Room room, TSG tsg)
    {
        currentRoom = room;
        displayRoomInfo(tsg, true);
        currentRoom.roomEntered(tsg);
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }
}
