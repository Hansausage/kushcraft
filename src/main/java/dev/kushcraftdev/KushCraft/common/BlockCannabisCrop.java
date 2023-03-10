package dev.kushcraftdev.kushcraft.common;

import java.util.Random;

import dev.kushcraftdev.kushcraft.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCannabisCrop extends BlockBush implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    @SideOnly(Side.CLIENT)
    public BlockCannabisCrop(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        setCreativeTab(getCreativeTabToDisplayOn());
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    /**
     * @return
     */
    protected PropertyInteger getAgeProperty() {
        return AGE;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockBush#getBoundingBox(net.minecraft.block.state.IBlockState, net.minecraft.world.IBlockAccess, net.minecraft.util.math.BlockPos)
     */
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROPS_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockBush#canSustainBush(net.minecraft.block.state.IBlockState)
     */
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.FARMLAND;
    }

    /**
     * @return
     */
    public int getMaxAge() {
        return 7;
    }

    /**
     * @param state
     * @return
     */
    public boolean isMaxAge(IBlockState state) {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
    }

    /**
     * @param age
     * @return
     */
    public IBlockState withAge(int age) {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    /**
     * @param state
     * @return
     */
    protected int getAge(IBlockState state) {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }

    /**
     * @param worldIn
     * @param pos
     * @param state
     */
    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    /**
     * @param worldIn
     * @return
     */
    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.getInt(worldIn.rand, 2, 5);
    }

    /**
     * @param blockIn
     * @param worldIn
     * @param pos
     * @return
     */
    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));

                if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn)) {
                    f1 = 1.0F;

                    if (iblockstate.getBlock().isFertile(worldIn, blockpos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();

        if (flag && flag1) {
            f /= 2.0F;
        }
        else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();

            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockBush#updateTick(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, java.util.Random)
     */
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        if (!world.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (world.getLightFromNeighbors(pos.up()) >= 9) {
            int i = this.getAge(state);

            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, world, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
                }
            }
        }        
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockBush#canBlockStay(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState)
     */
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    /**
     * @return ItemSeed
     */
    protected Item getSeed() {
        return ItemInit.CANNABIS_SEED;
    } 
 
    /**
     * @return ItemCrop
     */
    protected Item getCrop() {
        return ItemInit.CANNABIS_PLANT;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#getDrops(net.minecraft.util.NonNullList, net.minecraft.world.IBlockAccess, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, int)
     */
    @SuppressWarnings("unused")
	@Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, 0);
        int age = getAge(state);
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= getMaxAge())
        {
            int k = 3 + fortune;

            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (rand.nextInt(2 * getMaxAge()) <= age)
                {
                    drops.add(new ItemStack(this.getSeed(), 1, 0));
                }
            }
        }
    }

    @SuppressWarnings("unused")
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        if (false && !worldIn.isRemote) {
            int i = this.getAge(state);

            if (i >= this.getMaxAge()) {
                int j = 3 + fortune;

                for (int k = 0; k < j; ++k) {
                    if (worldIn.rand.nextInt(2 * this.getMaxAge()) <= i) {
                        spawnAsEntity(worldIn, pos, new ItemStack(this.getSeed()));
                    }
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#getItemDropped(net.minecraft.block.state.IBlockState, java.util.Random, int)
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#getItem(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState)
     */
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.getSeed());
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.IGrowable#canGrow(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, boolean)
     */
    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.IGrowable#canUseBonemeal(net.minecraft.world.World, java.util.Random, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState)
     */
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.IGrowable#grow(net.minecraft.world.World, java.util.Random, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState)
     */
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state);
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#getStateFromMeta(int)
     * Convert given metadata into a BlockState
     * @param int
     * @return IBlockState
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.withAge(meta);
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#getMetaFromState(net.minecraft.block.state.IBlockState)
     */
    public int getMetaFromState(IBlockState state) {
        return this.getAge(state);
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#createBlockState()
     */
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
}
