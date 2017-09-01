package foopis.main.rooms;

import foopis.main.TSG;

public interface Room {

    void action(TSG tsg);

    void roomEntered(TSG tsg);

    double getEncountChance();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void setNorth(Room r);

    void setSouth(Room r);

    void setEast(Room r);

    void setWest(Room r);

    Room getNorth();

    Room getWest();

    Room getEast();

    Room getSouth();

    public String getName();
}
