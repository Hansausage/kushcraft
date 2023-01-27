package dev.kushcraftdev.KushCraft.common;

import dev.kushcraftdev.KushCraft.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabKushcraft extends CreativeTabs {

    public CreativeTabKushcraft(String name) {
        super("kushcraft_creative_tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.CANNABIS_SEED);
    }
}
