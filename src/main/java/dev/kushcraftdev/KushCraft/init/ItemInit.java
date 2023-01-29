package dev.kushcraftdev.kushcraft.init;

import java.util.ArrayList;
import java.util.List;

import dev.kushcraftdev.kushcraft.common.ItemCannabisPlant;
import dev.kushcraftdev.kushcraft.common.ItemCannabisSeed;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item CANNABIS_PLANT = new ItemCannabisPlant("item_cannabis_plant");
    public static final Item CANNABIS_SEED = new ItemCannabisSeed("item_cannabis_seed", BlockInit.BLOCK_CANNABIS_CROP, Blocks.FARMLAND);
}
