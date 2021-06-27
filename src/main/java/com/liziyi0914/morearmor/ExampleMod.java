package com.liziyi0914.morearmor;

import com.liziyi0914.morearmor.armor.BaseArmor;
import com.liziyi0914.morearmor.armormaterial.BaseArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;

public class ExampleMod implements ModInitializer {

    public static final String MODID = "more_armor";

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "tab"), () -> new ItemStack(Blocks.CHEST));

    @Override
    public void onInitialize() {
        Field[] fields = Blocks.class.getFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(null);
                if (obj instanceof Block block) {
                    if(isInvalid(block))continue;
                    String name = Registry.BLOCK.getId(block).getPath();
                    ArmorMaterial material = new BaseArmorMaterial(name);
                    Registry.register(Registry.ITEM, new Identifier(MODID, name + "_helmet"), new BaseArmor(block, material, EquipmentSlot.HEAD));
                    Registry.register(Registry.ITEM, new Identifier(MODID, name + "_chestplate"), new BaseArmor(block, material, EquipmentSlot.CHEST));
                    Registry.register(Registry.ITEM, new Identifier(MODID, name + "_leggings"), new BaseArmor(block, material, EquipmentSlot.LEGS));
                    Registry.register(Registry.ITEM, new Identifier(MODID, name + "_boots"), new BaseArmor(block, material, EquipmentSlot.FEET));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isInvalid(Block block) {
        return block == Blocks.AIR || block == Blocks.BEDROCK;
    }
}
