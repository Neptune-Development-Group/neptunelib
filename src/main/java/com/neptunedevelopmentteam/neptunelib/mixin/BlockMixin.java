package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Block.class)
public class BlockMixin implements NeptuneBlock {

    @Unique
    NeptuneBlockSettings neptuneBlockSettings;

    @Unique
    Supplier<BlockEntityType<? extends BlockEntity>> blockEntityType;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (settings instanceof NeptuneBlockSettings) {
            this.neptuneBlockSettings = (NeptuneBlockSettings) settings;
        }
    }

    @Override
    public NeptuneBlockSettings neptunelib$getSettings() {
        return this.neptuneBlockSettings;
    }

}
