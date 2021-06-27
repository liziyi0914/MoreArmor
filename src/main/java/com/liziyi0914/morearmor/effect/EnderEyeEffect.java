package com.liziyi0914.morearmor.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.Heightmap;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EnderEyeEffect extends AbstractEffect {

    long lastTp = 0L;

    public EnderEyeEffect(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void exec(ArmorDesc desc) {
        LivingEntity entity = getEntity();
        long current = System.currentTimeMillis();
        if (current - lastTp >= TimeUnit.SECONDS.toMillis(20) && entity.isTouchingWaterOrRain()) {
            lastTp = current;
            Random rnd = new Random();
            double x = entity.getX() + rnd.nextInt(10) - 5;
            double z = entity.getZ() + rnd.nextInt(10) - 5;
            double y = entity.getEntityWorld().getTopY(Heightmap.Type.MOTION_BLOCKING, (int) x, (int) z);
            getEntity().teleport(x, y, z);
        }
    }
}
