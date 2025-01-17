package com.gmail.chuto0504.chishin.aormod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import com.gmail.chuto0504.chishin.aormod.util.RailGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.InteractionHand;

public class RailKitItem extends Item {
    private static final Map<UUID, BlockPos> selectedPoints = new HashMap<>();
    private static final int MAX_RAIL_LENGTH = 100;

    public RailKitItem(Properties properties) {
        super(properties);
    }

    public static void handleConnectionPoint(Level level, BlockPos newPos, Player player) {
        UUID playerId = player.getUUID();
        
        if (!selectedPoints.containsKey(playerId)) {
            // 最初の接続点を選択
            selectedPoints.put(playerId, newPos);
            player.displayClientMessage(Component.translatable("message.aormod.rail.start_selected"), true);
            return;
        }

        BlockPos startPos = selectedPoints.get(playerId);
        selectedPoints.remove(playerId);

        // 距離チェック
        if (startPos.distManhattan(newPos) > MAX_RAIL_LENGTH) {
            player.displayClientMessage(Component.translatable("message.aormod.rail.too_far"), true);
            return;
        }

        // レール生成
        boolean success = RailGenerator.generateRailsBetweenPoints(level, startPos, newPos);
        
        if (success) {
            player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
            player.displayClientMessage(Component.translatable("message.aormod.rail.placed"), true);
        } else {
            player.displayClientMessage(Component.translatable("message.aormod.rail.failed"), true);
        }
    }
} 