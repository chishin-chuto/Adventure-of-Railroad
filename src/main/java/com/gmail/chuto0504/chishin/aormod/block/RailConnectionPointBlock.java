package com.gmail.chuto0504.chishin.aormod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import com.gmail.chuto0504.chishin.aormod.item.RailKitItem;

public class RailConnectionPointBlock extends Block {
    public RailConnectionPointBlock(Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, 
            Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // インスタンスメソッドとして呼び出し
        if (player.getItemInHand(hand).getItem() instanceof RailKitItem) {
            RailKitItem.handleConnectionPoint(level, pos, player);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }
} 