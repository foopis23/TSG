package foopis.main;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

import foopis.main.rooms.*;

public class MapGenerator
{
    private Random rand;
    private LinkedList<MapCrawler> crawlers;
    private LinkedList<Room> rooms;
    
    public MapGenerator()
    {
        rand = new Random();
        crawlers = new LinkedList<>();
        rooms = new LinkedList<>();
    }
    
    public LinkedList<Room> makeFloor(TSG tsg)
    {
        crawlers.clear();
        MapCrawler newCrawler = new MapCrawler(0, 0);
        int roomsPerFloor = newCrawler.getRoomsPerFloor();
        crawlers.add(newCrawler);
        rooms.clear();
        Room startRoom = new RoomEmpty();
        startRoom.setX(0);
        startRoom.setY(0);
        rooms.add(startRoom);
        while(rooms.size() < roomsPerFloor)
        {
            if(crawlers.size() == 0)
            {
                ArrayList<Room> okRooms = new ArrayList<>();
                int i = 0;
                while(i < rooms.size())
                {
                    Room room = rooms.get(i);
                    if(room.getNumExits() < 3)
                    {
                        okRooms.add(room);
                    }
                    
                    i++;
                }
                
                Room okRoom = okRooms.get(rand.nextInt(okRooms.size()));
                crawlers.add(new MapCrawler(okRoom.getX(), okRoom.getY()));
            }
            
            int i = 0;
            while(i < crawlers.size())
            {
                MapCrawler crawler = crawlers.get(i);
                if(crawler.isDead())
                {
                    crawlers.remove(crawler);
                }
                else
                {
                    rooms = crawler.move(rooms);
                    if(rand.nextInt(20) == 0)
                    {
                        crawler.die();
                        break;
                    }
                    
                    if(rand.nextInt(3) == 0)
                    {
                        crawlers.add(new MapCrawler(crawler.getX(), crawler.getY()));
                    }
                    
                    if(rooms.size() >= roomsPerFloor)
                    {
                        break;
                    }
                }
                
                i++;
            }
        }
        
        return rooms;
    }
    
    public LinkedList<Room> getRooms()
    {
        return rooms;
    }
}