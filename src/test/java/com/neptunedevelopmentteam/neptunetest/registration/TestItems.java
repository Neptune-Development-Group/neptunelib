package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneItemRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.minecraft.item.Item;

public class TestItems implements NeptuneItemRegistrationType {
    // Uses the field name as the name of the item
    public Item TEST_ITEM = new Item(new NeptuneItemSettings());

    @CustomName("SECRET_TEST_ITEM") // Use the CustomName annotation to change the name of the item
    public Item OTHER_ITEM = new Item(new NeptuneItemSettings());
}