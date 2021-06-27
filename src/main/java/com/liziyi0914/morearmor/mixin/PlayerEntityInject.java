package com.liziyi0914.morearmor.mixin;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.liziyi0914.morearmor.armor.BaseArmor;
import com.liziyi0914.morearmor.data.DataManager;
import com.mojang.authlib.GameProfile;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityInject extends LivingEntity {

    @Shadow
    public abstract void addExperience(int experience);

    @Shadow
    public abstract GameProfile getGameProfile();

    protected PlayerEntityInject(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")//尾部
    )
    private void tick(CallbackInfo ci) {
        Item helmet = this.getEquippedStack(EquipmentSlot.HEAD).getItem();
        Item chest = this.getEquippedStack(EquipmentSlot.CHEST).getItem();
        Item leggings = this.getEquippedStack(EquipmentSlot.LEGS).getItem();
        Item boots = this.getEquippedStack(EquipmentSlot.FEET).getItem();

        if (helmet instanceof BaseArmor armor) {
            onArmor(armor.getBlock());
        }
        if (chest instanceof BaseArmor armor) {
            onArmor(armor.getBlock());
        }
        if (leggings instanceof BaseArmor armor) {
            onArmor(armor.getBlock());
        }
        if (boots instanceof BaseArmor armor) {
            onArmor(armor.getBlock());
        }

    }

    private void onArmor(Block block) {
        DataManager.put(getUuid(), block);
    }
}
