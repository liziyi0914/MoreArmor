package com.liziyi0914.morearmor.armor;

import com.liziyi0914.morearmor.ExampleMod;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseArmor extends ArmorItem {
    @Getter
    private Block block;
    public BaseArmor(Block block,ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(ExampleMod.FABRIC_EXAMPLE_GROUP));
        this.block=block;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("block.minecraft." + getMaterial().getName()));
    }
}