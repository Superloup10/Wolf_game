package fr.wolf.game.gameobjects;

import fr.wolf.engine.Inventory;
import fr.wolf.game.gameobjects.item.EquippableItem;

public class Equipment
{
    private EquippableItem[] equip;
    private Inventory inventory;

    public Equipment(Inventory inventory)
    {
        equip = new EquippableItem[EquippableItem.NUM_SLOTS];
        this.inventory = inventory;
    }

    public boolean equip(EquippableItem item)
    {
        int index = item.getSlot();

        if(equip[index] != null)
        {
            if(!deEquip(index))
                return false;
        }
        inventory.remove(item);
        equip[index] = item;
        return true;
    }

    public boolean deEquip(int slot)
    {
        if(inventory.add(equip[slot]))
        {
            equip[slot] = null;
            return true;
        }
        return false;
    }
}