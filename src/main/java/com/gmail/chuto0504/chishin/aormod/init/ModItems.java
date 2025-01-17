package com.gmail.chuto0504.chishin.aormod.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.gmail.chuto0504.chishin.aormod.item.RailKitItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "aormod");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // レールキットアイテム
    public static final DeferredHolder<Item, Item> RAIL_KIT = ITEMS.register("rail_kit",
        () -> new RailKitItem(new Item.Properties()));

    // ブロックアイテム
    public static final DeferredHolder<Item, Item> RAIL = ITEMS.register("rail",
        () -> new BlockItem(ModBlocks.RAIL.value(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> RAIL_CONNECTION_POINT = ITEMS.register("rail_connection_point",
        () -> new BlockItem(ModBlocks.RAIL_CONNECTION_POINT.value(), new Item.Properties()));
} 