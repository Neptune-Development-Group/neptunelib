package com.neptunedevelopmentteam.neptunelib.interfaces;

import net.minecraft.util.math.BlockPos;

public interface NeptuneWorld {

    /**
     * Updates the block if it is on the server.
     *
     * @param  blockPos  the position of the block to update
     */
    void neptunelib$updateBlockIfOnServer(BlockPos blockPos);
}
