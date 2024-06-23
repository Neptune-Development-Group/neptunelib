package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneItemRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import com.neptunedevelopmentteam.neptunetest.NeptuneTest;
import com.neptunedevelopmentteam.neptunetest.items.TestDataItem;
import net.minecraft.item.Item;

public class TestItems implements NeptuneItemRegistrationType {
    // Uses the field name as the name of the item
    public static final Item TEST_ITEM = new Item(new NeptuneItemSettings());

    @CustomName("SECRET_TEST_ITEM") // Use the CustomName annotation to change the name of the item
    public static final Item OTHER_ITEM = new Item(new NeptuneItemSettings());

    public static final Item TEST_ITEM_WITH_GROUP = new Item(new NeptuneItemSettings()
            .group(() -> NeptuneTest.ITEM_GROUP));

    public static final Item CUSTOM_TEST_ITEM_WITH_DATA = new TestDataItem(new NeptuneItemSettings());
}