package com.neptunedevelopmentteam.neptunelib.mixin;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataSource;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import net.minecraft.component.ComponentHolder;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements NeptuneDataSource, ComponentHolder {

    @Shadow @Nullable public abstract <T> T set(ComponentType<? super T> type, @Nullable T value);

    @Shadow public abstract ComponentMap getComponents();

    @Unique
    @Override
    public <T> void neptunelib$setData(NeptuneDataType<T> type, T data) {
        this.set(type.getDataComponentType(), data);
    }

    @Unique
    @Override
    public <A> A neptunelib$getData(NeptuneDataType<A> type) {
        return (A) this.get(type.getDataComponentType());
    }
}
