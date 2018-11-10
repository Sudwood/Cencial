package com.sudwood.cencial.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import com.sudwood.cencial.CencialMod;
import com.sudwood.cencial.Reference;
import com.sudwood.cencial.items.CencialItems;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

public class BlockBasicPlinth extends BaseBlock
{
	public static final PropertyBool DISCHARGING = PropertyBool.create("discharging");
	public static final PropertyBool CHARGING = PropertyBool.create("charging");
    public BlockBasicPlinth(String name) 
    {
		super(Material.ROCK);
		setUnlocalizedName(CencialMod.MODID + ".basicplinth");
        setRegistryName(name);
        this.setCreativeTab(CencialMod.tabCencial);
        this.setResistance(100f);
        this.setHardness(1F);
        CencialBlocks.BLOCKS.add(this);
        ItemBlock itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(name);
        CencialItems.ITEMS.add(itemBlock);
	}

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new TileEntityBasicPlinth();
	}
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
        	playerIn.openGui(CencialMod.instance, Reference.BasicPlinthGui, worldIn, pos.getX(), pos.getY(), pos.getZ());
        	return true;
        }
        return true;
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	TileEntityBasicPlinth tile = (TileEntityBasicPlinth) worldIn.getTileEntity(pos);
    	IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
    	for(int i = 0; i < itemHandler.getSlots(); i++)
    	{
    		ItemStack stack = itemHandler.getStackInSlot(i);
    		if (stack != null) {
    			EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
    			worldIn.spawnEntity(item);
    		}
    	}
		
    	super.breakBlock(worldIn, pos, state);
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    }
    
    /**
     * 
     * @param active
     * @param world
     * @param pos
     * @param type 0 - discharing 1 - charging
     */
    public static void setState(boolean active, World world, BlockPos pos, int type)
    {
    	switch(type)
    	{
    	case 0: // discharging
    		IBlockState state = world.getBlockState(pos);
    		TileEntity tile = world.getTileEntity(pos);
    		world.setBlockState(pos, CencialBlocks.basicPlinth.getDefaultState().withProperty(DISCHARGING, active));
    		if(tile!=null)
    		{
    			tile.validate();world.setTileEntity(pos, tile);
    		}
    		break;
    	case 1: // charging
    		IBlockState state1 = world.getBlockState(pos);
    		TileEntity tile1 = world.getTileEntity(pos);
    		world.setBlockState(pos, CencialBlocks.basicPlinth.getDefaultState().withProperty(CHARGING, active));
    		if(tile1!=null)
    		{
    			tile1.validate();world.setTileEntity(pos, tile1);
    		}
    		break;
    	}
    }
    
	
	 @SideOnly(Side.CLIENT)
	 public void initModel() 
	 {
	    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	 }
	 @Override
	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
	        return false;
	    }

	    @Override
	    public boolean isBlockNormalCube(IBlockState blockState) {
	        return false;
	    }

	    @Override
	    public boolean isOpaqueCube(IBlockState blockState) {
	        return false;
	    }


    
}
