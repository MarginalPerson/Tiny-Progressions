package com.kashdeya.tinyprogressions.armor;

import java.util.List;

import com.kashdeya.tinyprogressions.handlers.ArmorHandler;
import com.kashdeya.tinyprogressions.inits.TechArmor;
import com.kashdeya.tinyprogressions.inits.TechItems;
import com.kashdeya.tinyprogressions.main.TinyProgressions;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class LavaArmour extends ItemArmor {
	
	public LavaArmour(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
		super(material, renderIndex, equipmentSlotIn);
		this.maxStackSize = 1;
		this.setCreativeTab(TinyProgressions.tabTP);
	}
	
    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = new ItemStack(TechItems.lava_crystal);
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
    	ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    	ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    	ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    	ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    	if (((head != null) && (head.getItem() == TechArmor.lava_helmet) && 
    			(chest != null) && (chest.getItem() == TechArmor.lava_chestplate) && 
    			(legs != null) && (legs.getItem() == TechArmor.lava_leggings) && 
    			(feet != null) && (feet.getItem() == TechArmor.lava_boots)) || (entity.capabilities.isCreativeMode) || (entity.isSpectator())){
    		if (ArmorHandler.lava_armor && ArmorHandler.lava_armor_fire){
    			entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 180, ArmorHandler.lava_armor_fire_lvl, true, false));
    		}
    		if (ArmorHandler.lava_armor && ArmorHandler.lava_armor_resistance){
    			entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 180, ArmorHandler.lava_armor_resistance_lvl, true, false));
    		}
    	}
    }
    
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	if (ArmorHandler.lava_armor && (ArmorHandler.lava_armor_resistance || ArmorHandler.lava_armor_fire)){
    		tooltip.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.lavaarmor_1").getFormattedText());
    	}
    	if (ArmorHandler.lava_armor && ArmorHandler.lava_armor_fire){
    		tooltip.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.lavaarmor_2").getFormattedText());
    	}
    	if (ArmorHandler.lava_armor && ArmorHandler.lava_armor_resistance){
    		tooltip.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.lavaarmor_3").getFormattedText());
    	}
    }
}