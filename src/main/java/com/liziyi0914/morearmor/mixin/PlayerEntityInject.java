package com.liziyi0914.morearmor.mixin;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.liziyi0914.morearmor.armor.BaseArmor;
import com.mojang.authlib.GameProfile;
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
    private static final Multimap<UUID, Block> DATA = LinkedHashMultimap.create();

    @Shadow
    public abstract void addExperience(int experience);

    @Shadow
    public abstract GameProfile getGameProfile();

    protected PlayerEntityInject(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void tick(CallbackInfo ci) {
        Item helmet = this.getEquippedStack(EquipmentSlot.HEAD).getItem();
        Item chest = this.getEquippedStack(EquipmentSlot.CHEST).getItem();
        Item leggings = this.getEquippedStack(EquipmentSlot.LEGS).getItem();
        Item boots = this.getEquippedStack(EquipmentSlot.FEET).getItem();

        if (helmet instanceof BaseArmor armor) {
            onArmor(armor.getBlock());
        }
    }

    private void onArmor(Block block) {
        DATA.put(getUuid(), block);
    }
}
