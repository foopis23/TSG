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
        int healthDrop = 10;
        int damageBoost = 20;

        tsg.appendMessage("You have consumed a dank meme");
        tsg.player.raiseDamage(tsg,damageBoost);
        tsg.player.lowerMaximumHealth(tsg,healthDrop);
        return true;
    }
}
