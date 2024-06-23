# Registration

Neptunelib allows simple and easy registration of various things, even some things that you normally don't register in the typical fashion.

All you have to do is create a class and implement one of the existing easy registration types, or even create your [own](%wiki%/custom_registration_types).
## Item Registration
Supports the `CustomName` annotation to set a custom name.

If you want a custom item group for an item go to [this](%wiki%/item_groups) page.
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneItemRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.minecraft.item.Item;

public class TestItems implements NeptuneItemRegistrationType {
    // Uses the field name as the name of the item
    public static final Item TEST_ITEM = new Item(new NeptuneItemSettings());

    @CustomName("SECRET_TEST_ITEM") // Use the CustomName annotation to change the name of the item
    public static final Item OTHER_ITEM = new Item(new NeptuneItemSettings());
}
```
## Block Registration
Supports the `CustomName` annotation to set a custom name.

If you want a custom item group for a block item go to [this](%wiki%/item_groups) page.
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneBlockRegistrationType;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.minecraft.block.Block;

public class TestBlocks implements NeptuneBlockRegistrationType {
    public static final Block TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());

    public static final Block TEST_BLOCK_WITH_ITEM = new Block(NeptuneBlockSettings.createWithItemSettings(
            new NeptuneItemSettings()).getSettings());

    @CustomName("OTHER_TEST_BLOCK")
    public static final Block UNIQUE_TEST_BLOCK = new Block(NeptuneBlockSettings.create().getSettings());
}
```

`WARNING`: The NeptuneBlockSettings is planned to be worked sometime in the future, so existing setups with this may break in the future.

When this happens there'll be a git commit history link that shows the changes that were made to the test mod to port the changes from the old version to the new version.
## Data Type Registration (For NBT Components)
This depends on information from this [wiki](%wiki%/easy_data) page, also contains all the supported types for this type of data registration.
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataRegistry;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneDataRegistrationType;
import net.minecraft.util.Identifier;

public class TestDataTypes implements NeptuneDataRegistrationType {
    public static final NeptuneDataType<Integer> INTEGER_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "integer_data_type"), 0);

    public static final NeptuneDataType<String> STRING_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "string_data_type"), "");

    public static final NeptuneDataType<Boolean> BOOLEAN_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "boolean_data_type"), false);
}

```
## Sound Registration
This requires that you've set up [datagen](%wiki%/datagen).
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneSoundRegistrationType;
import net.minecraft.util.Identifier;

public class TestSounds implements NeptuneSoundRegistrationType {
    public static final NeptuneSound TEST_SOUND = new NeptuneSound(Identifier.of("neptunetest", "test sound"),
            Identifier.of("neptunetest", "test_sound_1"),
            Identifier.of("minecraft", "ambient.cave.cave1"));
}
```
You can obtain SoundEvent from the NeptuneSound object, I may change NeptuneSound to be an extension of SoundEvent in the future for simplicity.
## Ore Registration
This requires that you've set up [datagen](%wiki%/datagen).

One of the newest additions to neptunelib is the ore registration system, ores require two different json files (that can be done with datagen) and also required to be registered through the registries on top of that so this simplifies the process of making ores, there's more advanced ways of using this system, but you can look through the javadocs for that, I may later add a separate page for this in the future.
```java
package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneOre;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneOreRegistrationType;
import net.minecraft.util.Identifier;

public class TestOres implements NeptuneOreRegistrationType {
    public static final NeptuneOre TEST_ORE = NeptuneOre.createStoneOverworldOre(
            Identifier.of("neptunetest", "test_ore"), // Ore Identifier
            TestBlocks.TEST_ORE_BLOCK, // Ore Block
            50, // The highest height the ore can generate at
            -50, // The lowest height the ore can generate at
            3, // The vein size
            2, // Veins per chunk
            0.5f // Chance that on generation that if the block is exposed to air that it'll not generate
    );

    public static final NeptuneOre DEEPSLATE_TEST_ORE = NeptuneOre.createDeepslateOverworldOre(
            Identifier.of("neptunetest", "test_ore"),
            TestBlocks.TEST_ORE_BLOCK,
            50,
            -50,
            3,
            2,
            0.5f
    );
}
```

As this is a new and mostly experimental feature (Though fully functional within the expected use case) it may be subject to changes, there is also more advanced ways to use this system too, the default functions are for the most common use cases, but you can also create a new instance of `NeptueneOre` to pass in all the data you want to give it, and then there's also the `addModifier` and `addBlockReplacement` additions to it too

Support for other dimensions besides vanilla dimensions is currently lacking, but will change in a later update.