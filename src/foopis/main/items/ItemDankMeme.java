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
        tsg.appendMessage("Will To Live: "+tsg.healthLimit+" --"+ healthDrop + "-> "+(tsg.healthLimit- healthDrop));
        tsg.appendMessage("Damage Boost: "+tsg.damageBoost+" -+"+ damageBoost +"-> "+(tsg.damageBoost+ damageBoost));
        tsg.damageBoost+= damageBoost;
        tsg.healthLimit-= healthDrop;
        if(tsg.health>tsg.healthLimit)
        {
            tsg.health = tsg.health - (tsg.health-tsg.healthLimit);
        }
        return true;
    }
}
