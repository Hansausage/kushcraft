package dev.kushcraftdev.KushCraft.init;

import java.util.ArrayList;
import java.util.List;

import dev.kushcraftdev.KushCraft.common.ItemCannabisPlant;
import dev.kushcraftdev.KushCraft.common.ItemCannabisSeed;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item CANNABIS_PLANT = new ItemCannabisPlant("item_cannabis_plant", 0, 0, true);
    public static final Item CANNABIS_SEED = new ItemCannabisSeed("item_cannabis_seed", BlockInit.BLOCK_CANNABIS_CROP, Blocks.FARMLAND);
}
