package com.liziyi0914.morearmor.effect;

import lombok.Builder;
import net.minecraft.entity.LivingEntity;

public abstract class AbstractEffect {

    private final LivingEntity entity;

    public AbstractEffect(LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public abstract void exec(ArmorDesc desc);

    @Builder
    public static class ArmorDesc {
        boolean helmet = false;
        boolean chestplate = false;
        boolean leggings = false;
        boolean boots = false;
    }

}
