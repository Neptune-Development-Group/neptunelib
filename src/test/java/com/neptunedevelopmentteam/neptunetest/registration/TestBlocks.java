package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneBlockRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.minecraft.block.Block;

public class TestBlocks implements NeptuneBlockRegistrationType {
    public Block TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());

    public Block TEST_BLOCK_WITH_ITEM = new Block(NeptuneBlockSettings.createWithItemSettings(
            new NeptuneItemSettings()).getSettings());

    @CustomName("OTHER_TEST_BLOCK")
    public Block UNIQUE_TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());
}
