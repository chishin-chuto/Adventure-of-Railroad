package com.gmail.chuto0504.chishin.aormod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import com.gmail.chuto0504.chishin.aormod.init.ModBlocks;
import java.util.ArrayList;
import java.util.List;

public class RailGenerator {
    public static boolean generateRailsBetweenPoints(Level level, BlockPos start, BlockPos end) {
        List<BlockPos> railPositions = calculateRailPositions(start, end);
        
        // レール設置が可能か確認
        if (!canPlaceRails(level, railPositions)) {
            return false;
        }

        // レールを設置
        for (BlockPos pos : railPositions) {
            placeRail(level, pos);
        }

        return true;
    }

    private static List<BlockPos> calculateRailPositions(BlockPos start, BlockPos end) {
        List<BlockPos> positions = new ArrayList<>();
        
        // ベジェ曲線を使用してレールの位置を計算
        double distance = start.distManhattan(end);
        int steps = (int) Math.ceil(distance);
        
        // 制御点を計算（単純な中点を使用）
        BlockPos controlPoint = new BlockPos(
            (start.getX() + end.getX()) / 2,
            Math.max(start.getY(), end.getY()),
            (start.getZ() + end.getZ()) / 2
        );

        for (int i = 0; i <= steps; i++) {
            double t = i / (double) steps;
            
            // 二次ベジェ曲線の計算
            double x = Math.pow(1-t, 2) * start.getX() + 
                      2 * (1-t) * t * controlPoint.getX() + 
                      Math.pow(t, 2) * end.getX();
            
            double y = Math.pow(1-t, 2) * start.getY() + 
                      2 * (1-t) * t * controlPoint.getY() + 
                      Math.pow(t, 2) * end.getY();
            
            double z = Math.pow(1-t, 2) * start.getZ() + 
                      2 * (1-t) * t * controlPoint.getZ() + 
                      Math.pow(t, 2) * end.getZ();

            positions.add(new BlockPos((int)x, (int)y, (int)z));
        }

        return positions;
    }

    private static boolean canPlaceRails(Level level, List<BlockPos> positions) {
        for (BlockPos pos : positions) {
            if (!level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below()) || 
                !level.getBlockState(pos).isAir()) {
                return false;
            }
        }
        return true;
    }

    private static void placeRail(Level level, BlockPos pos) {
        BlockState railState = ModBlocks.RAIL.value().defaultBlockState();
        level.setBlock(pos, railState, 3);
    }
} 