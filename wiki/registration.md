# Registration

Neptunelib allows simple and easy registration of various things, even some things that you normally don't register

All you have to do is create a class and implement one of the existing easy registration types, or even create your [own](%wiki%/custom_registration_types)
## Item Registration
Supports the `CustomName` annotation to set a custom name

If you want a custom item group for an item go to [this](%wiki%/item_groups) page
```java
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
```
## Block Registration
Supports the `CustomName` annotation to set a custom name

If you want a custom item group for a block item go to [this](%wiki%/item_groups) page
```java
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
```

`WARNING`: The NeptuneBlockSettings is planned to be worked sometime in the future, so existing setups with this may break in the future.

When this happens there'll be a git commit history link that shows the changes that were made to the test mod to port the changes from the old version to the new version

