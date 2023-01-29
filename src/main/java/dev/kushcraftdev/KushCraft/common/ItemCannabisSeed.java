package dev.kushcraftdev.kushcraft.common;

import dev.kushcraftdev.kushcraft.KushCraft;
import dev.kushcraftdev.kushcraft.init.BlockInit;
import dev.kushcraftdev.kushcraft.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ItemCannabisSeed extends ItemSeeds {

    public ItemCannabisSeed(String name, Block crops, Block soil) {
        super(crops, soil);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(KushCraft.KUSHCRAFT_CREATIVE_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return BlockInit.BLOCK_CANNABIS_CROP.getDefaultState();
    }
}
