# Custom Registration Types

You can create a custom type, which is full possible, but currently I no example code on how to do so, nor have I figured out what I want it to look like.

As anything I think of, would just be baked in anyways. So here's the source for one of the existing registration types

`Item Registration Type`
```java
package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public interface NeptuneItemRegistrationType extends NeptuneEasyRegistrationType<Item> {
    default void register(Item object, Field fieldSource, String namespace) {
        String name = NeptuneEasyRegistrationType.getName(fieldSource);
        if (object instanceof NeptuneItem neptuneItem) {
            NeptuneItemSettings neptuneItemSettings = neptuneItem.neptunelib$getSettings();
            if (neptuneItemSettings != null && !neptuneItemSettings.getGroups().isEmpty()) {
                for (Supplier<NeptuneItemGroup> group : neptuneItemSettings.getGroups()) {
                    if (!group.get().items.contains(object)) group.get().__addItemToGroup(object);
                }
            }
            Registry.register(Registries.ITEM, Identifier.of(namespace, name), object);
        }
    }
}
```

If you want to hook it up to datagen you can create a class that holds those variables in a static reference and then from your datagen class take those values and datagen with it

Like here's how sound datagen and registration is done

```java
package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public interface NeptuneSoundRegistrationType extends NeptuneEasyRegistrationType<NeptuneSound> {

    default void register(NeptuneSound object, Field fieldSource, String namespace) {
        Registry.register(Registries.SOUND_EVENT, object.getSoundIdentifier(), object.getSoundEvent());
        NeptuneRegistrationDatagenHookupManager.addSound(object);
    }
}
```

`NeptuneRegistrationDatagenHookupManager` just contains a list of sounds
```java
package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneOre;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

public class NeptuneRegistrationDatagenHookupManager {
    private static List<NeptuneSound> sounds = new ArrayList<>();
    ...
    public static List<NeptuneSound> getSounds() {
        return sounds;
    }

    public static void addSound(NeptuneSound neptuneSound) {
        sounds.add(neptuneSound);
    }
    ...
}
```

I usually then take that information and work with it when the registries are built

Inside your datagen class that uses the `NeptuneDataGenerator` override this function
```java
@Override
public void onRegistryBuild(RegistryBuilder registryBuilder) {
// Custom registry build code
}
```

The below is how sound is done, though just take a look at the source code behind `NeptuneDataGenerator` to see how it works
```java
for (NeptuneSound sound : NeptuneRegistrationDatagenHookupManager.getSounds()) {
            this.handler.addSound(sound);
}
```

Yes I'll eventually add a decent tutorial here, but I'm tired okay :(