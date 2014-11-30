package com.juanwan11_magicnation.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class itemGemStoneBloodSword extends ItemSword{
//vars

	public itemGemStoneBloodSword(ToolMaterial toolMaterial) {
		super(toolMaterial.EMERALD);
		setMaxDamage(1561);
	}
	
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setString("type", "Normal");
	}
	
	private String checkType(){
		ItemStack itemStack = new ItemStack(MAItems.itemGemStoneBloodSword);
		
		String type =itemStack.stackTagCompound.getString("type");
		switch(type){
		case "Poison":
			itemStack.stackTagCompound.setString("type", "Wither");
			Minecraft.getMinecraft().thePlayer.sendChatMessage("The sword has gained poisones powers ");
			break;
		case "Wither":
			itemStack.stackTagCompound.setString("type", "Hunger");
			break;
		case "Hunger":
			itemStack.stackTagCompound.setString("type", "LifeSteal");
			break;
		case "LifeSteal":
			itemStack.stackTagCompound.setString("type", "Normal");
			break;
		case "Normal":
			itemStack.stackTagCompound.setString("type", "Poison");
			break;
		}
		return type;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {		
		checkType();
		return itemStack;
		}		
		
	 public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase entity1)
	    {
			String type=checkType();
			if(type.equals("Normal")){
				itemStack.damageItem(1, entity1);
			}
			else if (type.equals("Poison")){
		        itemStack.damageItem(1, entity1);
				entity.addPotionEffect(new PotionEffect(Potion.poison.id, 200,3));			
			}else if(type.equals("Wither")){
				 itemStack.damageItem(1, entity1);
					entity.addPotionEffect(new PotionEffect(Potion.wither.id, 200,3));	
			}else if(type.equals("Hunger")){
				 itemStack.damageItem(1, entity1);
					entity.addPotionEffect(new PotionEffect(Potion.hunger.id, 200,3));	
			}else if(type.equals("LifeSteal")){
				 itemStack.damageItem(1, entity1);
					entity.addPotionEffect(new PotionEffect(Potion.poison.id, 140,1));	
					entity1.addPotionEffect(new PotionEffect(Potion.regeneration.id, 140,1));	
			}
	       
				return true;
	    }

}
