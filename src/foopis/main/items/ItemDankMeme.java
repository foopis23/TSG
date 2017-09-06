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
        tsg.appendMessage("Will To Live: "+tsg.player.getHealthLimit()+" --"+ healthDrop + "-> "+(tsg.player.getHealthLimit()- healthDrop));
        tsg.appendMessage("Damage Boost: "+tsg.player.getDamageBoost()+" -+"+ damageBoost +"-> "+(tsg.player.getDamageBoost()+ damageBoost));
        tsg.player.setDamageBoost(tsg.player.getDamageBoost()+damageBoost);
        tsg.player.setMaxHealth(tsg.player.getHealthLimit()-healthDrop);
        return true;
    }
}
