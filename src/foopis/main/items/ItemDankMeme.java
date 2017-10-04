package foopis.main.items;

import foopis.main.TSG;

public class ItemDankMeme extends Item
{

    public ItemDankMeme()
    {
        name = "Dank Meme";
    }

    public boolean use(TSG tsg)
    {
        tsg.appendMessage("You have consumed a dank meme");
        tsg.appendMessage("You gained 1 stat point!");
        tsg.player.addStatPoint();
        return true;
    }
}
