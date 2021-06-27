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

	public static final ItemGroup FABRIC_EXAMPLE_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "example"), () -> new ItemStack(Blocks.CHEST));

	@Override
	public void onInitialize() {
		Field[] fields = Blocks.class.getFields();
		for (Field field : fields) {
			try {
				Object obj = field.get(null);
				if (obj instanceof Block block) {
					String name = Registry.BLOCK.getId(block).getPath();
					ArmorMaterial material = new BaseArmorMaterial(name);
					Registry.register(Registry.ITEM, new Identifier(MODID, name + "_helmet"), new BaseArmor(material, EquipmentSlot.HEAD));
					Registry.register(Registry.ITEM, new Identifier(MODID, name + "_chestplate"), new BaseArmor(material, EquipmentSlot.CHEST));
					Registry.register(Registry.ITEM, new Identifier(MODID, name + "_leggings"), new BaseArmor(material, EquipmentSlot.LEGS));
					Registry.register(Registry.ITEM, new Identifier(MODID, name + "_boots"), new BaseArmor(material, EquipmentSlot.FEET));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
