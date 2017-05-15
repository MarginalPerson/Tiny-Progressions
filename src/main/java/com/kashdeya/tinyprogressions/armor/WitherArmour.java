package com.kashdeya.tinyprogressions.armor;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kashdeya.tinyprogressions.inits.TechArmor;
import com.kashdeya.tinyprogressions.main.tinyprogressions;

public class WitherArmour extends ItemArmor {
	
	public WitherArmour(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
		super(material, renderIndex, equipmentSlotIn);
		this.maxStackSize = 1;
		this.setCreativeTab(tinyprogressions.tabTP);
	}
	
    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = new ItemStack(Items.NETHER_STAR);
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
    
	@Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
    	ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    	ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    	ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    	ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    	if (((head != null) && (head.getItem() == TechArmor.wither_helmet) && 
    			(chest != null) && (chest.getItem() == TechArmor.wither_chestplate) && 
    			(legs != null) && (legs.getItem() == TechArmor.wither_leggings) && 
    			(feet != null) && (feet.getItem() == TechArmor.wither_boots)) || (entity.capabilities.isCreativeMode) || (entity.isSpectator())){
    		entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 180, 0, true, false));
    		entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 180, 0, true, false));
    		entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 180, 3, true, false));
    		entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 180, 3, true, false));
    		}
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
    {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.reinforcedarmor_1").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.reinforcedarmor_2").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.reinforcedarmor_3").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.reinforcedarmor_4").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.reinforcedarmor_5").getFormattedText());
    }
}