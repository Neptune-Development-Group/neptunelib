package com.neptunedevelopmentteam.neptunelib.core.util;

import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

public class NeptuneUtil {

    public static BlockEntityType<? extends BlockEntity> getBlockEntityTypeForBlock(Block block) {
        return ((NeptuneBlock) block).neptunelib$getBlockEntityType().get();
    }
}
