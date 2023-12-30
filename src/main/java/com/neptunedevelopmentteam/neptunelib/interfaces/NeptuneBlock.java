package com.neptunedevelopmentteam.neptunelib.interfaces;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface NeptuneBlock {

    NeptuneBlockSettings neptunelib$getSettings();
}
