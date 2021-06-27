package com.liziyi0914.morearmor.armormaterial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

@Builder
@AllArgsConstructor
public class BaseArmorMaterial implements ArmorMaterial {

    int[] durabilityMap;//定义护甲的耐久，此处数据按照从头到脚的顺序
    int[] protectionAmountMap;//定义护甲的保护值，顺序同上
    int enchantability;
    SoundEvent equipSound;
    Ingredient repairIngredient;
    String name;
    float toughness;
    float knockbackResistance;

    public BaseArmorMaterial() {
        this(new int[]{20, 30, 25, 15}, new int[]{2, 5, 6, 3}, 100, null, null, "base", 10, 0);
    }

    public BaseArmorMaterial(String name) {
        this(new int[]{20, 30, 25, 15}, new int[]{2, 5, 6, 3}, 100, null, null, name, 10, 0);
    }

    @Override
    public int getDurability(EquipmentSlot arg0) {
        return durabilityMap[arg0.getEntitySlotId()] * 25;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot arg0) {
        return protectionAmountMap[arg0.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;//设置附魔等级
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;//设置使用时的声音，可选
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;//设置使用铁砧修复的配方，可选
    }

    @Override
    public String getName() {
        return name;//设置名称，后期添加材质需要使用
    }

    @Override
    public float getToughness() {
        return toughness;//设置武器韧性
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;//击退抗性
    }

}
