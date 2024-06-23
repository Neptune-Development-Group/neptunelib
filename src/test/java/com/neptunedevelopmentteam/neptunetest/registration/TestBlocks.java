package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneBlockRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import com.neptunedevelopmentteam.neptunetest.NeptuneTest;
import net.minecraft.block.Block;

public class TestBlocks implements NeptuneBlockRegistrationType {
    public static final Block TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());

    public static final Block TEST_BLOCK_WITH_ITEM = new Block(NeptuneBlockSettings.createWithItemSettings(
            new NeptuneItemSettings()).getSettings());

    public static final Block TEST_ORE_BLOCK = new Block(NeptuneBlockSettings.createWithItemSettings(
            new NeptuneItemSettings()).getSettings()
    );

    @CustomName("OTHER_TEST_BLOCK")
    public static final Block UNIQUE_TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());

    public static final Block BLOCK_ITEM_WITH_A_GROUP = new Block(NeptuneBlockSettings.createWithItemSettings(
            new NeptuneItemSettings().group(() -> NeptuneTest.ITEM_GROUP)
    ).getSettings());
}
