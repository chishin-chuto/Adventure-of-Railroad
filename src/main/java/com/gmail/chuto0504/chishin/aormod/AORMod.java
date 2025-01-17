package com.gmail.chuto0504.chishin.aormod;

import com.gmail.chuto0504.chishin.aormod.init.ModBlocks;
import com.gmail.chuto0504.chishin.aormod.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(AORMod.MOD_ID)
public class AORMod {
    public static final String MOD_ID = "aormod";

    public AORMod(IEventBus modEventBus) {
        // レジストリの登録
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
    }
} 