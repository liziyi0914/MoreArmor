package com.liziyi0914.morearmor.armor;

import com.liziyi0914.morearmor.ExampleMod;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseArmor extends ArmorItem {
    @Getter
    private final Block block;

    public BaseArmor(Block block, ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(ExampleMod.GROUP));
        this.block = block;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("block.minecraft." + getMaterial().getName()));
    }

    @Override
    public String getTranslationKey() {
        final String key = String.format("item.more_armor.%s_%s", Registry.BLOCK.getId(block).getPath(), getSlotType().getName());
        if (I18n.hasTranslation(key)) {
            return key;
        }
        return "item.more_armor.base_" + getSlotType().getName();
    }

    @Override
    public Text getName() {
        return new TranslatableText(getTranslationKey(), block.getName().getString());
    }

    @Override
    public Text getName(ItemStack stack) {
        return new TranslatableText(getTranslationKey(stack), block.getName().getString());
    }
}