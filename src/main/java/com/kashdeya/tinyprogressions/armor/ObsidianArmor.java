package com.kashdeya.tinyprogressions.armor;

import java.util.List;

import com.kashdeya.tinyprogressions.handlers.ArmorHandler;
import com.kashdeya.tinyprogressions.inits.TechArmor;
import com.kashdeya.tinyprogressions.main.TinyProgressions;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ObsidianArmor extends ItemArmor {

    public ObsidianArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
        super(material, renderIndex, equipmentSlotIn);
        this.setMaxStackSize(1);
        this.setCreativeTab(TinyProgressions.tabTP);
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        ItemStack mat = new ItemStack(Blocks.OBSIDIAN);
        return !mat.isEmpty() && OreDictionary.itemMatches(mat, repair, false) || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        if (((!head.isEmpty()) && (head.getItem() == TechArmor.obsidian_helmet) &&
            (!chest.isEmpty()) && (chest.getItem() == TechArmor.obsidian_chestplate) &&
            (!legs.isEmpty()) && (legs.getItem() == TechArmor.obsidian_leggings) &&
            (!feet.isEmpty()) && (feet.getItem() == TechArmor.obsidian_boots)) || (entity.capabilities.isCreativeMode) || (entity.isSpectator())) {
            if (ArmorHandler.obsidian_armor && ArmorHandler.obsidian_armor_resistance) {
                entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 180, ArmorHandler.obsidian_armor_resistance_lvl, false, false));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (ArmorHandler.obsidian_armor && ArmorHandler.obsidian_armor_resistance) {
            tooltip.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.obsidianarmor_1").getFormattedText());
            tooltip.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.obsidianarmor_2").getFormattedText());
        }
    }
}