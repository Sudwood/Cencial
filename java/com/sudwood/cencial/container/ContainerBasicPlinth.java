package com.sudwood.cencial.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

public class ContainerBasicPlinth extends Container
{
	private TileEntityBasicPlinth plinth;
	private int lastBurnTime = 0;
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
	
	public ContainerBasicPlinth(InventoryPlayer playerInv, TileEntityBasicPlinth plinthInv, EntityPlayer player)
	{
		plinth = plinthInv;
		IItemHandler itemhandler = this.plinth.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotItemHandler(itemhandler, 0, 16, 22));
		this.addSlotToContainer(new SlotItemHandler(itemhandler, 1, 147, 22));
		for(int i = 0; i < 3;i++)
		{
			for(int x = 0;x<9;x++)
			{
				this.addSlotToContainer(new Slot(playerInv, x+i*9+9,8+x*18,84+i*18));
			}
		}
		for(int y = 0; y < 9; y++)
		{
			this.addSlotToContainer(new Slot(playerInv, y, 8+y*18,142));
		}
	}
	public void detectAndSendChanges()
    {
		 super.detectAndSendChanges();
		 for (int i = 0; i < this.listeners.size(); ++i)
	     {
			 IContainerListener icontainerlistener = this.listeners.get(i);
			 if (this.lastBurnTime != this.plinth.getBurnTime())
	            {
	                icontainerlistener.sendWindowProperty(this, 0, this.plinth.getBurnTime());
	            }
	     }
	 
    }
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
		if(id==0)
        this.plinth.setBurnTime(data);
    }
	
	 @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
	        ItemStack itemstack = ItemStack.EMPTY;
	        Slot slot = this.inventorySlots.get(index);

	        if (slot != null && slot.getHasStack()) {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (index < TileEntityBasicPlinth.SIZE) {
	                if (!this.mergeItemStack(itemstack1, TileEntityBasicPlinth.SIZE, this.inventorySlots.size(), true)) {
	                    return ItemStack.EMPTY;
	                }
	            } else if (!this.mergeItemStack(itemstack1, 0, TileEntityBasicPlinth.SIZE, false)) {
	                return ItemStack.EMPTY;
	            }

	            if (itemstack1.isEmpty()) {
	                slot.putStack(ItemStack.EMPTY);
	            } else {
	                slot.onSlotChanged();
	            }
	        }

	        return itemstack;
	    }

}
