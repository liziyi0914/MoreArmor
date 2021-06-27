package com.liziyi0914.morearmor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

public class Utils {

    public static final int ARMOR_TYPE_HELMET = 0;
    public static final int ARMOR_TYPE_CHESTPLATE = 1;
    public static final int ARMOR_TYPE_LEGGINGS = 2;
    public static final int ARMOR_TYPE_BOOTS = 3;

    private static final String[][] ARMOR_RECIPE = new String[][]{
            new String[]{"XXX", "X X"},
            new String[]{"X X", "XXX", "XXX"},
            new String[]{"XXX", "X X", "X X"},
            new String[]{"X X", "X X"}
    };
    private static final String[] ARMOR_NAME = new String[]{
            "helmet",
            "chestplate",
            "leggings",
            "boots"
    };

    private static final Gson gson = new Gson();

    public static JsonElement autoRecipe(int type, Block block) {
        String id= Registry.ITEM.getId(block.asItem()).getPath();
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:crafting_shaped");

        JsonArray jsonArray = new JsonArray();
        for (String s : ARMOR_RECIPE[type]) {
            jsonArray.add(s);
        }
        json.add("pattern", jsonArray);

        JsonObject individualKey = new JsonObject();
        JsonObject keyList = new JsonObject();
        individualKey.addProperty("item", "minecraft:" + id);
        keyList.add("X", individualKey);
        json.add("key", keyList);

        JsonObject result = new JsonObject();
        result.addProperty("item", ExampleMod.MODID + ":" + id + "_" + ARMOR_NAME[type]);
        result.addProperty("count", 1);
        json.add("result", result);
        //This creates:
        //"result": {
        //  "item": "modid:copper_pickaxe",
        //  "count": 1
        //}

        return json;
    }

    public static Gson gson() {
        return gson;
    }

}
