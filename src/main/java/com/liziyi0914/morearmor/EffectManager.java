package com.liziyi0914.morearmor;

import com.liziyi0914.morearmor.effect.AbstractEffect;
import net.minecraft.entity.LivingEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EffectManager {

    public static final Map<LivingEntity, Map<Class<? extends AbstractEffect>, AbstractEffect>> map = new HashMap<>();

    public static void refreshEffect(LivingEntity entity, Collection<Class<? extends AbstractEffect>> classes) {
        if (!map.containsKey(entity)) {
            map.put(entity, new HashMap<>());
        }
        Map<Class<? extends AbstractEffect>, AbstractEffect> _map = map.get(entity);
        //_map.
        for (Class<? extends AbstractEffect> cls : classes) {
            if (_map.containsKey(cls)) {
                return;
            }
            try {
                _map.put(cls, cls.getDeclaredConstructor(LivingEntity.class).newInstance(entity));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static void tick(LivingEntity entity, HashMap<Class<? extends AbstractEffect>, AbstractEffect.ArmorDesc> descMap) {
        refreshEffect(entity, descMap.keySet());
    }

}
