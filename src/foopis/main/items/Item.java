package foopis.main.items;

import foopis.main.TSG;

public class Item {
    protected String name;

    public boolean use(TSG tsg)
    {
        tsg.appendMessage("This is the Default Item Thing");
        return true;
    }

    public String getName()
    {
        return name;
    }
}
