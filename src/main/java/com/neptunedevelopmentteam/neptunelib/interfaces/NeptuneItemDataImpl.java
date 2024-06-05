package com.neptunedevelopmentteam.neptunelib.interfaces;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataSource;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Unique;

public interface NeptuneItemDataImpl {

    default  <A> void setStackData(ItemStack stack, NeptuneDataType<A> type, A data) {
        ((NeptuneDataSource) (Object) stack).neptunelib$setData(type, data);
    }
    default <A> A getStackData(ItemStack stack, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) stack).neptunelib$getData(type);
    }
    default <A> A getStackDataOrDefault(ItemStack stack, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) stack).getDataOrDefault(type);
    }
    default <A> A getStackDataOrElse(ItemStack stack, NeptuneDataType<A> type, A default_value) {
        return ((NeptuneDataSource) (Object) stack).getDataOrElse(type, default_value);
    }
    default <A> A getOrCreateStackData(ItemStack stack, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) stack).getOrCreateData(type);
    }
}
