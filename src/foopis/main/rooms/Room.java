package foopis.main.rooms;

import foopis.main.TSG;

public interface Room {

    void action(TSG tsg);

    void roomEntered(TSG tsg, Room cameFrom, int direction);

    void firstEnter(TSG tsg, Room cameFrom, int direction);

    String getDoors(Room cameFrom);

    double getEncountChance();

    void go(TSG tsg, int direction);
}
