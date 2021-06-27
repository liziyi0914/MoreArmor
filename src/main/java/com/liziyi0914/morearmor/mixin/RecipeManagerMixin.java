package com.liziyi0914.morearmor.mixin;

import com.google.gson.JsonElement;
import com.liziyi0914.morearmor.MoreArmorMod;
import com.liziyi0914.morearmor.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        Field[] fields = Blocks.class.getFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(null);
                if (obj instanceof Block block) {
                    if(MoreArmorMod.isInvalid(block))continue;
                    String name = Registry.BLOCK.getId(block).getPath();
                    map.put(new Identifier(MoreArmorMod.MODID, name + "_helmet"), Utils.autoRecipe(Utils.ARMOR_TYPE_HELMET, block));
                    map.put(new Identifier(MoreArmorMod.MODID, name + "_chestplate"), Utils.autoRecipe(Utils.ARMOR_TYPE_CHESTPLATE, block));
                    map.put(new Identifier(MoreArmorMod.MODID, name + "_leggings"), Utils.autoRecipe(Utils.ARMOR_TYPE_LEGGINGS,block));
                    map.put(new Identifier(MoreArmorMod.MODID, name + "_boots"), Utils.autoRecipe(Utils.ARMOR_TYPE_BOOTS, block));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
