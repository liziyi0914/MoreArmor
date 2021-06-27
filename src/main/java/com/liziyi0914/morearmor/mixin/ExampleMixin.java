package com.liziyi0914.morearmor.mixin;

import com.liziyi0914.morearmor.armor.BaseArmor;
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

@Mixin(PlayerEntity.class)
public abstract class ExampleMixin extends LivingEntity {
	@Shadow
	public abstract void addExperience(int experience);

	protected ExampleMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(
			method = "tick",
			at = @At("HEAD")
	)
	private void tick(CallbackInfo ci) {
		Item helmet = this.getEquippedStack(EquipmentSlot.HEAD).getItem();
		Item chestplate = this.getEquippedStack(EquipmentSlot.CHEST).getItem();
		Item leggings = this.getEquippedStack(EquipmentSlot.LEGS).getItem();
		Item boots = this.getEquippedStack(EquipmentSlot.FEET).getItem();

		if (helmet instanceof BaseArmor) {
			//
		}
	}
}
