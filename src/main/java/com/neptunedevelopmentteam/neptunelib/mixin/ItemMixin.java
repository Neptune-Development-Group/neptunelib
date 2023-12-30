package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.ForcedItemSettings;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin implements ForcedItemSettings {
    @Unique
    NeptuneItemSettings neptuneItemSettings;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(Item.Settings settings, CallbackInfo ci) {
        if (settings instanceof NeptuneItemSettings) {
            neptuneItemSettings = (NeptuneItemSettings) settings;
        }
    }

    @Override
    public NeptuneItemSettings neptunelib$getSettings() {
        return neptuneItemSettings;
    }
}
