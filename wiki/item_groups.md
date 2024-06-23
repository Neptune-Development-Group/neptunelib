# Item Groups
## Creating the Item Group
```java
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
        // If you use the easy registration system, register it all before you initialize the item group
        ...
        ITEM_GROUP.initialize();
    }
}
```

## Adding an Item Group to an Item
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneItemRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import com.neptunedevelopmentteam.neptunetest.NeptuneTest;
import net.minecraft.item.Item;

public class TestItems implements NeptuneItemRegistrationType {
    ...
    // Refer to %wiki%/registration
    // On how this registration system works
    
    public static final Item TEST_ITEM_WITH_GROUP = new Item(new NeptuneItemSettings()
            .group(() -> NeptuneTest.ITEM_GROUP));
}
```

This applies to the NeptuneItemSettings part of the Block Registration