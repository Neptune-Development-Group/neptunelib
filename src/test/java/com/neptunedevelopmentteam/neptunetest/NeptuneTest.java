package com.neptunedevelopmentteam.neptunetest;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneEasyRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneRegistrationManager;
import com.neptunedevelopmentteam.neptunetest.registration.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class NeptuneTest implements ModInitializer {
    public static final NeptuneItemGroup ITEM_GROUP = new NeptuneItemGroup(Identifier.of("neptunetest", "test_item_group"), TestItems.TEST_ITEM.getDefaultStack());
    @Override
    public void onInitialize() {
        System.out.println("Test booting...");
        NeptuneRegistrationManager.setup("neptunetest",
                TestBlocks.class,
                TestItems.class,
                TestOres.class,
                TestDataTypes.class,
                TestSounds.class
        );
        ITEM_GROUP.initialize();
    }
}
