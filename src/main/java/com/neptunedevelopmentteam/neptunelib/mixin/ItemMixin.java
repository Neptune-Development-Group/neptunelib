package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(Item.Settings settings, CallbackInfo ci) {
        if (settings instanceof NeptuneItemSettings) {
            ((NeptuneItemSettings) settings).group().addItemToGroup((Item) (Object) this);
        }
    }
}
