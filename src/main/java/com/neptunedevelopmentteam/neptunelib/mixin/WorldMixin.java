package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneWorld;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(World.class)
public class WorldMixin implements NeptuneWorld {
    @Override
    public void updateBlockIfOnServer(BlockPos blockPos) {
        World world = (World) (Object) this;
        if (!(world instanceof ServerWorld serverWorld)) return;
        serverWorld.getChunkManager().markForUpdate(blockPos);
    }
}
