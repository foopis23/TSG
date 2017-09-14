package foopis.main;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

import foopis.main.rooms.*;

public class MapCrawler
{
    private Random rand;
    private int roomsPerFloor;
    private boolean dead;
    private double[] roomProb = {0.45, 0.15, 0.10, 0.30}; //probability of rooms: RoomEmpty, RoomTreasure, RoomTrappedTreasure, RoomHiddenTreasure
    private int x;
    private int y;
    
    public MapCrawler(int x, int y)
    {
        dead = false;
        roomsPerFloor = 50;
        rand = new Random();
        this.x = x;
        this.y = y;
    }
    
    public LinkedList<Room> move(LinkedList<Room> rooms)
    {
        Room currentRoom = findRoom(x, y, rooms);
        int[][] moves = {{x, y-1}, {x+1, y}, {x, y+1}, {x-1, y}};
        ArrayList<int[]> choices = new ArrayList<>();
        for(int i = 0; i < 4; i++)
        {
            if(!currentRoom.isExit(i))
            {
                choices.add(moves[i]);
            }
        }
        
        if(choices.size() <= 1)
        {
            dead = true;
            return rooms;
        }
        
        int[] choice = choices.get(rand.nextInt(choices.size()));
        int direction = 0;
        for(int i = 0; i < 4; i++)
        {
            if(moves[i] == choice)
            {
                direction = i;
            }
        }
        
        Room nextRoom = findRoom(choice[0], choice[1], rooms);
        int inverseDir = (direction + 2) % 4;
        currentRoom.setExit(direction, true);
        if(nextRoom == null)
        {
            nextRoom = makeRoom(choice[0], choice[1], rooms.size());
            nextRoom.setExit(inverseDir, true);
            rooms.add(nextRoom);
        }
        else
        {
            nextRoom.setExit(inverseDir, true);
        }
        
        return rooms;
    }
    
    private Room makeRoom(int x, int y, int numRooms)
    {
        Room newRoom = new RoomEmpty();
        if(numRooms + 1 == roomsPerFloor)
        {
            newRoom = new RoomLadder();
        }
        else
        {
            int roomIndex = randFromFreq(roomProb);
            switch(roomIndex)
            {
            case 0:
                newRoom = new RoomEmpty();
                break;
            
            case 1:
                newRoom = new RoomTreasure();
                break;
                
            case 2:
                newRoom = new RoomTrappedTreasure();
                break;
                
            case 3:
                newRoom = new RoomHiddenTreasure();
                break;
            }
        }
        
        newRoom.setX(x);
        newRoom.setY(y);
        return newRoom;
    }
    
    private int randFromFreq(double[] probTable)
    {
        int index = 0;
        double cumSum = 0;
        double random = rand.nextDouble();
        for(int i = 0; i < probTable.length; i++)
        {
            cumSum += probTable[i];
            if(random < cumSum)
            {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    private Room findRoom(int x, int y, LinkedList<Room> rooms)
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
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean isDead()
    {
        return dead;
    }
    
    public void die()
    {
        dead = true;
    }
    
    public int getRoomsPerFloor()
    {
        return roomsPerFloor;
    }
}
