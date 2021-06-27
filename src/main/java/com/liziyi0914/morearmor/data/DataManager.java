package com.liziyi0914.morearmor.data;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

import java.util.Collection;
import java.util.UUID;

/**
 * 2021/6/27<br>
 * MoreArmor<br>
 *
 * @author huanmeng_qwq
 */
public class DataManager {
    private static final Multimap<UUID, Block> data = LinkedHashMultimap.create();

    public static Collection<Block> get(Entity entity) {
        return data.get(entity.getUuid());
    }

    public static void clear(Entity entity) {
        data.removeAll(entity.getUuid());
    }

    public static void put(UUID uuid, Block block) {
        data.put(uuid, block);
    }
}
