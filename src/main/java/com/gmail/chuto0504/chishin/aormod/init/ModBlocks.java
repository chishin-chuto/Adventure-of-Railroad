package com.gmail.chuto0504.chishin.aormod.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.gmail.chuto0504.chishin.aormod.block.CustomRailBlock;
import com.gmail.chuto0504.chishin.aormod.block.RailConnectionPointBlock;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, "aormod");

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static final DeferredHolder<Block, Block> RAIL = BLOCKS.register("rail",
        () -> new CustomRailBlock(BlockBehaviour.Properties.of().mapColor(Blocks.RAIL.defaultMapColor())));

    public static final DeferredHolder<Block, Block> RAIL_CONNECTION_POINT = BLOCKS.register("rail_connection_point",
        () -> new RailConnectionPointBlock(BlockBehaviour.Properties.of().mapColor(Blocks.IRON_BLOCK.defaultMapColor())));
} 