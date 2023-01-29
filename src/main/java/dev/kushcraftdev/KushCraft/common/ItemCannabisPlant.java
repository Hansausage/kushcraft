package dev.kushcraftdev.kushcraft.common;

import dev.kushcraftdev.kushcraft.KushCraft;
import dev.kushcraftdev.kushcraft.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCannabisPlant extends Item {

    public ItemCannabisPlant(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(KushCraft.KUSHCRAFT_CREATIVE_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {

        for(EnumWeedTypes types : EnumWeedTypes.values()) {
            int typeBits = types.getMetadata();
            int metadata = typeBits;
            ItemStack subItemStack = new ItemStack(this, 1, metadata);
            subItems.add(subItemStack);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int metadata = stack.getMetadata();
        EnumWeedTypes types = EnumWeedTypes.byMetadata(metadata);
        return this.getUnlocalizedName() + "." + types.getName();
    }

 

    public static enum EnumWeedTypes implements IStringSerializable {

        KUSH(0, "kush"),
        OBAMA(1, "obama"),
        GEORGE_BUSH(2, "george_bush"),
        NINE_ELEVEN(3, "9/11"),
        MENTHOL_WORMS(4, "menthol_worms"),
        TOCAHONTAS(5, "tokahontas");

        private final String name;
        private final int meta;
        private static final EnumWeedTypes[] META_LOOKUP = new EnumWeedTypes[values().length];

        private EnumWeedTypes(int i_meta, String i_name) {
            this.meta = i_meta;
            this.name = i_name;
        }

        static {
            for(EnumWeedTypes value : values()) {
                META_LOOKUP[value.getMetadata()] = value;
            }
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static EnumWeedTypes byMetadata(int meta) {
            if(meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
    }

    
}