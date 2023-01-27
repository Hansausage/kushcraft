package dev.kushcraftdev.KushCraft.common;

import dev.kushcraftdev.KushCraft.KushCraft;
import dev.kushcraftdev.KushCraft.init.ItemInit;
import net.minecraft.item.ItemFood;

public class ItemCannabisPlant extends ItemFood {

    public ItemCannabisPlant(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(KushCraft.KUSHCRAFT_CREATIVE_TAB);
        ItemInit.ITEMS.add(this);
    }

}