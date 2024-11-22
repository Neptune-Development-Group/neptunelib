package com.neptunedevelopmentteam.neptunelib.interfaces;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataSource;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import net.minecraft.block.entity.BlockEntity;

public interface NeptuneBlockEntityDataImpl {
    default <A> void setBlockEntityData(BlockEntity blockEntity, NeptuneDataType<A> type, A data) {
        ((NeptuneDataSource) (Object) blockEntity).neptunelib$setData(type, data);
    }

    default <A> A getBlockEntityData(BlockEntity blockEntity, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) blockEntity).neptunelib$getData(type);
    }

    default <A> A getBlockEntityDataOrDefault(BlockEntity blockEntity, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) blockEntity).getDataOrDefault(type);
    }

    default <A> A getBlockEntityDataOrElse(BlockEntity blockEntity, NeptuneDataType<A> type, A default_value) {
        return ((NeptuneDataSource) (Object) blockEntity).getDataOrElse(type, default_value);
    }

    default <A> A getOrCreateBlockEntityData(BlockEntity blockEntity, NeptuneDataType<A> type) {
        return ((NeptuneDataSource) (Object) blockEntity).getOrCreateData(type);
    }
}
